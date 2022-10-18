package com.ennea.valuemanage.Repositories.security;

import com.ennea.valuemanage.Model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

}
