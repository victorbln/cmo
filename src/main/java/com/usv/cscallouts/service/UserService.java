package com.usv.cscallouts.service;

import com.usv.cscallouts.model.LoginRequest;
import com.usv.cscallouts.model.LoginResponse;
import com.usv.cscallouts.model.RegisterRequest;
import com.usv.cscallouts.model.RegisterResponse;
import com.usv.cscallouts.model.User;
import com.usv.cscallouts.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public RegisterResponse register(RegisterRequest registerRequest) {
    User user = new User();
    User user1 = userRepository.findByEmail(registerRequest.email());
    if (user1 != null) {
      log.info("User already exists");
      return null;
    }
    user.setEmail(registerRequest.email());
    user.setPassword(registerRequest.password());
    userRepository.save(user);
    RegisterResponse registerResponse = new RegisterResponse();
    registerResponse.setEmail(user.getEmail());
    registerResponse.setMessage("User registered successfully");
    registerResponse.setToken("token");
    return registerResponse;
  }

  public LoginResponse login(LoginRequest loginRequest) {
    LoginResponse loginResponse = new LoginResponse();
    User user = userRepository.findByEmail(loginRequest.getEmail());
    if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
      loginResponse.setEmail(user.getEmail());
      loginResponse.setToken("token");
      loginResponse.setMessage("Login successful");
    } else {
      loginResponse.setMessage("Login failed");

    }
    return loginResponse;
  }
}
