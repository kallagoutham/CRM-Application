package com.ennea.valuemanage.API.v1.Mapper;

import com.ennea.valuemanage.API.v1.DTO.AddressDTO;
import com.ennea.valuemanage.Model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    AddressDTO addressToAddressDTO(Address address);
    Address addressDTOToAddress(AddressDTO addressDTO);
}
