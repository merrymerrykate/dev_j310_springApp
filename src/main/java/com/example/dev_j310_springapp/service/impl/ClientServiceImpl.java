package com.example.dev_j310_springapp.service.impl;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.common.dto.ClientType;
import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;
import com.example.dev_j310_springapp.repo.AddressRepository;
import com.example.dev_j310_springapp.repo.ClientRepository;
import com.example.dev_j310_springapp.service.AddressService;
import com.example.dev_j310_springapp.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AddressService addressService;

    public ClientServiceImpl(ClientRepository clientRepository, AddressService addressService) {
        this.clientRepository = clientRepository;
        this.addressService = addressService;
    }

    @Override
    public Stream<ClientDto> findAll() {
        return clientRepository.findAll(ClientEntity.class).map(ClientServiceImpl::entityToDto);
    }

    @Override
    public Optional <ClientDto> findClientById(Integer id) {
        return clientRepository.findById(ClientEntity.class, id).map(ClientServiceImpl::entityToDto);
    }


    @Override
    public void update(ClientDto clientDto) throws EAppException {
            clientRepository.findById(ClientEntity.class, clientDto.getClientid()).ifPresentOrElse(
                    entity -> {
                        entity.setClientName(clientDto.getClientName());
                        entity.setAdded(clientDto.getAdded());
                        entity.setType(clientDto.getType().name());
                        clientRepository.update(entity);
                    },
                    () -> {
                        throw new EAppException(String.format("Объект с идентификатором '%d' не найден", clientDto.getClientid()));
                    }
            );
    }

    @Override
    public Optional<ClientDto> create(ClientDto clientDto) {
        return clientRepository.create(dtoToEntity(clientDto)).map(ClientServiceImpl::entityToDto);
    }

    @Override
    public Stream<ClientDto> findByClientName(String clientname) {
        return clientRepository.findByClientName(clientname).map(ClientServiceImpl::entityToDto);

    }

    @Override
    public void remove(Integer id){
        clientRepository.findById(ClientEntity.class, id).ifPresentOrElse(
                entity -> {
                        addressService.findAddressByClientId(id)
                                .map(AddressDto::getAddressId)
                                .sorted(Integer::compareTo)
                                .forEach(addressService::remove);
                        clientRepository.remove(entity);
                },
                () -> {
                    throw new EAppException(String.format("Объект с идентификатором '%d' не найден", id));
                }

        );
    }

    public static ClientDto entityToDto(ClientEntity entity){
            return ClientDto.builder()
                    .clientid(entity.getClientId())
                    .clientName(entity.getClientName())
                    .type(ClientType.getClientType(entity.getType()))
                    .added(entity.getAdded())
                    .build();
    }

    public static ClientEntity dtoToEntity(ClientDto dto){
        ClientEntity entity = new ClientEntity();
        entity.setClientId(dto.getClientid());
        entity.setClientName(dto.getClientName());
        entity.setAdded(dto.getAdded());
        entity.setType(dto.getType().getType());
        return entity;
    }
}
