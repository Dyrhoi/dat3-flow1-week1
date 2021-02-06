/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade ef = EmployeeFacade.getInstance(emf);
        
        ef.create(new Employee("John Martin", "Gurrelynge 5", 1000000));
        ef.create(new Employee("Martin Skov", "Brostræd 1", 10000));
        ef.create(new Employee("Christian Klovn", "Lungevej 12", 1000005));
        ef.create(new Employee("Lunge Mans", "Weedistreet 52a", 100));
        ef.create(new Employee("Mogens Bo", "Gurrelynge 2", 15000));
        ef.create(new Employee("Lars Martin", "Gurrelynge 5", 1000000));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
