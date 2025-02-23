package org.example.user.application.interfaces;

import org.example.user.domain.User;

public interface UserRepository {

  User save(User user);
  User findById(Long id);
}
