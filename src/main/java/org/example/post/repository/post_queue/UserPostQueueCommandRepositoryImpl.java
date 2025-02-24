package org.example.post.repository.post_queue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.post.repository.entity.post.PostEntity;
import org.example.post.repository.entity.post.UserPostQueueEntity;
import org.example.post.repository.jpa.JpaPostRepository;
import org.example.post.repository.jpa.jpaUserPostQueueRepository;
import org.example.user.repository.entity.UserEntity;
import org.example.user.repository.jpa.JpaUserListQueryRepository;
import org.example.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommentRepository {
  
  private final JpaPostRepository jpaPostRepository;
  private final JpaUserRelationRepository jpaUserRelationRepository;
  private final JpaUserListQueryRepository jpaUserListQueryRepository;
  private final jpaUserPostQueueRepository jpaUserPostQueueRepository;
  
  @Override
  @Transactional
  public void publishPost(PostEntity postEntity) {
    UserEntity userEntity = postEntity.getAuthor();
    List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());
    
    List<UserPostQueueEntity> userPostQueueEntityList = followersIds.stream()
        .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
        .toList();
  
    jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
  }
  
  @Override
  @Transactional
  public void saveFollowPost(Long userId, Long targetId) {
    List<Long> postIdList = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
    List<UserPostQueueEntity> userPostQueueEntityList = postIdList.stream()
        .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
        .toList();
  
    jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
  }
  
  @Override
  @Transactional
  public void deleteUnfollowPost(Long userId, Long targetId) {
   jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
  }
}
