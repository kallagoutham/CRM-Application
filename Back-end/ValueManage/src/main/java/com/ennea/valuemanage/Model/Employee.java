package com.ennea.valuemanage.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee extends Person implements Serializable {

    @OneToMany(mappedBy = "employee")
    private Set<Report> reports=new HashSet<>();

    @OneToMany(mappedBy = "agent")
    private Set<Customer> customers=new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<Attendance> attendanceList=new HashSet<>();

    @Enumerated(EnumType.STRING)
    private EmployeeRole employeeRole;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;

    @Builder
    public Employee(String name, String phoneNo, Address address,
                    Long id, @Singular Set<Report> reports,
                    @Singular Set<Customer> customers,
                    @Singular Set<Attendance> attendances,
                    Employee supervisor,EmployeeRole employeeRole) {
        super(name, phoneNo, address, id);
        this.reports = reports;
        this.customers = customers;
        this.attendanceList = attendances;
        this.supervisor = supervisor;
        this.employeeRole=employeeRole;
    }
}
