package com.retroBoard.retroBoard.controller;

import com.retroBoard.retroBoard.RetroModelAssembler;
import com.retroBoard.retroBoard.exception.RetroNotFoundException;
import com.retroBoard.retroBoard.model.Retro;
import com.retroBoard.retroBoard.repository.RetroRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RetroController {

    private RetroRepo retroRepo;
    RetroModelAssembler retroModelAssembler = new RetroModelAssembler();

    RetroController(RetroRepo retroRepo, RetroModelAssembler retroModelAssembler) {
        this.retroRepo = retroRepo;
        this.retroModelAssembler = retroModelAssembler;
    }

    public int getRetro(int id) {
        return id;
    }

    public Class<?> getAllRetros() {
        return null;
    }

    @GetMapping("/retros/{id}")
    public EntityModel<Retro> getAllRetros(@PathVariable int id) {
        Retro retro = retroRepo.findById(id).orElseThrow(() -> new RetroNotFoundException(id));
        return EntityModel.of(retro,
                linkTo(methodOn(RetroController.class).getRetro(id)).withSelfRel(),
                linkTo(methodOn(RetroController.class).getAllRetros()).withRel("retros"));
    }

//    @GetMapping("/retros")
//    public CollectionModel<EntityModel<Retro>> getRetros(){
//        List<EntityModel<Retro>> retros=retroRepo.findBy();
//        ((findBy something like the team id?
//        should only look for retros connected to current user's team))
//    }

}
