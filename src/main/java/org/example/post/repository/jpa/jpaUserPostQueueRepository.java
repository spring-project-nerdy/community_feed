package org.example.post.repository.jpa;

import org.example.post.repository.entity.post.UserPostQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {
  
  void deleteAllByUserIdAndAuthorId(Long userId, Long targetId);
}
