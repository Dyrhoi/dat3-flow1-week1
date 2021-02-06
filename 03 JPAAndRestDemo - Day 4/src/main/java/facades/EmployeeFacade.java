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
import javax.persistence.TypedQuery;

/**
 *
 * @author Windowshoi
 */
public class EmployeeFacade {
    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;
    
    public static EmployeeFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
    public Employee get(long id) {
        return emf.createEntityManager().find(Employee.class, id);
    }
    
    public List<Employee> getByName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
        q.setParameter("name", name);
        
        return q.getResultList();
    }
    
    public List<Employee> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e", Employee.class);
        
        return q.getResultList();
    }
    
    public List<Employee> getWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(ee.salary) FROM Employee ee)", Employee.class);
        
        return q.getResultList();
    }
    
    public Employee create(Employee employee) {
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       em.persist(employee);
       em.getTransaction().commit();
       
       return employee;
    }
    
    
}
