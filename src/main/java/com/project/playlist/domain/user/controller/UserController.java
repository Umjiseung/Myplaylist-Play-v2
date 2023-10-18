package com.project.playlist.domain.user.controller;


import com.project.playlist.domain.user.data.dto.response.MyProfileInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<MyProfileInfoResponse> myProfileInfo() {


    }

}
