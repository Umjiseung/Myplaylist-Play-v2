package com.project.playlist.domain.playlist.controller;

import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlayListController {

    private final PlayListService boardListService;

    @PostMapping("/write")
    public ResponseEntity<Void> write(@RequestBody PlayListWriteRequest writeRequest) {
        boardListService.playListWrite(writeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
