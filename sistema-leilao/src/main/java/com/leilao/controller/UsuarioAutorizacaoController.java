package com.leilao.controller;

import com.leilao.entity.UsuarioAutorizacao;
import com.leilao.service.UsuarioAutorizacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuarios-autorizacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioAutorizacaoController {

    @Inject
    UsuarioAutorizacaoService usuarioAutorizacaoService;

    @POST
    @Path("/cadastrar")
    public Response cadastrarUsuarioAutorizacao(UsuarioAutorizacao usuarioAutorizacao) {
        UsuarioAutorizacao novaRelacao = usuarioAutorizacaoService.cadastrarUsuarioAutorizacao(usuarioAutorizacao);
        return Response.status(Response.Status.CREATED).entity(novaRelacao).build();
    }

    @GET
    public List<UsuarioAutorizacao> listarTodos() {
        return usuarioAutorizacaoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        UsuarioAutorizacao usuarioAutorizacao = usuarioAutorizacaoService.buscarPorId(id);
        if (usuarioAutorizacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(usuarioAutorizacao).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirUsuarioAutorizacao(@PathParam("id") Long id) {
        usuarioAutorizacaoService.excluirUsuarioAutorizacao(id);
        return Response.noContent().build();
    }
}
