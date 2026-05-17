package com.firstclub.membership.services;

import com.firstclub.membership.dto.UserDto;
import com.firstclub.membership.entity.User;
import com.firstclub.membership.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User register(UserDto.RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    public User login(UserDto.LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public User recordOrder(Long userId, BigDecimal orderValue) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setTotalOrderCount(user.getTotalOrderCount() + 1);
        user.setMonthlyOrderValue(user.getMonthlyOrderValue().add(orderValue));
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
