/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Windowshoi
 */
public class CustomerFacade {
    
    EntityManagerFactory emf;
    
    public CustomerFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Customer> getAllCustomers() {
        EntityManager em = getEntityManager();
        TypedQuery q = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return q.getResultList();
    }
    
    public Customer createCustomer(Customer c) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        return c;
    }
    
    public static void main(String[] args) {
        CustomerFacade cf = new CustomerFacade(Persistence.createEntityManagerFactory("pu"));
        
        cf.createCustomer(new Customer("Jens", "+459999999"));
        
        List<Customer> customers = cf.getAllCustomers();
        for(Customer c : customers) {
            System.out.println(c.getName());
        }
        
    }
    
}
