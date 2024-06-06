package com.example.dev_j310_springapp.repo;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.entity.AddressEntity;
import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;

import java.util.Optional;
import java.util.stream.Stream;

public interface AddressRepository extends CustomRepository {
//    Stream<AddressEntity> findAll();

    Optional<AddressEntity> findAddressById(Integer id);
    Stream<AddressEntity> findByAddress(String address, String type);
    Stream<AddressEntity> findAddressByClientId(Integer id);



}
