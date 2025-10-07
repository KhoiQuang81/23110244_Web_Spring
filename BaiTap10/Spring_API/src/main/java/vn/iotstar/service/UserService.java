package vn.iotstar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.dto.request.UserCreationRequest;
import vn.iotstar.dto.request.UserUpdateRequest;
import vn.iotstar.entity.User;
import vn.iotstar.exception.AppException;
import vn.iotstar.exception.ErrorCode;
import vn.iotstar.mapper.UserMapper;
import vn.iotstar.repository.UserRepository;

import java.util.List;

// Xử lý logic sub domain
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    public User createUser(UserCreationRequest request) {
        // Kiem tra user ton tai chua
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
//              throw new RuntimeException("ErrorCode.USER_EXISTED");

        // Map request vao user
        User user = userMapper.toUser(request);

//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

        // database
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {

        User user = getUser(userId);
        userMapper.updateUser(user, request);
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
