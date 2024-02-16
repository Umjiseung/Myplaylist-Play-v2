package com.project.playlist.domain.playlist.controller;

import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
import com.project.playlist.domain.playlist.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
        List<PlayListGetsResponse> result = playListService.playlistAllGets();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<PlayListGetsResponse>> musicCategoryOfGets(@PathVariable("category") Category category) {
        List<PlayListGetsResponse> result = playListService.playListOfGets(category);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayListInfoResponse> musicInfo(@PathVariable("id") UUID id) {
        PlayListInfoResponse result = playListService.playListGet(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> musicDelete(@PathVariable("id") UUID id) {
        playListService.playListDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> musicUpdate(@PathVariable("id") UUID id, @Valid @RequestBody PlayListUpdateRequest updateRequest) {
        playListService.playListUpdate(id,updateRequest);
        return ResponseEntity.noContent().build();
    }
}
