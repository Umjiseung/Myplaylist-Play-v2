package com.project.playlist.domain.playlist.service;


import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.request.PlayListDeleteRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListWriteResponse;
import com.project.playlist.domain.playlist.data.entity.Category;

import java.util.List;

public interface PlayListService {

    PlayListWriteResponse playListWrite(Member member, PlayListWriteRequest writeRequest);
    List<PlayListGetsResponse> playListOfGets(Member member,Category category);
    PlayListInfoResponse playListGet(Long id,Member member);
    void playListDelete(Member member, Long id, PlayListDeleteRequest deleteRequest);
    void playListUpdate(Member member, Long id,PlayListUpdateRequest updateRequest);

}
