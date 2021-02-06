/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
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
@Path("employee")
public class EmployeeResource {
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private EmployeeFacade ef = EmployeeFacade.getInstance(EMF_Creator.createEntityManagerFactory());

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<EmployeeDTO> employees = new ArrayList();
        ef.getAll().forEach(e -> employees.add(new EmployeeDTO(e)));
        
        return Response.ok().entity(gson.toJson(employees)).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        EmployeeDTO edto = new EmployeeDTO(ef.get(id));
        
        return Response.ok().entity(gson.toJson(edto)).build();
    }
    
    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHighestPaid() {
        List<EmployeeDTO> employees = new ArrayList();
        ef.getWithHighestSalary().forEach(e -> employees.add(new EmployeeDTO(e)));
        
        return Response.ok().entity(gson.toJson(employees)).build();
    }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name) {
        List<EmployeeDTO> employees = new ArrayList();
        ef.getByName(name).forEach(e -> employees.add(new EmployeeDTO(e)));
        
        return Response.ok().entity(gson.toJson(employees)).build();
    }
    
    @GET
    @Path("/populate")
    public Response populate() {
        if(ef.getAll().size() == 0) {
            ef.create(new Employee("John Martin", "Gurrelynge 5", 1000000));
            ef.create(new Employee("Martin Skov", "Brostræd 1", 10000));
            ef.create(new Employee("Christian Klovn", "Lungevej 12", 1000005));
            ef.create(new Employee("Lunge Mans", "Weedistreet 52a", 100));
            ef.create(new Employee("Mogens Bo", "Gurrelynge 2", 15000));
            ef.create(new Employee("Lars Martin", "Gurrelynge 5", 1000000));
            return Response.ok().entity("Populated Database...").build();
        }
        return Response.ok().entity("Database already populated... Ignoring.").build();
    }
}
