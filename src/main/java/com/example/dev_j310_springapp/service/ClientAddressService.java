package com.example.dev_j310_springapp.service;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.dto.ClientAddressDto;
import com.example.dev_j310_springapp.exception.EAppException;

import java.util.Optional;
import java.util.stream.Stream;

public interface ClientAddressService {

    Stream<ClientAddressDto> findAll();

    Stream<ClientAddressDto> findById(Integer id);

    void remove(Integer id) throws EAppException;
    Stream<ClientAddressDto> findByClientName(String clientname,String clientType);

    Stream<ClientAddressDto> findByAddress(String address, String clientType);

    public ClientAddressDto create(ClientDto clientDto, AddressDto addressDto) throws EAppException;
    AddressDto createAddressForClient(Integer clientId, AddressDto addressDto) throws EAppException;





}
