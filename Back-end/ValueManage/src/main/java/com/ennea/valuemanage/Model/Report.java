package com.ennea.valuemanage.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Report extends BaseEntity {
    int totalMet;
    int newOnboarded;
    int existingMet;
    int ordersPlaced;
    LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;

    @OneToOne
    Comment comment;

    public Report(Long id, int totalMet, int newOnboarded, int existingMet, int ordersPlaced, LocalDate date, Comment comment,Employee employee) {
        super(id);
        this.totalMet = totalMet;
        this.newOnboarded = newOnboarded;
        this.existingMet = existingMet;
        this.ordersPlaced = ordersPlaced;
        this.date = date;
        this.comment = comment;
        this.employee=employee;
    }
}
