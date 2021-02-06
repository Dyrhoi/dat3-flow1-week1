/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;

/**
 *
 * @author Windowshoi
 */
public class EmployeeDTO {
    private Long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        address = employee.getAddress();
    }
    
    
    
}
