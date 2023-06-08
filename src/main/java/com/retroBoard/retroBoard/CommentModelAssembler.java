package com.retroBoard.retroBoard;

import com.retroBoard.retroBoard.controller.CommentController;
import com.retroBoard.retroBoard.model.Comment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommentModelAssembler implements RepresentationModelAssembler<Comment, EntityModel<Comment>> {

    @Override
    public EntityModel<Comment> toModel(Comment comment) {
        return EntityModel.of(comment, linkTo(methodOn(CommentController.class).getComment(comment.getId())).withSelfRel(), linkTo(methodOn(CommentController.class).getAllComments()).withRel("comments"));
    }

}
