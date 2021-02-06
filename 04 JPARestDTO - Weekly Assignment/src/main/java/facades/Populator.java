/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BankCustomerDTO;
import entities.BankCustomer;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        BankCustomerFacade bcf = BankCustomerFacade.getInstance(emf);
        bcf.add(new BankCustomer("Martin", "Jørgensen", "AX22V", 60000, 1, "Scary User"));
        bcf.add(new BankCustomer("Christian", "Skovsen", "AWP47", 40000, 2, "Friendly User"));
        bcf.add(new BankCustomer("John", "Johansen", "L96V", 250102, 3, "CEO Account"));
        
    }
    
    public static void main(String[] args) {
        populate();
    }
}
