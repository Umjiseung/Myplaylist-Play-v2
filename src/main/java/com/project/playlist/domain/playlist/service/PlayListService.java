package com.project.playlist.domain.playlist.service;


import com.project.playlist.domain.playlist.data.dto.request.PlayListDeleteRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListWriteResponse;

import java.util.List;

public interface PlayListService {

    PlayListWriteResponse playListWrite(PlayListWriteRequest writeRequest);
    List<PlayListGetsResponse> playListGets();

    List<PlayListGetsResponse> playListOfGets();
    PlayListInfoResponse playListGet(Long id);
    void playListDelete(Long id, PlayListDeleteRequest deleteRequest);
    void playListUpdate(Long id, PlayListUpdateRequest updateRequest);

}
