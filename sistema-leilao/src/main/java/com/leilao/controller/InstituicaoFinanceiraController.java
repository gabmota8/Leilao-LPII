package com.leilao.controller;

import com.leilao.entity.InstituicaoFinanceira;
import com.leilao.service.InstituicaoFinanceiraService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/instituicoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InstituicaoFinanceiraController {

    @Inject
    InstituicaoFinanceiraService instituicaoFinanceiraService;

    @POST
    @Path("/cadastrar")
    public Response cadastrarInstituicao(InstituicaoFinanceira instituicao) {
        InstituicaoFinanceira novaInstituicao = instituicaoFinanceiraService.cadastrarInstituicao(instituicao);
        return Response.status(Response.Status.CREATED).entity(novaInstituicao).build();
    }

    @GET
    public List<InstituicaoFinanceira> listarTodas() {
        return instituicaoFinanceiraService.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        InstituicaoFinanceira instituicao = instituicaoFinanceiraService.buscarPorId(id);
        if (instituicao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(instituicao).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirInstituicao(@PathParam("id") Long id) {
        instituicaoFinanceiraService.excluirInstituicao(id);
        return Response.noContent().build();
    }
}
