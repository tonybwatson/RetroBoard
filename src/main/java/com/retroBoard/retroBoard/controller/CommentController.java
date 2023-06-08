package com.retroBoard.retroBoard.controller;

import com.retroBoard.retroBoard.CommentModelAssembler;
import com.retroBoard.retroBoard.exception.CommentNotFoundException;
import com.retroBoard.retroBoard.model.Comment;
import com.retroBoard.retroBoard.repository.CommentRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CommentController {

    private final CommentRepo commentRepo;
    private CommentModelAssembler commentModelAssembler;


    public CommentController(CommentRepo commentRepo, CommentModelAssembler commentModelAssembler) {
        this.commentRepo = commentRepo;
        this.commentModelAssembler = commentModelAssembler;
    }

    @GetMapping("/comments/{id}")
    public EntityModel<Comment> getComment(@PathVariable int id) {
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new CommentNotFoundException(id));

        return EntityModel.of(comment,
                linkTo(methodOn(CommentController.class).getComment(id)).withSelfRel(),
                linkTo(methodOn(CommentController.class).getAllComments()).withRel("comments"));
    }

    @GetMapping("/comments")
    public CollectionModel<EntityModel<Comment>> getAllComments() {
        List<EntityModel<Comment>> comments = commentRepo.findAll().stream()
                .map(comment -> EntityModel.of(comment,
                        linkTo(methodOn(CommentController.class).getComment(comment.getId())).withSelfRel(),
                        linkTo(methodOn(CommentController.class).getAllComments()).withRel("comments")))
                .collect(Collectors.toList());

        return CollectionModel.of(comments, linkTo(methodOn(CommentController.class).getAllComments()).withSelfRel());
    }

}
