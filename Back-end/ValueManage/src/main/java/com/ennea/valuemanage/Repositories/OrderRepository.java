package com.ennea.valuemanage.Repositories;

import com.ennea.valuemanage.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
