package com.ennea.valuemanage.API.v1.DTO;

import lombok.Data;

@Data
public class AddressDTO {
    String houseNumber;
    String street;
    String city;
    String state;
    String pinCode;
    String country;

}
