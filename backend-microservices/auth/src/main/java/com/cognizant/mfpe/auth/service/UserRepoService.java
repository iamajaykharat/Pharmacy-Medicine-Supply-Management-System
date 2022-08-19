package com.cognizant.mfpe.auth.service;

import com.cognizant.mfpe.auth.model.User;
import com.cognizant.mfpe.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRepoService {

    @Autowired
    UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<String> getAllUserByUsername() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}
