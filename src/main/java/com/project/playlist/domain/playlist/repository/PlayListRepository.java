package com.project.playlist.domain.playlist.repository;

import com.project.playlist.domain.member.Member;
import com.project.playlist.domain.playlist.enums.Category;
import com.project.playlist.domain.playlist.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList,Long> {
    List<PlayList> findByCategory(Category category);
    PlayList findByIdOrIdNull(UUID id);
    List<PlayList> findPlayListsByMember(Member member);
    boolean existsByMemberAndMusicName(Member member, String musicName);
    void deleteById(UUID id);
    List<PlayList> findPlayListByMemberAndCategory(Member member, Category category);
}
