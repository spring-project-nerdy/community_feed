package org.example.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.common.ui.Response;
import org.example.post.application.CommentService;
import org.example.post.application.dto.CreateCommentRequestDto;
import org.example.post.application.dto.LikeRequestDto;
import org.example.post.application.dto.UpdateCommentRequestDto;
import org.example.post.domain.comment.Comment;
import org.example.post.repository.CommentQueryRepositoryImpl;
import org.example.post.ui.dto.GetContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
  
  private final CommentService commentService;
  private final CommentQueryRepositoryImpl commentQueryRepository;
  
  @PostMapping
  public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
    Comment comment = commentService.createComment(dto);
    return Response.ok(comment.getId());
  }
  
  @PatchMapping("/{commentId}")
  public Response<Long> updateComment(@PathVariable(name = "commentId") Long commentId, @RequestBody
  UpdateCommentRequestDto dto) {
    Comment comment = commentService.updateComment(commentId, dto);
    return Response.ok(comment.getId());
  }
  
  @PostMapping("/like")
  public Response<Void> likeComment(@RequestBody LikeRequestDto dto) {
    commentService.likeComment(dto);
    return Response.ok(null);
  }
  
  @PostMapping("/unlike")
  public Response<Void> ulikeComment(@RequestBody LikeRequestDto dto) {
    commentService.unLikeComment(dto);
    return Response.ok(null);
  }
  
  @GetMapping("/post/{postId}")
  public Response<List<GetContentResponseDto>> getCommentList(@PathVariable(name = "postId") Long postId, Long userId, Long lastCommentId) {
    List<GetContentResponseDto> commentList = commentQueryRepository.getCommentList(postId, userId, lastCommentId);
    return Response.ok(commentList);
  }
}
