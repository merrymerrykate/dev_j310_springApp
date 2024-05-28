package com.example.dev_j310_springapp.service;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.exception.EAppException;

import java.util.Optional;
import java.util.stream.Stream;

public interface AddressService {

    Stream<AddressDto> findAll();
    Optional<AddressDto> findAddressById(Integer id);
    void remove(Integer id) throws EAppException;
    void update(AddressDto addressDto) throws EAppException;
    Optional <AddressDto> create(AddressDto addressDto);

    Stream<AddressDto> findAddressByClientId(Integer id);

}
