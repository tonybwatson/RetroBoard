package com.retroBoard.retroBoard;

import com.retroBoard.retroBoard.controller.UserController;
import com.retroBoard.retroBoard.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user, linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
    }
}
