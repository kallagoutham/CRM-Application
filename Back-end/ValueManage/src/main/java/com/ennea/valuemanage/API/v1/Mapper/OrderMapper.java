package com.ennea.valuemanage.API.v1.Mapper;

import com.ennea.valuemanage.API.v1.DTO.OrdersDTO;
import com.ennea.valuemanage.Model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    public OrdersDTO orderToOrderDTO(Order order);
    public Order orderDTOToOrder(OrdersDTO orderDTO);
}
