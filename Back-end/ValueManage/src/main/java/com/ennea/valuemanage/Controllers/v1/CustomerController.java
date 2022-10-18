package com.ennea.valuemanage.Controllers.v1;

import com.ennea.valuemanage.API.v1.DTO.OrdersDTO;
import com.ennea.valuemanage.Model.Comment;
import com.ennea.valuemanage.Services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")

public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"retailers/{id}/orders","distributors/{id}/orders"})
    public ResponseEntity<Page<OrdersDTO>> getCustomerOrders(@PathVariable Long id,
                                                             @RequestParam(defaultValue = "0")int page,
                                                             @RequestParam(defaultValue = "10")int size){
        return new ResponseEntity<>(customerService.getOrders(id, PageRequest.of(page,size, Sort.Direction.DESC,"date")), HttpStatus.OK);
    }

    @GetMapping({"retailers/{id}/comments","distributors/{id}/comments"})
    public ResponseEntity<Page<Comment>> getCustomerComments(@PathVariable Long id,
                                                             @RequestParam(defaultValue = "0")int page,
                                                             @RequestParam(defaultValue = "10")int size){
        return new ResponseEntity<>(customerService.getComments(id, PageRequest.of(page,size)),HttpStatus.OK);
    }

    @PostMapping({"retailers/{id}/comments","distributors/{id}/comments"})
    public ResponseEntity<Void> addComment(@PathVariable Long id,@RequestBody Comment comment){
        customerService.addNewComment(id,comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
