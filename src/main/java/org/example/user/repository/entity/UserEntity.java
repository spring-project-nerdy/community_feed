package org.example.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.common.domain.PositiveIntegerCounter;
import org.example.common.repository.entity.TimeBaseEntity;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity extends TimeBaseEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String profileImage;
  private Integer followerCount;
  private Integer followingCount;
  
  public UserEntity(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.profileImage = user.getProfileImage();
    this.followerCount = followerCount;
    this.followingCount = followingCount;
  }
  
  public User toUser() {
    return User.builder()
        .id(id)
        .info(new UserInfo(name, profileImage))
        .followerCount(new PositiveIntegerCounter(followerCount))
        .followingCount(new PositiveIntegerCounter(followingCount))
        .build();
  }
}
