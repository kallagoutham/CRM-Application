package com.ennea.valuemanage.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Customer extends Person implements Serializable {

    private String businessName;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Employee agent;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders=new HashSet<>();

    @OneToMany
    private Set<Comment> comments=new HashSet<>();

    private String ERP;

    public void addOrder(Order order){
        order.setCustomer(this);
        this.orders.add(order);
    }
    @Builder
    public Customer(String name, String phoneNo, Address address,
                    Long id, String businessName, CustomerType customerType,
                    @Singular Set<Order> orders,@Singular Set<Comment> comments,
                    String ERP) {
        super(name, phoneNo, address, id);
        this.businessName = businessName;
        this.customerType = customerType;
        this.orders = orders;
        this.comments = comments;
        this.ERP=customerType==CustomerType.RETAILER?null:ERP;
    }
}
