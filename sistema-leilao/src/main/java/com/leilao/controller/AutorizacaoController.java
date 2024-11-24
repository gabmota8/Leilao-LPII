package com.leilao.controller;

import com.leilao.entity.Autorizacao;
import com.leilao.service.AutorizacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/autorizacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutorizacaoController {

    @Inject
    AutorizacaoService autorizacaoService;

    @POST
    @Path("/cadastrar")
    public Response cadastrarAutorizacao(Autorizacao autorizacao) {
        Autorizacao novaAutorizacao = autorizacaoService.cadastrarAutorizacao(autorizacao);
        return Response.status(Response.Status.CREATED).entity(novaAutorizacao).build();
    }

    @GET
    public List<Autorizacao> listarTodos() {
        return autorizacaoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Autorizacao autorizacao = autorizacaoService.buscarPorId(id);
        if (autorizacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(autorizacao).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirAutorizacao(@PathParam("id") Long id) {
        autorizacaoService.excluirAutorizacao(id);
        return Response.noContent().build();
    }
}
