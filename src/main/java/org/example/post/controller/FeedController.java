package org.example.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.common.controller.Response;
import org.example.post.controller.dto.GetPostContentResponseDto;
import org.example.post.repository.post_queue.UserPostQueueQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable(name = "userId") Long userId, Long lastPostId){
        List<GetPostContentResponseDto> contentResponse = queueQueryRepository.getContentResponse(userId, lastPostId);
        return Response.ok(contentResponse);
    }
}
