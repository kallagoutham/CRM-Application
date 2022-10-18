package com.ennea.valuemanage.API.v1.DTO;

import com.ennea.valuemanage.Model.Comment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportDTO {
    int totalMet;
    int newOnboarded;
    int existingMet;
    int ordersPlaced;
//    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate date;
    Comment comment;
}
