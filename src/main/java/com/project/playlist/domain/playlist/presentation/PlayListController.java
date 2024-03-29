package com.project.playlist.domain.playlist.presentation;

import com.project.playlist.domain.playlist.presentation.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.presentation.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.presentation.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.presentation.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.enums.Category;
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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PlayListGetsResponse>> musicAllGets() {
        return ResponseEntity.status(HttpStatus.OK).body(playListService.playlistAllGets());
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<PlayListGetsResponse>> musicCategoryOfGets(@PathVariable("category") Category category) {
        return ResponseEntity.status(HttpStatus.OK).body(playListService.playListOfGets(category));
    }

    @GetMapping("info/{id}")
    public ResponseEntity<PlayListInfoResponse> musicInfo(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(playListService.playListGet(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> musicDelete(@PathVariable("id") UUID id) {
        playListService.playListDelete(id);
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> musicUpdate(@PathVariable("id") UUID id, @Valid @RequestBody PlayListUpdateRequest updateRequest) {
        playListService.playListUpdate(id,updateRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
