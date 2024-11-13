package com.leilao.controller;

import com.leilao.entity.Lance;
import com.leilao.service.LanceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/lances")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LanceController {

    @Inject
    LanceService lanceService;

    @POST
    @Path("/cadastrar")
    public Response registrarLance(Lance lance) {
        Optional<Lance> novoLance = lanceService.registrarLance(lance);
        if (novoLance.isPresent()) {
            return Response.status(Response.Status.CREATED).entity(novoLance.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Lance inválido ou produto não encontrado").build();
    }

    @GET
    @Path("/listar")
    public List<Lance> listarTodos() {
        return lanceService.obterHistoricoDeLances(null); // Implementação provisória
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Optional<Lance> lance = lanceService.obterHistoricoDeLances(id).stream().findFirst(); // Implementação provisória
        if (lance.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lance.get()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLance(@PathParam("id") Long id) {
        // Implemente a lógica de exclusão no LanceService
        // lanceService.excluirLance(id);
        return Response.noContent().build();
    }
}
