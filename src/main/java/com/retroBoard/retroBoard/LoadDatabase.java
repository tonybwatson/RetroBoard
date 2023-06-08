package com.retroBoard.retroBoard;

import com.retroBoard.retroBoard.model.Comment;
import com.retroBoard.retroBoard.model.User;
import com.retroBoard.retroBoard.repository.CommentRepo;
import com.retroBoard.retroBoard.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepo userRepo, CommentRepo commentRepo) {

        return args -> {
            log.info("Preloading " + userRepo.save(new User("Bilbo Baggins", "password")));
            log.info("Preloading " + userRepo.save(new User("Frodo Baggins", "hunter2")));
            log.info("Preloading " + commentRepo.save(new Comment("This is a comment")));
            log.info("Preloading " + commentRepo.save(new Comment("This is another comment")));

        };
    }

}