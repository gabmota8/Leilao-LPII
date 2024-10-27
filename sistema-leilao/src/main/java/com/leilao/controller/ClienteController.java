package com.leilao.controller;

import com.leilao.entity.Cliente;
import com.leilao.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @POST
    @Path("/cadastrar")
    public Response cadastrarCliente(Cliente cliente) {
        clienteService.cadastrarCliente(cliente);
        return Response.status(Response.Status.CREATED).entity(cliente).build();
    }

    @GET
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cliente).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirCliente(@PathParam("id") Long id) {
        clienteService.excluirCliente(id);
        return Response.noContent().build();
    }
}
