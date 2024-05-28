package com.example.dev_j310_springapp.repo;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.entity.AddressEntity;
import com.example.dev_j310_springapp.exception.EAppException;

import java.util.Optional;
import java.util.stream.Stream;

public interface AddressRepository extends CustomRepository {
//    Stream<AddressEntity> findAll();

    Optional<AddressEntity> findAddressById(Integer id);
//    void remove(Integer id) throws EAppException;
//    void update(AddressEntity addressEntity) throws EAppException;
//    Optional <AddressEntity> create(AddressEntity addressEntity);

    Stream<AddressEntity> findAddressByClientId(Integer id);
//    void removeByClientId(Integer id) throws EAppException;

}
