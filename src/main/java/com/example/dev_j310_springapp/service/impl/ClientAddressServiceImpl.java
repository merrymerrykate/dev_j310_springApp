package com.example.dev_j310_springapp.service.impl;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.common.dto.ClientType;
import com.example.dev_j310_springapp.common.entity.AddressEntity;
import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.dto.ClientAddressDto;
import com.example.dev_j310_springapp.exception.EAppException;
import com.example.dev_j310_springapp.service.AddressService;
import com.example.dev_j310_springapp.service.ClientAddressService;
import com.example.dev_j310_springapp.service.ClientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ClientAddressServiceImpl implements ClientAddressService {

    private final ClientService clientService;
    private final AddressService addressService;

    public ClientAddressServiceImpl(@Qualifier("clientService") ClientService clientService,
                                    AddressService addressService) {
        this.clientService = clientService;
        this.addressService = addressService;
    }

    @Override
    public Stream<ClientAddressDto> findAll() {
        return clientService.findAll().flatMap(this::clientDtoToClientAddressDto);

    }

    @Override
    public Stream<ClientAddressDto> findById(Integer id) {
        return Stream.of(clientService.findClientById(id).orElse(null))
                .filter(Objects::nonNull)
                .flatMap(this::clientDtoToClientAddressDto);
    }

    @Override
    public void remove(Integer clientId) throws EAppException {
        clientService.remove(clientId);
    }


    @Override
    public Stream<ClientAddressDto> findByClientName(String clientname,String clientType) {
        return clientService.findByClientName(clientname, clientType).flatMap(this::clientDtoToClientAddressDto);
    }

    @Override
    public Stream<ClientAddressDto> findByAddress(String address, String clientType) {
        return addressService.findByAddress(address, clientType).flatMap(this::addressDtoToClientAddressDto);
    }

    public Stream<ClientAddressDto> clientDtoToClientAddressDto(ClientDto clientDto) {
        if (addressService.findAddressByClientId(clientDto.getClientid()).count() == 0) {
            return Stream.of(converter(clientDto, null));
        } else {
            return addressService.findAddressByClientId(clientDto.getClientid()).map(address ->
                    converter(clientDto, address));
        }
    }
        public Stream<ClientAddressDto> addressDtoToClientAddressDto(AddressDto addressDto) {
            return Stream.of(converter(addressDto.getClientDto(), addressDto));

    }

    @Override
    public ClientAddressDto create(ClientDto clientDto, AddressDto addressDto) throws EAppException {

        ClientDto createdClient = clientService.create(clientDto)
                 .orElseThrow(() -> new EAppException("Не удалось создать клиента"));

        addressDto.setClientDto(createdClient);

        AddressDto createdAddress = addressService.create(addressDto)
                .orElseThrow(() -> new EAppException("Не удалось создать клиента"));

        return converter(createdClient, createdAddress);
    }

    @Override
    public AddressDto createAddressForClient(Integer clientId, AddressDto addressDto) throws EAppException {
        ClientDto clientDto = clientService.findClientById(clientId)
                .orElseThrow(() -> new EAppException("Клиент не найден"));

        addressDto.setClientDto(clientDto);

        return addressService.create(addressDto)
                .orElseThrow(() -> new EAppException("Не удалось создать клиентаs"));
    }

    public ClientAddressDto converter(ClientDto clientDto, AddressDto addressDto){
        return ClientAddressDto.builder()
                .clientid(clientDto.getClientid())
                .clientName(clientDto.getClientName())
                .type(clientDto.getType())
                .added(clientDto.getAdded())
                .addressId(addressDto != null ? addressDto.getAddressId(): null)
                .ip(addressDto != null ? addressDto.getIp(): null)
                .mac(addressDto != null ? addressDto.getMac(): null)
                .model(addressDto != null ? addressDto.getModel(): null)
                .address(addressDto != null ? addressDto.getAddress(): null)
                .build();
    }

}

