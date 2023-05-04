package com.retroBoard.retroBoard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepo userRepo) {

        return args -> {
            log.info("Preloading " + userRepo.save(new User("Bilbo Baggins", "burglar")));
            log.info("Preloading " + userRepo.save(new User("Frodo Baggins", "thief")));
        };
    }
}