package com.project.playlist.domain.playlist.service;


import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListUpdateResponse;
import com.project.playlist.domain.playlist.data.entity.Category;

import java.util.List;

public interface PlayListService {

    void playListWrite(PlayListWriteRequest writeRequest);
    List<PlayListGetsResponse> playListOfGets(Category category);
    PlayListInfoResponse playListGet(Long id);
    void playListDelete(Long id);

    void playListUpdate(Long id, PlayListUpdateRequest updateRequest);

    List<PlayListGetsResponse> playlistAllGets();

    List<PlayListGetsResponse> playlistGetsDate(String date);
}
