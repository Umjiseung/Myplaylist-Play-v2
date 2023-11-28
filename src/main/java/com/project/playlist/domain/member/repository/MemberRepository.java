package com.project.playlist.domain.member.repository;

import com.project.playlist.domain.member.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByStudentName(String studentName);
    boolean existsByEmail(String email);

    Optional<Member> findByStudentName(String studentName);

    boolean existsByEmailAndStudentIdAndStudentName(String email, String studentId, String studentName);
}
