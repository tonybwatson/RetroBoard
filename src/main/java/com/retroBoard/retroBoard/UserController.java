package com.retroBoard.retroBoard;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserRepo userRepo;

    UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = userRepo.findAll().stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).all()).withRel("users")))
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userRepo.save(newUser);
    }

    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return EntityModel.of(user, //
                linkTo(methodOn(UserController.class).one(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
    }

}
