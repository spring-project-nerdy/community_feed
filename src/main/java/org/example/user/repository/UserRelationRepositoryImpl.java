package org.example.user.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.post.repository.post_queue.UserPostQueueCommentRepository;
import org.example.user.application.interfaces.UserRelationRepository;
import org.example.user.domain.User;
import org.example.user.repository.entity.UserEntity;
import org.example.user.repository.entity.UserRelationEntity;
import org.example.user.repository.entity.UserRelationIdEntity;
import org.example.user.repository.jpa.JpaUserRelationRepository;
import org.example.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {
  
  private final JpaUserRelationRepository jpaUserRelationRepository;
  private final JpaUserRepository jpaUserRepository;
  private final UserPostQueueCommentRepository commandRepository;
  
  @Override
  public boolean isAlreadyFollow(User user, User targetUser) {
    UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
    return jpaUserRelationRepository.existsById(id);
  }
  
  @Override
  @Transactional
  public void save(User user, User targetUser) {
    UserRelationEntity entity = new UserRelationEntity(user.getId(),
        targetUser.getId());
    jpaUserRelationRepository.save(entity);
    jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    commandRepository.saveFollowPost(user.getId(), targetUser.getId());
  }
  
  @Override
  @Transactional
  public void delete(User user, User targetUser) {
    UserRelationEntity id = new UserRelationEntity(user.getId(),
        targetUser.getId());
    jpaUserRelationRepository.delete(id);
    jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    commandRepository.deleteUnfollowPost(user.getId(), targetUser.getId());
  }
}
