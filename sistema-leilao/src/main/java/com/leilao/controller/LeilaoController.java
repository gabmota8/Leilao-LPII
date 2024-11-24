package com.leilao.controller;

import com.leilao.entity.Leilao;
import com.leilao.service.LeilaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/leiloes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LeilaoController {

    @Inject
    LeilaoService leilaoService;

    @POST
@Path("/cadastrar")
public Response cadastrarLeilao(Leilao leilao) {
    System.out.println("Recebendo requisição para cadastrar leilão: " + leilao);
    Leilao novoLeilao = leilaoService.cadastrarLeilao(leilao);
    return Response.status(Response.Status.CREATED).entity(novoLeilao).build();
}

    @GET
    public List<Leilao> listarTodos() {
        return leilaoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Leilao leilao = leilaoService.buscarPorId(id);
        if (leilao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(leilao).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLeilao(@PathParam("id") Long id) {
        leilaoService.excluirLeilao(id);
        return Response.noContent().build();
    }
}
