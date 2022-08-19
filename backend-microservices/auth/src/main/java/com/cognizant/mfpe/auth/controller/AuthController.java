package com.cognizant.mfpe.auth.controller;

import com.cognizant.mfpe.auth.model.User;
import com.cognizant.mfpe.auth.pojo.ApiResponse;
import com.cognizant.mfpe.auth.pojo.LoginRequest;
import com.cognizant.mfpe.auth.pojo.LoginResponse;
import com.cognizant.mfpe.auth.pojo.SignupRequest;
import com.cognizant.mfpe.auth.security.JwtTokenProvider;
import com.cognizant.mfpe.auth.service.UserRepoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepoService userRepoService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @GetMapping("/validate")
    public ResponseEntity<ApiResponse> validateToken(@RequestHeader(name = "Authorization") String token) {
        if (!tokenProvider.validateToken(token.substring(7))) {
            log.info("Token is Invalid");
            return new ResponseEntity<>(new ApiResponse(false, "Invalid Token!"), HttpStatus.FORBIDDEN);
        }
        log.info("Token is Valid");
        return new ResponseEntity<>(new ApiResponse(true, "Valid Token!"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(loginRequest);
            log.info(jwt);
            return new ResponseEntity<>(new LoginResponse(true, String.format("Bearer %s", jwt)), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            log.error("Invalid credentials");
            return new ResponseEntity<>(new LoginResponse(false, ""), HttpStatus.FORBIDDEN);

        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepoService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword()));
        userRepoService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "User registered successfully"));
    }

    private ApiResponse fallback() {
        return new ApiResponse(false, "Request timed out.");
    }
}

