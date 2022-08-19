package com.cognizant.mfpe.auth.controller;

import com.cognizant.mfpe.auth.pojo.UserIdentityAvailability;
import com.cognizant.mfpe.auth.service.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserRepoService userRepoService;

    @GetMapping("/username")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepoService.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }


    @GetMapping("/user")
    public List<String> allUserDetails() {
        return userRepoService.getAllUserByUsername();
    }
}
