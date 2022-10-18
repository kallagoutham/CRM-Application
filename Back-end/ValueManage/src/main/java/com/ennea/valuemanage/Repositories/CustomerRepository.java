package com.ennea.valuemanage.Repositories;

import com.ennea.valuemanage.Model.Comment;
import com.ennea.valuemanage.Model.Customer;
import com.ennea.valuemanage.Model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("select ord from Order ord where ord.customer.id=?1")
    Page<Order> findAllOrders(Long id, PageRequest pageRequest);

    @Query("select cust.comments from Customer cust where cust.id=?1")
    Page<Comment> findAllComments(Long id,PageRequest pageRequest);
}
