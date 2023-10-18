package com.project.playlist.domain.user.service;

import com.project.playlist.domain.user.data.entity.User;
import com.project.playlist.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;




}
