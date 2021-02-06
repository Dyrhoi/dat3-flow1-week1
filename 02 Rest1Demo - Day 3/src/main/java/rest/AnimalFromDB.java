/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Windowshoi
 */
@Path("animals_db")
public class AnimalFromDB {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }
    
    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
      EntityManager em = emf.createEntityManager();
      try{
          TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
          List<Animal> animals = query.getResultList();
          return new Gson().toJson(animals);
       } finally {
              em.close();
       }
    }
    
    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimal(@PathParam("id") int id) {
        long idLong = id;
        EntityManager em = emf.createEntityManager();
        try {
            Animal animal = em.find(Animal.class,idLong);
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }
    
    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try{
          TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type = :type", Animal.class);
          query.setParameter("type", type);
          List<Animal> animals = query.getResultList();
          return new Gson().toJson(animals);
       } finally {
              em.close();
       }
    }
    
    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal() {
        EntityManager em = emf.createEntityManager();
        /*This should be the smart way (?), but RAND() not part of JPL?*/
        
        /*
        try{
          TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a ORDER BY RAND() LIMIT 1", Animal.class);
          Animal animal = query.getSingleResult();
          return new Gson().toJson(animal);
        } finally {
               em.close();
        }
        
        */
        
        try{
          TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
          List<Animal> animals = query.getResultList();
          Animal animal = animals.get(new Random().nextInt(animals.size()));
          return new Gson().toJson(animal);
        } finally {
               em.close();
        }
    }
}
