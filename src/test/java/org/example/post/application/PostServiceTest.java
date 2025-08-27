package org.example.post.application;

import org.example.post.application.dto.LikeRequestDto;
import org.example.post.application.dto.UpdatePostRequestDto;
import org.example.post.domain.Post;
import org.example.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // when
        Post savedPost = postService.createPost(createPostRequestDto);

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatePost() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);

        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(savedPost.getId(), user.getId(), "this is test content", PostPublicationState.PUBLIC);

        // when
        Post updatedPost = postService.updatePost(savedPost.getId(), updateDto);

        // then
        assertEquals(savedPost.getId(), updatedPost.getId());
        assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
        assertEquals(savedPost.getContent(), updatedPost.getContent());
    }

    @Test
    void givenCreatePost_whenLiked_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }


    @Test
    void givenCreatePost_whenLikedTwice_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // when
        postService.likePost(likeRequestDto);


        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePostLiked_whenUnliked_thenReturnWithOutLike() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // when
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenUnliked_thenReturnPostWithOutLike() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

}