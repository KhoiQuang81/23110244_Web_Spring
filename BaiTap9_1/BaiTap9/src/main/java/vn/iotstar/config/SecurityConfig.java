package vn.iotstar.config;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // để dùng @PreAuthorize ở controller
public class SecurityConfig {
	// Tạo 2 user mẫu: admin và user
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails admin = User.withUsername("admin").password("{noop}123456") // {noop} = không mã hoá mật khẩu
				.roles("ADMIN").build();

		UserDetails user = User.withUsername("user").password("{noop}123456").roles("USER").build();

		return new InMemoryUserDetailsManager(admin, user);
	}

	// Cấu hình filter chain cho Spring Security
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/hello").permitAll() // ai cũng vào được
				.anyRequest().authenticated() // còn lại phải login
		).formLogin() // bật form login mặc định
				.and().logout(); // bật logout mặc định

		return http.build();
	}
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable()) // demo nhanh
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/hello").permitAll()
                    // để @PreAuthorize quyết định chi tiết quyền cho /customer/**
                    .requestMatchers("/customer/**").authenticated()
                    .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .logout(Customizer.withDefaults());
    return http.build();
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}