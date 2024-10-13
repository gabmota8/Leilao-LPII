package com.leilao.controller;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {

    public NotFoundException(String message) {
        super(Response.status(404).entity(message).build());
    }
}