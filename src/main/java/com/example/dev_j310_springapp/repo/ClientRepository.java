package com.example.dev_j310_springapp.repo;

import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;

import java.util.Optional;
import java.util.stream.Stream;

public interface ClientRepository extends CustomRepository{

//    Stream<ClientEntity> findAll();

//    void remove(Integer id) throws EAppException;
//    void update(ClientEntity clientEntity) throws EAppException;
//    Optional <ClientEntity> create(ClientEntity clientEntity);

    Stream<ClientEntity> findByClientName(String firstName);
}
