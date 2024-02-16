package com.project.playlist.domain.comment.repository;

import com.project.playlist.domain.comment.data.entiry.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> { }
