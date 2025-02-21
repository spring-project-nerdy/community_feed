package org.example.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.fake.FakeObjectFactory;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.application.dto.LikeRequestDto;
import org.example.post.domain.Post;
import org.example.post.domain.content.PostPublicationState;
import org.example.user.application.UserService;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.domain.User;
import org.junit.jupiter.api.Test;

class PostServiceTest {

  private final UserService userService = FakeObjectFactory.getUserService();
  private final PostService postService = FakeObjectFactory.getPostService();

  private final User user = userService.createUser(new CreateUserRequestDto("user1", null));
  private final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

  private final CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(),
      "this is test content", PostPublicationState.PUBLIC);


  @Test
  void givenPostRequestDto_whenCreate_thenReturnPost() {
    // when
    Post savePost = postService.createPost(dto);

    // then
    Post post = postService.getPost(savePost.getId());
    assertEquals(savePost, post);
  }

  @Test
  void givenCreatePost_whenUpdate_thenReturnUpdatePost() {
    // given
    Post savePost = postService.createPost(dto);

    // when
    Post updatePost = postService.updatePost(savePost.getId(), dto);

    // then
    assertEquals(savePost.getId(), updatePost.getId());
    assertEquals(savePost.getAuthor(), updatePost.getAuthor());
    assertEquals(savePost.getContent(), updatePost.getContent());
  }

  @Test
  void givenCreatedPost_whenLiked_thenReturnPostWithLike() {
    // given
    Post savedPost = postService.createPost(dto);

    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);

    // then
    assertEquals(1, savedPost.getLikeCount());
  }

  @Test
  void givenCreatedPost_whenLikedTwice_thenReturnPostWithLike() {
    // given
    Post savedPost = postService.createPost(dto);

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
    Post savedPost = postService.createPost(dto);

    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);
    postService.unlikePost(likeRequestDto);

    // then
    assertEquals(0, savedPost.getLikeCount());
  }

}