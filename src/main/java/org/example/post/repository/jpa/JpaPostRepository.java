package org.example.post.repository.jpa;

import org.example.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT u.id FROM PostEntity u WHERE u.author.id = :authorId")
    List<Long> findAllPostIdByAuthorId(Long authorId);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.content = :#{#postEntity.getContent()}, " +
            "p.state = :#{#postEntity.getState()}, " +
            "p.updDt = now() " +
            "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p SET p.likeCount = :#{#postEntity.likeCount}, p.updDt = now() WHERE p.id = :#{#postEntity.getId()}")
    void updateLikeCount(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p SET p.commentCount = p.commentCount + 1, p.updDt = now() WHERE p.id =:id")
    void increaseCommentCount(Long id);
}
