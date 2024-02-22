package com.project.playlist.domain.member.repository;

import com.project.playlist.domain.member.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

}
