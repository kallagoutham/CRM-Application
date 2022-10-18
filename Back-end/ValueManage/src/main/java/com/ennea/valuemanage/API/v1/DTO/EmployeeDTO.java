package com.ennea.valuemanage.API.v1.DTO;

import com.ennea.valuemanage.Model.EmployeeRole;
import lombok.Data;

@Data
public class EmployeeDTO {
    Long id;
    String name;
    String phoneNo;
    AddressDTO address;
    EmployeeRole employeeRole;
}

