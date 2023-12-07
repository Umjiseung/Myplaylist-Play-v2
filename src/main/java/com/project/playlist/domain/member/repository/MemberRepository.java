package com.project.playlist.domain.member.repository;

import com.project.playlist.domain.member.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByStudentName(String studentName);
    boolean existsByEmail(String email);

    Optional<Member> findByStudentId(String studentId);

    boolean existsByEmailOrStudentIdOrStudentName(String email, String studentId, String studentName);
}
