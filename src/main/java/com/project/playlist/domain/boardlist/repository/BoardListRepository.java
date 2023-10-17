package com.project.playlist.domain.boardlist.repository;

import com.project.playlist.domain.boardlist.data.entity.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardListRepository extends JpaRepository<BoardList,Long> {
}
