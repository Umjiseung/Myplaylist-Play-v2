package com.project.playlist.domain.playlist.repository;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.entity.Category;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList,Long> {
    List<PlayList> findByCategory(Category category);
    PlayList findByCategoryAndId(Category category, Long id);
    List<PlayList> findBoardsByMember(Member member);
    boolean existsByMemberAndMusicName(Member member, String musicName);
}
