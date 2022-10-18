package com.ennea.valuemanage.Model.security;

import com.ennea.valuemanage.Model.BaseEntity;
import com.ennea.valuemanage.Model.Employee;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString
public class User extends BaseEntity implements UserDetails {
    String username;
    String password;

    Long employeeId;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Collection<? extends GrantedAuthority> covertToSprigAuthorities(Set<Authority> authorities) {
        if(authorities!=null&&authorities.size()>0){
            return authorities.stream()
                    .map(Authority::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
        return new HashSet<>();
    }
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return covertToSprigAuthorities(authorities);
    }

    public Set<Authority> getPlainAuthorities(){
        return authorities;
    }

    @ManyToMany
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID"))
    private Set<Authority> authorities;

    @Builder
    public User(Long id, String userName, String password, Employee employee,@Singular Set<Authority> authorities) {
        super(id);
        this.username = userName;
        this.password = password;
        this.employeeId = employee.getId();
        this.authorities=authorities;
    }


}
