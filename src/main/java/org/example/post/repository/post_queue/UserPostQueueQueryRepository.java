package org.example.post.repository.post_queue;

import org.example.post.controller.dto.GetContentResponseDto;
import org.example.post.controller.dto.GetPostContentResponseDto;

import java.util.List;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId);
}
