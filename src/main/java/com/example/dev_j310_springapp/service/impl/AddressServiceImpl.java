package com.example.dev_j310_springapp.service.impl;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.common.entity.AddressEntity;
import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;
import com.example.dev_j310_springapp.repo.AddressRepository;
import com.example.dev_j310_springapp.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Stream<AddressDto> findAll() {

        return addressRepository.findAll(AddressEntity.class).map(AddressServiceImpl::entityToDto);
    }

    @Override
    public Optional<AddressDto> findAddressById(Integer id) {
        return addressRepository.findAddressById(id).map(AddressServiceImpl::entityToDto);
    }

    @Override
    public void remove(Integer id) throws EAppException {
        findAddressById(id).map(e -> dtoToEntity(e))
                .ifPresent(addressRepository::remove);
    }

    @Override
    public void update(AddressDto addressDto) throws EAppException {
        addressRepository.update(dtoToEntity(addressDto));
    }

    @Override
    public Optional<AddressDto> create(AddressDto addressDto) {
        return addressRepository.create(dtoToEntity(addressDto)).map(AddressServiceImpl::entityToDto);
    }

    @Override
    public Stream<AddressDto> findAddressByClientId(Integer id) {
        return addressRepository.findAddressByClientId(id).map(AddressServiceImpl::entityToDto);
    }

    @Override
    public Stream<AddressDto> findByAddress(String address, String type) {
        return addressRepository.findByAddress(address, type).map(AddressServiceImpl::entityToDto);

    }

    public static AddressDto entityToDto(AddressEntity entity){
        return AddressDto.builder()
                .addressId(entity.getId())
                .ip(entity.getIp())
                .mac(entity.getMac())
                .model(entity.getModel())
                .address(entity.getAddress())
                .clientDto(ClientServiceImpl.entityToDto(entity.getClient()))
                .build();
    }

    public static AddressEntity dtoToEntity(AddressDto dto){
        AddressEntity entity = new AddressEntity();
        entity.setId(dto.getAddressId());
        entity.setIp(dto.getIp());
        entity.setMac(dto.getMac());
        entity.setModel(dto.getModel());
        entity.setAddress(dto.getAddress());
        entity.setClient(ClientServiceImpl.dtoToEntity(dto.getClientDto()));
        return entity;
    }
}
