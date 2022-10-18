package com.ennea.valuemanage.API.v1.DTO;

import com.ennea.valuemanage.Model.CustomerType;
import lombok.Data;

@Data
public class CustomerDTO {
    Long id;
    String name;
    String phoneNo;
    AddressDTO address;
    String businessName;
    String ERP;
    CustomerType customerType;
}
