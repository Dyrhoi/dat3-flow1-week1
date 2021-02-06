/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
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
public class EmployeeFacadeTest {
    
    private static EntityManagerFactory emf;
    private static EmployeeFacade ef;
    
    public EmployeeFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        ef = EmployeeFacade.getInstance(emf);
    }
    
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Employee").executeUpdate();
            em.getTransaction().commit();
            
            ef.create(new Employee("John Martin", "Gurrelynge 2", 10000));
        } finally {
            em.close();
        }
    }

    /**
     * Test of get method, of class EmployeeFacade.
     */
    @Test
    public void testGet() {
        Employee e = ef.create(new Employee("Eirk Martin", "Gurrelynge 22", 10000));
        Employee efound = ef.get(e.getId());
        assertEquals(e.getId(), efound.getId());
    }

    /**
     * Test of getByName method, of class EmployeeFacade.
     */
    @Test
    public void testGetByName() {
        Employee e = ef.getByName("John Martin").get(0);
        assertEquals("John Martin", e.getName());
    }

    /**
     * Test of getAll method, of class EmployeeFacade.
     */
    @Test
    public void testGetAll() {
        ef.create(new Employee("John Ludvig", "Gurrelynge 20", 1000000));
        ef.create(new Employee("John Mogens", "Gurrelynge 5", 10000));
        
        int count = ef.getAll().size();
        assertEquals(3, count);
    }

    /**
     * Test of getWithHighestSalary method, of class EmployeeFacade.
     */
    @Test
    public void testGetWithHighestSalary() {
        ef.create(new Employee("John Ludvig", "Gurrelynge 20", 1000000));
        Employee e = ef.getWithHighestSalary().get(0);
        
        assertEquals("John Ludvig", e.getName());
    }

    /**
     * Test of create method, of class EmployeeFacade.
     */
    @Test
    public void testCreate() {
        assertEquals(1, ef.getAll().size());
        ef.create(new Employee("John Ludvig", "Gurrelynge 20", 1000000));
        assertEquals(2, ef.getAll().size());
    }
    
}
