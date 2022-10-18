package com.ennea.valuemanage.Services;

import com.ennea.valuemanage.API.v1.DTO.OrdersDTO;
import com.ennea.valuemanage.Model.Comment;
import com.ennea.valuemanage.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CustomerService {
    public Customer save(Customer customer);
    public Page<OrdersDTO> getOrders(Long id, PageRequest pageRequest);
    public Page<Comment> getComments(Long id,PageRequest pageRequest);

    void addNewComment(Long id, Comment comment);
}
