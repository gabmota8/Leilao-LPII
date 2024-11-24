package com.leilao.controller;

import com.leilao.entity.Produto;
import com.leilao.entity.ProdutoNotebook;
import com.leilao.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @Inject
    ProdutoService produtoService;

    @POST
@Path("/cadastrar")
public Response cadastrarProduto(Produto produto) {
    System.out.println("Recebendo requisição para cadastrar produto: " + produto);
    if (produto instanceof ProdutoNotebook) {
        ProdutoNotebook notebook = (ProdutoNotebook) produto;
        System.out.println("Armazenamento: " + notebook.getArmazenamento());
    }
    Produto novoProduto = produtoService.cadastrarProduto(produto);
    return Response.status(Response.Status.CREATED).entity(novoProduto).build();
}


    @GET
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(produto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirProduto(@PathParam("id") Long id) {
        produtoService.excluirProduto(id);
        return Response.noContent().build();
    }
}
