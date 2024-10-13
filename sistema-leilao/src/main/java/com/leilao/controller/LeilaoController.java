package com.leilao.controller;

import com.leilao.entity.Leilao;
import com.leilao.service.LeilaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/leiloes")
@Produces(MediaType.APPLICATION_JSON)
public class LeilaoController {

    @Inject
    LeilaoService leilaoService;

    @GET
    @Path("/{id}")
    public Response getLeilaoById(@PathParam("id") Long id) {
        Optional<Leilao> leilao = leilaoService.buscarPorId(id);
        return leilao.map(Response::ok).orElseGet(() -> Response.status(Response.Status.NOT_FOUND)).build();
    }
}
