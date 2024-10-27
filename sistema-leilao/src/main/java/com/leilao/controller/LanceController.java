package com.leilao.controller;

import com.leilao.entity.Lance;
import com.leilao.service.LanceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/lances")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LanceController {

    @Inject
    LanceService lanceService;

    @POST
    @Path("/cadastrar")
    public Response cadastrarLance(Lance lance) {
        Lance novoLance = lanceService.cadastrarLance(lance);
        return Response.status(Response.Status.CREATED).entity(novoLance).build();
    }

    @GET
    public List<Lance> listarTodos() {
        return lanceService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Lance lance = lanceService.buscarPorId(id);
        if (lance == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lance).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLance(@PathParam("id") Long id) {
        lanceService.excluirLance(id);
        return Response.noContent().build();
    }
}
