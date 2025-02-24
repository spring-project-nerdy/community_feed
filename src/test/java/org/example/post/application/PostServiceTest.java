package org.example.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.post.application.dto.LikeRequestDto;
import org.example.post.application.dto.UpdatePostRequestDto;
import org.example.post.domain.Post;
import org.example.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Test;

class PostServiceTest extends PostApplicationTestTemplate {
  
  @Test
  void givenPostRequestDto_whenCreate_thenReturnPost() {
    // when
    Post savePost = postService.createPost(postRequestDto);
    
    // then
    Post post = postService.getPost(savePost.getId());
    assertEquals(savePost, post);
  }
  
  @Test
  void givenCreatePost_whenUpdate_thenReturnUpdatePost() {
    // given
    Post savePost = postService.createPost(postRequestDto);
    
    // when
    UpdatePostRequestDto updatePostRequestDto = new UpdatePostRequestDto(user.getId(),
        "this is test content", PostPublicationState.PUBLIC);
    Post updatePost = postService.updatePost(savePost.getId(), updatePostRequestDto);
    
    // then
    assertEquals(savePost.getId(), updatePost.getId());
    assertEquals(savePost.getAuthor(), updatePost.getAuthor());
    assertEquals(savePost.getContent(), updatePost.getContent());
  }
  
  @Test
  void givenCreatedPost_whenLiked_thenReturnPostWithLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);
    
    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);
    
    // then
    assertEquals(1, savedPost.getLikeCount());
  }
  
  @Test
  void givenCreatedPost_whenLikedTwice_thenReturnPostWithLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);
    
    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);
    postService.likePost(likeRequestDto);
    
    // then
    assertEquals(1, savedPost.getLikeCount());
  }
  
  @Test
  void givenCreatedPost_whenUnliked_thenReturnPostWithoutLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);
    
    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);
    postService.unlikePost(likeRequestDto);
    
    // then
    assertEquals(0, savedPost.getLikeCount());
  }
  
}