package org.example.post.application.interfaces;

import org.example.post.domain.Post;
import org.example.post.domain.comment.Comment;
import org.example.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);

    boolean checkLike(Comment comment, User user);
    void like(Comment post, User user);
    void unlike(Comment post, User user);

}
