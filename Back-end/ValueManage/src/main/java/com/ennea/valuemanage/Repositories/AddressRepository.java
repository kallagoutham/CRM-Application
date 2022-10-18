package com.ennea.valuemanage.Repositories;

import com.ennea.valuemanage.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
