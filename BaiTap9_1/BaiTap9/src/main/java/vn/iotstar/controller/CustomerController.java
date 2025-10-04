package vn.iotstar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.model.Customer;

@RestController
@EnableMethodSecurity
public class CustomerController {
	final private List<Customer> customers = List.of(
			Customer.builder().id("001").name("Đoàn Quang Khôi").email("23110244@student.hcmute.edu.vn").build(),
			Customer.builder().id("002").name("Quang Khôi").email("qkhoi1142@gmail.com").build());

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Hello is Guest");
	}

	@GetMapping("/customer/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getCustomerList() {
		List<Customer> list = this.customers;
		return ResponseEntity.ok(list);
	}

	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
		Optional<Customer> customer = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        return customer.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
}
