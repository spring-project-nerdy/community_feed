package org.example.user.application;

import static org.junit.jupiter.api.Assertions.*;

import org.example.fake.FakeObjectFactory;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.application.dto.FollowUserRequestDto;
import org.example.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {

  private final UserService userService = FakeObjectFactory.getUserService();
  private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

  private User user1;
  private User user2;

  private FollowUserRequestDto requestDto;

  @BeforeEach
  void init() {
    CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
    this.user1 = userService.createUser(dto);
    this.user2 = userService.createUser(dto);

    this.requestDto = new FollowUserRequestDto(user1.getId(),
        user2.getId());
  }

  @Test
  void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
    // given

    // when
    userRelationService.follow(requestDto);

    // then
    assertEquals(1, user1.getFollowingCount());
    assertEquals(1, user2.getFollowerCount());
  }

  @Test
  void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
    // given
    userRelationService.follow(requestDto);

    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
  }

  @Test
  void givenCreateOneUser_whenFollow_thenUserThrowError() {
    // given
    FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
  }

  @Test
  void givenCreateTwoUserFollow_whenUnFollow_thenUserUnFollowSaved() {
    // given
    userRelationService.follow(requestDto);

    // when
    userRelationService.unfollow(requestDto);

    // then
    assertEquals(0, user1.getFollowingCount());
    assertEquals(0, user2.getFollowerCount());
  }

  @Test
  void givenCreateTwoUser_whenFollow_thenUserThrowError() {
    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
  }

  @Test
  void givenCreateOneUser_whenUnFollowSelf_thenUserThrowError() {
    // given
    FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
  }

}