package vn.iotstar.repository;

import vn.iotstar.entity.UserInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{
    Optional<UserInfo> findByName(String username);

}