package org.example.user.application;

import lombok.RequiredArgsConstructor;
import org.example.user.application.dto.CreateUseRequestDto;
import org.example.user.application.dto.GetUserResponseDto;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(CreateUseRequestDto createUseRequestDto) {
        UserInfo info = new UserInfo(createUseRequestDto.name(), createUseRequestDto.profileImageUrl());
        User user = new User(null, info);
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
       return userRepository.findById(userId);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }
}
