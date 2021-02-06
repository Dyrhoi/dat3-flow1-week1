/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dyrhoi.github.customerjpademo.facade;

import dyrhoi.github.customerjpademo.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Windowshoi
 */
public class CustomerFacade {
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {}
    
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
       if (instance == null) {
           emf = _emf;
           instance = new CustomerFacade();
       }
       return instance;
   }
    
    public Customer addCustomer(String firstname, String lastname) {
        Customer customer = new Customer(firstname, lastname);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }

    public Customer findById(int id){
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class,id);
            return customer;
        } finally {
            em.close();
        }
    }
    
    public List<Customer> findByLastName(String lastname){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("Select c from Customer c WHERE c.lastname = :lastname", Customer.class);
            query.setParameter("lastname", lastname);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select COUNT(c) from Customer c", Customer.class);
            return ((Long) query.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("Select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
