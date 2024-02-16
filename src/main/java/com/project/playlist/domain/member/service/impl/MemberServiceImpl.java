package com.project.playlist.domain.member.service.impl;

import com.project.playlist.domain.member.data.dto.request.UpdatePasswordRequest;
import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.exception.MemberNotFoundException;
import com.project.playlist.domain.member.exception.MemberNotSameException;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import com.project.playlist.global.member.MemberUtils;
import com.project.playlist.global.playlist.PlayListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PlayListUtils playListUtils;
    private final MemberUtils memberUtils;
    private final PlayListRepository playListRepository;

    @Transactional(readOnly = true)
    public MemberResponse myMemberInfo(String studentId) {
        Member user = memberUtils.getCurrentMember();
        Member userInfo = memberRepository.findByStudentId(studentId)
                .orElseThrow(MemberNotFoundException::new);

        if (user != userInfo) {
            throw new MemberNotSameException();
        }

        return MemberResponse.of(user);
    }

    @Transactional(readOnly = true)
    public List<MyPlaylistGetsResponse> getMyPlaylist() {
        Member member = memberUtils.getCurrentMember();
        return playListUtils.findPlaylistsByUserInfo(member);
    }

    @Transactional(readOnly = true)
    public List<MyPlaylistGetsResponse> getMyPlaylistCategory(Category category) {
        Member member = memberUtils.getCurrentMember();
        List<PlayList> myPlayLists = playListRepository.findPlayListByMemberAndCategory(member, category);
        List<MyPlaylistGetsResponse> responses = new ArrayList<>();

        for (PlayList playList: myPlayLists) {
            responses.add(new MyPlaylistGetsResponse(
                    playList.getId(),
                    playList.getMember(),
                    playList.getMusicName(),
                    playList.getMusicURL(),
                    playList.getMusicContent(),
                    playList.getCategory(),
                    playList.getDate()
            ));
        }
        return responses;
    }

}