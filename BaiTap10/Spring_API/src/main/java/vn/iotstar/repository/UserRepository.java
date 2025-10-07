package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import vn.iotstar.dto.request.UserCreationRequest;
import vn.iotstar.entity.User;

// Tương tác trực tiếp với dbms, jpa
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String name);
}
