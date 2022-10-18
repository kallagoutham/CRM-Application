package com.ennea.valuemanage.Model.security;

import com.ennea.valuemanage.Model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Authority  extends BaseEntity {

    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}