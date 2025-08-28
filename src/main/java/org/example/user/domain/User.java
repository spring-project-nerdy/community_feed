package org.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.common.domain.PositiveIntegerCounter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private final Long id;
    private final UserInfo userInfo;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        if(userInfo == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.userInfo = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        if(targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if(this.equals(targetUser)) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public int followingCount() {
        return followingCount.getCount();
    }

    public int followerCount() {
        return followerCount.getCount();
    }

    public String getProfileImage() {
        return userInfo.getProfileImageUrl();
    }

    public String getName() {
        return userInfo.getName();
    }
}
