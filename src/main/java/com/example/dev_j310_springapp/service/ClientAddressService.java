package com.example.dev_j310_springapp.service;

import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.dto.ClientAddressDto;
import com.example.dev_j310_springapp.exception.EAppException;

import java.util.Optional;
import java.util.stream.Stream;

public interface ClientAddressService {

    Stream<ClientAddressDto> findAll();

    Stream<ClientAddressDto> findById(Integer id);

    void remove(Integer id) throws EAppException;
    Stream<ClientAddressDto> findByClientName(String clientname);



}
