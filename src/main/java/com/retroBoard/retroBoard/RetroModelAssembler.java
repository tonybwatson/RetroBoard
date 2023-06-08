package com.retroBoard.retroBoard;

import com.retroBoard.retroBoard.controller.RetroController;
import com.retroBoard.retroBoard.model.Retro;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RetroModelAssembler implements RepresentationModelAssembler<Retro, EntityModel<Retro>> {

    @Override
    public EntityModel<Retro> toModel(Retro retro) {
        return EntityModel.of(retro, linkTo(methodOn(RetroController.class).getRetro(retro.getId())).withSelfRel(),linkTo(methodOn(RetroController.class).getAllRetros()).withRel("retros"));
    }

}
