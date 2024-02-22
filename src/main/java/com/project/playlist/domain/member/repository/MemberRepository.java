package com.project.playlist.domain.member.repository;

import com.project.playlist.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByStudentId(String studentId);
    boolean existsByStudentIdAndEmail(String studentId, String email);
    Optional<Member> findById(UUID id);
}
