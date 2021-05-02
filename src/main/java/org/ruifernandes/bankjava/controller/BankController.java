package org.ruifernandes.bankjava.controller;

import org.ruifernandes.bankjava.model.jpa.Transaction;
import org.ruifernandes.bankjava.service.BankService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BankController {

    @Inject
    BankService bankService;

    @POST
    @Path("client/new/{balance}")
    public Response newClient(@PathParam("balance") final Integer balance) {
        return Response
            .accepted(bankService.newClient(balance))
            .build();
    }

    @POST
    @Path("transaction")
    public Response newTransaction(final Transaction transaction) {
        return Response.accepted(bankService.newTransaction(transaction)).build();
    }

    @GET
    @Path("client/{id}/balance")
    public Response getBalance(@PathParam("id") Integer id) {
        return Response.ok(bankService.getBalance(id)).build();
    }
}
