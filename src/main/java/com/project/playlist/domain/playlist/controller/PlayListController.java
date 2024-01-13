package com.project.playlist.domain.playlist.controller;

import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListUpdateResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
import com.project.playlist.domain.playlist.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlayListController {

    private final PlayListService playListService;

    @PostMapping
    public ResponseEntity<Void> musicWrite(@Valid @RequestBody PlayListWriteRequest writeRequest) {
        playListService.playListWrite(writeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlayListGetsResponse>> musicAllGets() {
        playListService.playlistAllGets();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<PlayListGetsResponse>> musicOfGets(@PathVariable("category") Category category) {
        return new ResponseEntity<>(playListService.playListOfGets(category),HttpStatus.OK);
    }

    @GetMapping("/{category}/{id}")
    public ResponseEntity<PlayListInfoResponse> musicGet(@PathVariable("category") Category category,@PathVariable("id") Long id) {
        return new ResponseEntity<>(playListService.playListGet(id,category),HttpStatus.OK);
    }

    @GetMapping("/playlist")
    public ResponseEntity<PlayListGetsResponse> musicGetsOfDate(@RequestParam String date) {
        playListService.playlistGetsDate(date);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> musicDelete(@PathVariable("id") Long id) {
        playListService.playListDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> musicUpdate(@PathVariable("id") Long id, @Valid @RequestBody PlayListUpdateRequest updateRequest) {
        playListService.playListUpdate(id,updateRequest);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }



}
