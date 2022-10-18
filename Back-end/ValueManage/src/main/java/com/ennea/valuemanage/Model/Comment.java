package com.ennea.valuemanage.Model;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment extends BaseEntity{
    String name;
    String text;
    LocalDate date;


    Comment(Long id, String name, String text, LocalDate date){
        super(id);
        this.name=name;
        this.text=text;
        this.date=date;
    }

}
