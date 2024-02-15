package com.project.playlist.domain.playlist.service;


import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListUpdateResponse;
import com.project.playlist.domain.playlist.data.entity.Category;

import java.util.List;
import java.util.UUID;

public interface PlayListService {

    void playListWrite(PlayListWriteRequest writeRequest);
    List<PlayListGetsResponse> playListOfGets(Category category);
    PlayListInfoResponse playListGet(UUID id);
    void playListDelete(UUID id);
    void playListUpdate(UUID id, PlayListUpdateRequest updateRequest);
    List<PlayListGetsResponse> playlistAllGets();
}
