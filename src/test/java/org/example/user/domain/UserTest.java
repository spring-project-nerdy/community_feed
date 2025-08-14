package org.example.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final UserInfo userInfo1 = new UserInfo("user1", "");
    private final UserInfo userInfo2 = new UserInfo("user2", "");
    private User user1;
    private User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo1);
        user2 = new User(2L, userInfo2);
    }

    @Test
    void givenTwoUser_whenEqual_thenReturnFalse() {
        // given

        // when
        boolean isSame = user1.equals(user2);

        // then
        assertFalse(isSame);
    }

    @Test
    void givenTwoSameIdUser_whenEqual_thenReturnTrue() {
        // given
        User sameUser = new User(1L, userInfo1);

        // then
        boolean isSame = user1.equals(sameUser);

        // when
        assertTrue(isSame);
    }

    @Test
    void givenTwoUser_whenHashCode_thenNotEqual() {
        //when
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        //then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    void givenTwoUser_whenHashCode_thenEqual() {
        // given
        User sameUser = new User(1L, userInfo1);

        // then
        int hashCode1 = user1.hashCode();
        int hashCode2 = sameUser.hashCode();

        // when
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() {
        //when
        user1.follow(user2);

        //then
        assertEquals(1, user1.getFollowingCounter());
        assertEquals(0, user1.getFollowerCounter());
        assertEquals(0, user2.getFollowingCounter());
        assertEquals(1, user2.getFollowerCounter());
    }

    @Test
    void givenTwoUser1FollowUser2_whenUnfollow_thenDecreaseUserCount() {
        //given
        user1.follow(user2);

        //when
        user1.unfollow(user2);

        //then
        assertEquals(0, user1.getFollowingCounter());
        assertEquals(0, user1.getFollowerCounter());
        assertEquals(0, user2.getFollowingCounter());
        assertEquals(0, user2.getFollowerCounter());
    }

    @Test
    void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount() {
        //when
        user1.unfollow(user2);

        //then
        assertEquals(0, user1.getFollowingCounter());
        assertEquals(0, user1.getFollowerCounter());
        assertEquals(0, user2.getFollowingCounter());
        assertEquals(0, user2.getFollowerCounter());
    }
}