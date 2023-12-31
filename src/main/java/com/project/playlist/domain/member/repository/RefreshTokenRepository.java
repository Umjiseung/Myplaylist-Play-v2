package com.project.playlist.domain.member.repository;

import com.project.playlist.domain.member.data.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

}
