package com.ennea.valuemanage.API.v1.Mapper;

import com.ennea.valuemanage.API.v1.DTO.CustomerDTO;
import com.ennea.valuemanage.Model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
