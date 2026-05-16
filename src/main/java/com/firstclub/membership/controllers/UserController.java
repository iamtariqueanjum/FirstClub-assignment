package com.firstclub.membership.controllers;

import com.firstclub.membership.dto.UserDto;
import com.firstclub.membership.entity.User;
import com.firstclub.membership.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto.Response> register(@RequestBody UserDto.RegisterRequest request) {
        User user = userService.register(request);
        UserDto.Response response = new UserDto.Response();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto.Response> login(@RequestBody UserDto.LoginRequest request) {
        User user = userService.login(request);
        UserDto.Response response = new UserDto.Response();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return ResponseEntity.ok(response);
    }




}
