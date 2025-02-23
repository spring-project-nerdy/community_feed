package org.example.user.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.common.domain.PositiveIntegerCounter;

@Getter
@Builder
@AllArgsConstructor
public class User {

  private Long id;
  private UserInfo info;
  private PositiveIntegerCounter followingCount;
  private PositiveIntegerCounter followerCount;

  public User(Long id, UserInfo userinfo) {
    if(userinfo == null) {
      throw new IllegalArgumentException("UserInfo cannot be null");
    }

    this.id = id;
    this.info = userinfo;
    this.followingCount = new PositiveIntegerCounter();
    this.followerCount = new PositiveIntegerCounter();
  }

  public void follow(User targetUser) {
    if (targetUser.equals(this)) {
      throw new IllegalArgumentException("");
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

  public int getFollowingCount() {
    return followingCount.getCount();
  }

  public int getFollowerCount() {
    return followerCount.getCount();
  }
  
  public String getProfileImage() {
    return info.getProfileImageUrl();
  }
  
  public String getName() {
    return info.getName();
  }
  
}
