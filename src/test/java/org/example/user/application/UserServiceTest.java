package org.example.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.fake.FakeObjectFactory;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class UserServiceTest {

  private final UserService userService = FakeObjectFactory.getUserService();

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