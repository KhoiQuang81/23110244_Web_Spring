package vn.iotstar.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.dto.request.ApiResponse;
import vn.iotstar.dto.request.UserCreationRequest;
import vn.iotstar.dto.request.UserUpdateRequest;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;

import java.util.List;

// Quản lts mapping endpoint
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse <User> createUser(@RequestBody @Valid  UserCreationRequest request)
    {
        ApiResponse <User> apiResponse = new ApiResponse <>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping
    List<User> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser (@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
