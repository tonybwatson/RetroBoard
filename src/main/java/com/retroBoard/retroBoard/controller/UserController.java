package com.retroBoard.retroBoard.controller;

import com.retroBoard.retroBoard.UserModelAssembler;
import com.retroBoard.retroBoard.exception.UserNotFoundException;
import com.retroBoard.retroBoard.repository.UserRepo;
import com.retroBoard.retroBoard.model.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserRepo userRepo;
    private final UserModelAssembler userModelAssembler;

    UserController(UserRepo userRepo, UserModelAssembler userModelAssembler) {
        this.userRepo = userRepo;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> getAllUsers() {
        List<EntityModel<User>> users = userRepo.findAll().stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")))
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }

    @PostMapping("/users")
    ResponseEntity<EntityModel<User>> createNewUser(@RequestBody User newUser) {

        EntityModel<User> entityModel = userModelAssembler.toModel(userRepo.save(newUser));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return EntityModel.of(user, //
                linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable int id) {
        userRepo.deleteById(id);
    }

}
