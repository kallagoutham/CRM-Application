package com.ennea.valuemanage.API.v1.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrdersDTO {
    Long id;
    String productName;
    Integer quantity;
    Float amount;
    LocalDate date;
}
