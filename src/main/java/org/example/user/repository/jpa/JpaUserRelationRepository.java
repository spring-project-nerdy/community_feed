package org.example.user.repository.jpa;

import java.util.List;
import org.example.user.repository.entity.UserRelationEntity;
import org.example.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUserRelationRepository extends
    JpaRepository<UserRelationEntity, UserRelationIdEntity> {
  
  @Query("SELECT u.followingUserId FROM UserRelationEntity  u WHERE u.followerUserId   = :userId")
  List<Long> findFollowers(@Param("userId") Long userId);
}
