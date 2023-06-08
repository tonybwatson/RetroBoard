package com.retroBoard.retroBoard.repository;

import com.retroBoard.retroBoard.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
