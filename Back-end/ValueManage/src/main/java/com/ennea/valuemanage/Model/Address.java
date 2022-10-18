package com.ennea.valuemanage.Model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address extends BaseEntity{
private String houseNumber;
private String street;
private String city;
private String state;
private String pinCode;
private String country;


    public Address(Long id, String houseNumber, String street, String city, String state, String pinCode, String country) {
        super(id);
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }
}
