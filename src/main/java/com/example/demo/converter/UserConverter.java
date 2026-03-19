package com.example.demo.converter;

import com.example.demo.entity.User;
import com.example.demo.model.request.RegisterRequest;
import com.example.demo.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    public User toEntity(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        // TODO: hash password khi tích hợp Spring Security (BCryptPasswordEncoder)
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        return user;
    }
}
