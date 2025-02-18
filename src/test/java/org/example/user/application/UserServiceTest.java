package org.example.user.application;

import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.example.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

  private final UserRepository userRepository = new FakeUserRepository();
  private final UserService userService = new UserService(userRepository);

  @Test
  void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
    // given
    CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

    // when
    User savedUser = userService.createUser(dto);

    // then
    User foundUser = userService.getUser(savedUser.getId());
    UserInfo userInfo = foundUser.getInfo();
    assertEquals(foundUser.getId(), savedUser.getId());
    assertEquals("test", userInfo.getName());
  }
}