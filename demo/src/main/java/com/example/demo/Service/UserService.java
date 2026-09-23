package com.example.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRep;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRep, PasswordEncoder passwordEncoder) {
        this.userRep = userRep;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(String email, String password, String role) {

        User user = new User();

        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRep.save(user);
    }
}