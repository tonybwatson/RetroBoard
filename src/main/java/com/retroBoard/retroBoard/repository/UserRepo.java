package com.retroBoard.retroBoard.repository;

import com.retroBoard.retroBoard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
