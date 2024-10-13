package com.leilao.controller;

import com.leilao.entity.Leilao;
import com.leilao.service.LeilaoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/leiloes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeilaoController {

    @Inject
    private LeilaoService leilaoService;

    private Response createResponseFromOptional(Optional<Leilao> optional) {
        //return optional.isPresent() ? Response.ok(optional.get()) : Response.status(404).entity(optional).build();
        return optional.isPresent() ? Response.ok(optional.get()).build(): Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarLeilaoPorId(@PathParam("id") Long id) {
        return createResponseFromOptional(leilaoService.buscarPorId(id));
    }

    @PUT
    @Path("/{id}")
    public Response atualizarLeilao(@PathParam("id") Long id, Leilao leilao) {
        return createResponseFromOptional(leilaoService.atualizarLeilao(id, leilao));
    }

    @DELETE
@Path("/{id}")
public Response removerLeilao(@PathParam("id") Long id) {
    if (leilaoService.removerLeilao(id)) {
        return Response.noContent().build();
    } else {
        return Response.status(404).build();
    }
}
}

