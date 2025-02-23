package org.example.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

  private final UserInfo userInfo = new UserInfo("test", "");
  private User user1;
  private User user2;

  @BeforeEach
  void init() {
     user1 = new User(1L, userInfo);
     user2 = new User(2L, userInfo);
  }

  @Test
  void givenTwoUser_whenEqual_thenReturnFalse() {
    // given
    // when
    boolean value = user1.equals(user2);

    // then
    assertFalse(value);
  }

  @Test
  void givenTwoSameIdUser_whenEqual_thenReturnTrue() {
    // given
    User sameUser = new User(1L, userInfo);

    // when
    boolean value = user1.equals(sameUser);

    // then
    assertTrue(value);
  }

  @Test
  void givenTwoUser_whenHashCode_thenReturnFalse() {
    // given

    // when
    int hashCode1 = user1.hashCode();
    int hashCode2 = user2.hashCode();

    // then
    assertNotEquals(hashCode1, hashCode2);
  }

  @Test
  void givenTwoSameIdUser_whenHashCode_thenEqual() {
    // given
    User sameUser = new User(1L, userInfo);

    // when
    int hashCode1 = user1.hashCode();
    int hashCode2 = sameUser.hashCode();

    // then
    assertEquals(hashCode1, hashCode2);
  }

  @Test
  void givenTwoUser_whenUserFollowUser2_thenIncreaseUserCount() {
    // given
    // when
    user1.follow(user2);

    // then
    assertEquals(1, user1.getFollowingCount());
    assertEquals(0, user1.getFollowerCount());

    assertEquals(0, user2.getFollowingCount());
    assertEquals(1, user2.getFollowerCount());
  }

  @Test
  void givenTowUserUser1FollowUser2_whenUnfollow_thenDecreaseUserCount() {
    // given
    // when
    user1.follow(user2);
    user1.unfollow(user2);

    // then
    assertEquals(0, user1.getFollowingCount());
    assertEquals(0, user1.getFollowerCount());

    assertEquals(0, user2.getFollowingCount());
    assertEquals(0, user2.getFollowerCount());
  }

  @Test
  void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount() {
    // given
    // when
    user1.unfollow(user2);

    // then
    assertEquals(0, user1.getFollowingCount());
    assertEquals(0, user1.getFollowerCount());

    assertEquals(0, user2.getFollowingCount());
    assertEquals(0, user2.getFollowerCount());
  }

}