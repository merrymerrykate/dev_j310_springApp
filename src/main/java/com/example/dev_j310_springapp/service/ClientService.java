package com.example.dev_j310_springapp.service;

import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;

import java.util.Optional;
import java.util.stream.Stream;

public interface ClientService {

    Stream<ClientDto> findAll();
    Optional <ClientDto>findClientById(Integer id);
    void remove(Integer id) throws EAppException;
    void update(ClientDto clientDto) throws EAppException;
    Optional<ClientDto> create(ClientDto clientDto);

    Stream<ClientDto> findByClientName(String clientname, String clientType);
}
