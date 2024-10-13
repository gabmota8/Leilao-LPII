package com.leilao.controller;

import com.leilao.entity.Lance;
import com.leilao.service.LanceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/lances")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LanceController {

    @Inject
    LanceService lanceService;

    @POST
    public Response registrarLance(Lance lance) {
        Optional<Lance> novoLance = lanceService.registrarLance(lance);
        return novoLance.isPresent() 
            ? Response.status(Response.Status.CREATED).entity(novoLance.get()).build() 
            : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/produto/{produtoId}")
    public Response obterHistoricoDeLances(@PathParam("produtoId") Long produtoId) {
        List<Lance> lances = lanceService.obterHistoricoDeLances(produtoId);
        return Response.ok(lances).build();
    }
}
