package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();
            
            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            Employee employee;
            List<Employee> employees;
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            System.out.println("All employees with salary > 100000");
            TypedQuery<Employee> qe = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 100000", Employee.class);
            employees = qe.getResultList();
            employees.forEach(System.out::println);
            
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            System.out.println("Employee with ID klo999:");
            employee = em.find(Employee.class, "klo999");
            System.out.println(employee.getFirstName());
            
            //3) Create a query to fetch the highest salary and print the value
            System.out.println("Highest salary in Database");
            TypedQuery<BigDecimal> qs = em.createQuery("SELECT MAX(e.salary) FROM Employee e", BigDecimal.class);
            BigDecimal e = qs.getSingleResult();
            System.out.println(e);

            //4) Create a query to fetch the firstName of all Employees and print the names
            System.out.println("All employee firstnames:");
            TypedQuery<String> qa = em.createQuery("SELECT e.firstName FROM Employee e", String.class);
            List<String> employeeFirstnames = qa.getResultList();
            employeeFirstnames.forEach(System.out::println);
           
            //5 Create a query to calculate the number of employees and print the number
            System.out.println("Amount of employees in Database.");
            TypedQuery<Long> qc = em.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.salary > 100000", Long.class);
            long count = qc.getSingleResult();
            System.out.println(count);
           
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            System.out.println("Highest salaried employee:");
            qe = em.createQuery("SELECT e FROM Employee e ORDER BY e.salary DESC", Employee.class);
            employee = qe.setMaxResults(1).getSingleResult();
            System.out.println(employee);
            
        } finally {
            em.close();
            emf.close();
        }
    }

}
