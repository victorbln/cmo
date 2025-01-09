package com.usv.cscallouts.controller;

import com.usv.cscallouts.model.LoginRequest;
import com.usv.cscallouts.model.LoginResponse;
import com.usv.cscallouts.model.RegisterRequest;
import com.usv.cscallouts.model.RegisterResponse;
import com.usv.cscallouts.model.User;
import com.usv.cscallouts.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }
  @PostMapping("/register")
  public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
    return userService.register(registerRequest);
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    return userService.login(loginRequest);
  }
}
