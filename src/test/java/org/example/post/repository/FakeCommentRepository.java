package org.example.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.example.post.application.interfaces.CommentRepository;
import org.example.post.domain.comment.Comment;

public class FakeCommentRepository implements CommentRepository {

  private final Map<Long, Comment> store = new HashMap<>();

  @Override
  public Comment save(Comment comment) {
    if (comment.getId() != null) {
      store.put(comment.getId(), comment);
      return comment;
    }

    long id = store.size() + 1;
    Comment newComment = new Comment(id, comment.getPost(), comment.getAuthor(),
        comment.getContentObjets());
    store.put(id, newComment);

    return newComment;
  }

  @Override
  public Optional<Comment> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }
}
