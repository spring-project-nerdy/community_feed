package org.example.user.application;

import org.example.user.application.dto.CreateUseRequestDto;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;

import java.util.IllformedLocaleException;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUseRequestDto createUseRequestDto) {
        UserInfo info = new UserInfo(createUseRequestDto.name(), createUseRequestDto.profileImageUrl());
        User user = new User(null, info);
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
       return userRepository.findById(userId).orElseThrow(IllformedLocaleException::new);
    }
}
