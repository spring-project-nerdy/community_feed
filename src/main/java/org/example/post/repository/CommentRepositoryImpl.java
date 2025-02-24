package org.example.post.repository;

import lombok.RequiredArgsConstructor;
import org.example.post.application.interfaces.CommentRepository;
import org.example.post.domain.Post;
import org.example.post.domain.comment.Comment;
import org.example.post.repository.entity.comment.CommentEntity;
import org.example.post.repository.jpa.JpaCommentRepository;
import org.example.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
  
  private final JpaCommentRepository jpaCommentRepository;
  private final JpaPostRepository jpaPostRepository;
  
  @Override
  @Transactional
  public Comment save(Comment comment) {
    Post target = comment.getPost();
    CommentEntity commentEntity = new CommentEntity(comment);
    if (comment.getId() != null) {
      jpaCommentRepository.updateCommentEntity(commentEntity);
      return comment;
    }
    commentEntity = jpaCommentRepository.save(commentEntity);
    jpaPostRepository.increaseCommentCount(target.getId());
    return commentEntity.toComment();
  }
  
  @Override
  public Comment findById(Long id) {
    CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
    return commentEntity.toComment();
  }
}
