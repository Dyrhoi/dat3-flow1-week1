/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BankCustomer;
import facades.BankCustomerFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Windowshoi
 */
@Path("bankcustomer")
public class BankcustomerResource {
    
    private static final BankCustomerFacade CF = BankCustomerFacade.getInstance(EMF_Creator.createEntityManagerFactory());
       
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BankcustomerResource
     */
    public BankcustomerResource() {
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        return Response.ok(GSON.toJson(CF.get(id))).build();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(GSON.toJson(CF.getAll())).build();
    }
    
    @GET
    @Path("/populate")
    public Response populate() {
        if(CF.getAll().size() == 0) {
            CF.add(new BankCustomer("Martin", "Jørgensen", "AX22V", 60000, 1, "Scary User"));
            CF.add(new BankCustomer("Christian", "Skovsen", "AWP47", 40000, 2, "Friendly User"));
            CF.add(new BankCustomer("John", "Johansen", "L96V", 250102, 3, "CEO Account"));
            return Response.ok().entity("Populated Database...").build();
        }
        return Response.ok().entity("Database already populated... Ignoring.").build();
    }
}
