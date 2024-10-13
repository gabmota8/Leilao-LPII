package com.leilao.controller;



import com.leilao.entity.Produto;
import com.leilao.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @Inject
    ProdutoService produtoService;

    // Endpoint para listar todos os produtos
    @GET
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    // Endpoint para buscar um produto por ID
    @GET
    @Path("/{id}")
    public Response buscarProdutoPorId(@PathParam("id") Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        return produto.isPresent() ? Response.ok(produto.get()).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    // Endpoint para criar um novo produto
    @POST
    public Response criarProduto(Produto produto) {
        Produto novoProduto = produtoService.criarProduto(produto);
        return Response.status(Response.Status.CREATED).entity(novoProduto).build();
    }

    // Endpoint para atualizar um produto existente
    @PUT
    @Path("/{id}")
    public Response atualizarProduto(@PathParam("id") Long id, Produto produto) {
        Optional<Produto> produtoAtualizado = produtoService.atualizarProduto(id, produto);
        return produtoAtualizado.isPresent() ? Response.ok(produtoAtualizado.get()).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    // Endpoint para remover um produto
    @DELETE
    @Path("/{id}")
    public Response removerProduto(@PathParam("id") Long id) {
        boolean removido = produtoService.removerProduto(id);
        return removido ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
