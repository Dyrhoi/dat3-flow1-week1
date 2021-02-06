/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dyrhoi.github.customerjpademo;

import dyrhoi.github.customerjpademo.facade.CustomerFacade;
import dyrhoi.github.customerjpademo.entity.Customer;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Windowshoi
 */
public class Main {
    public static void main(String[] args) {
        
        CustomerFacade cf = CustomerFacade.getCustomerFacade(Persistence.createEntityManagerFactory("pu"));
        
        /*
        cf.addCustomer("Emil", "Andersen");
        cf.addCustomer("Tobias", "Andersen");
        cf.addCustomer("Martin", "Hansen");
        */
        
        List<Customer> customers = cf.allCustomers();
        System.out.println("All customers:");
        for(Customer c : customers) {
            System.out.println(c);
        }
        
        List<Customer> customersByLastname = cf.findByLastName("Andersen");
        System.out.println("Customer with lastname Andersen");
        for(Customer c : customersByLastname) {
            System.out.println(c);
        }
        
        int customersCount = cf.getNumberOfCustomers();
        System.out.println("Customer amount: " + customersCount);
    }
    
}
