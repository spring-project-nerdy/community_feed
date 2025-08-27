package org.example.post.application;

import org.example.fake.FakeObjectFactory;
import org.example.post.application.dto.CreateCommentRequestDto;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.domain.Post;
import org.example.post.domain.content.PostPublicationState;
import org.example.user.application.UserService;
import org.example.user.application.dto.CreateUseRequestDto;
import org.example.user.domain.User;

public class PostApplicationTestTemplate {
    final PostService postService = FakeObjectFactory.getPostService();
    final UserService userService = FakeObjectFactory.getUserService();

    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUseRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUseRequestDto("user1", null));

    final CreatePostRequestDto createPostRequestDto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);

    final Post post = postService.createPost(createPostRequestDto);

    final String commentContentText = "this is test content";
    final CreateCommentRequestDto createCommentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), commentContentText);
}
