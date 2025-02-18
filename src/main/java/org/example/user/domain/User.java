package org.example.user.domain;

import java.util.Objects;
import org.example.common.domain.PositiveIntegerCounter;

public class User {

  private final Long id;
  private final UserInfo info;
  private final PositiveIntegerCounter followingCount;
  private final PositiveIntegerCounter followerCount;

  public User(Long id, UserInfo userinfo) {
    if(userinfo == null) {
      throw new IllegalArgumentException();
    }

    this.id = id;
    this.info = userinfo;
    this.followingCount = new PositiveIntegerCounter();
    this.followerCount = new PositiveIntegerCounter();
  }

  public void follow(User targetUser) {
    if (targetUser.equals(this)) {
      throw new IllegalArgumentException();
    }

    followingCount.increase();
    targetUser.increaseFollowerCount();
  }

  public void unfollow(User targetUser) {
    if (this.equals(targetUser)) {
      throw new IllegalArgumentException();
    }

    followingCount.decrease();
    targetUser.decreaseFollowerCount();
  }

  private void increaseFollowerCount() {
    followerCount.increase();
  }

  private void decreaseFollowerCount() {
    followerCount.decrease();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Long getId() {
    return id;
  }

  public int followingCount() {
    return followingCount.getCount();
  }

  public int followerCount() {
    return followerCount.getCount();
  }

  public UserInfo getInfo() {
    return info;
  }

  public PositiveIntegerCounter getFollowingCount() {
    return followingCount;
  }

  public PositiveIntegerCounter getFollowerCount() {
    return followerCount;
  }
}
