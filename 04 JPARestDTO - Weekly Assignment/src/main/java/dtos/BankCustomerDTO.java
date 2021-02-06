/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class BankCustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private double balance;

    public BankCustomerDTO(BankCustomer c) {
        id = c.getId();
        firstName = c.getFirstName();
        lastName = c.getLastName();
        accountNumber = c.getAccountNumber();
        balance = c.getBalance();
    }
    
    public static List<BankCustomerDTO> ListToDTOs(List<BankCustomer> rms){
        List<BankCustomerDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new BankCustomerDTO(rm)));
        return rmdtos;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    
    
}
