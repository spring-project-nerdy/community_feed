package org.example.post.repository.entity.like;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.post.domain.Post;
import org.example.post.domain.comment.Comment;
import org.example.user.domain.User;

@Entity
@Table(name="community_like")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LikeEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likedUser) {
        this.id = new LikeIdEntity(post.getId(), likedUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likedUser) {
        this.id = new LikeIdEntity(comment.getId(), likedUser.getId(), LikeTarget.COMMENT.name());
    }
}
