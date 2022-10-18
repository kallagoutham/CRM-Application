package com.ennea.valuemanage.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@ToString(callSuper = true)
public class Person extends BaseEntity{
    private String name;
    private String phoneNo;
    @OneToOne
    private Address address;
    Person(String name,String phoneNo,Address address,Long id){
        super(id);
        this.name=name;
        this.address=address;
        this.phoneNo=phoneNo;
    }

}
