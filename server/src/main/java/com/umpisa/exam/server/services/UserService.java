package com.umpisa.exam.server.services;

import com.umpisa.exam.server.models.User;

@org.springframework.stereotype.Service
public class UserService extends Service {
  public User authenticate(String username, String password) {
    return User.authenticate(username, password);
  }

  public User create(User user) {
    // TODO: Add validation
    return transaction(() -> {
      user.save();

      return user;
    });
  }
}