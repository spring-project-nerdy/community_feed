package org.example.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.common.controller.Response;
import org.example.user.application.UserRelationService;
import org.example.user.application.dto.FollowUserRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {

    private final UserRelationService relationService;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto) {
        relationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto) {
        relationService.unfollow(dto);
        return Response.ok(null);
    }
}
