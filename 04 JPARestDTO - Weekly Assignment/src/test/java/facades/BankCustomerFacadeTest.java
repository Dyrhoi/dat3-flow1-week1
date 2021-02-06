/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BankCustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;


/**
 *
 * @author Windowshoi
 */
public class BankCustomerFacadeTest {
    
    private static EntityManagerFactory emf;
    private static BankCustomerFacade cf;
    
    public BankCustomerFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        cf = BankCustomerFacade.getInstance(emf);
    }
    
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM BankCustomer").executeUpdate();
            em.getTransaction().commit();
            
            cf.add(new BankCustomer("Martin", "Jørgensen", "AX22V", 60000, 1, "Scary User"));
        } finally {
            em.close();
        }
    }
    

    /**
     * Test of get method, of class BankCustomerFacade.
     */
    @Test
    public void testGet() {
        BankCustomer e = cf.add(new BankCustomer("John", "Test", "AX22V", 60000, 1, "Scary User"));
        BankCustomerDTO efound = cf.get(e.getId());
        assertEquals(e.getId(), efound.getId());
    }

    /**
     * Test of getByLastname method, of class BankCustomerFacade.
     */
    @Test
    public void testGetByLastname() {
        BankCustomerDTO e = cf.getByLastname("Jørgensen").get(0);
        assertEquals("Jørgensen", e.getLastName());
    }

    /**
     * Test of add method, of class BankCustomerFacade.
     */
    @Test
    public void testAdd() {      
        
        assertEquals(1, cf.getAll().size());
        
        cf.add(new BankCustomer("John", "Test", "AX22V", 60000, 1, "Scary User"));
        
        assertEquals(2, cf.getAll().size());
    }

    /**
     * Test of getAll method, of class BankCustomerFacade.
     */
    @Test
    public void testGetAll() {
        cf.add(new BankCustomer("John", "Test", "AX22V", 60000, 1, "Scary User"));
        cf.add(new BankCustomer("John", "Test", "AX22V", 60000, 1, "Scary User"));
        
        int count = cf.getAll().size();
        assertEquals(3, count);
    }
    
}
