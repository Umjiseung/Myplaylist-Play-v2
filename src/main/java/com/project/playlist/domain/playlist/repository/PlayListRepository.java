package com.project.playlist.domain.playlist.repository;

import com.project.playlist.domain.playlist.data.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<PlayList,Long> {
}
