package com.example.dev_j310_springapp.service.impl;

import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.common.dto.ClientType;
import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;
import com.example.dev_j310_springapp.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;


@Service
public class ClientServiceImplStored implements ClientService {

    private Set<ClientDto> clients;

    {
        clients = new HashSet<>();
        clients.add(ClientDto.builder()
                        .clientid(1)
                        .clientName("Федя Иванов")
                        .type(ClientType.getClientType("Физическое лицо"))

        .build());
        clients.add(ClientDto.builder()
                .clientid(2)
                .clientName("Вася Пупкин")
                .type(ClientType.getClientType("Физическое лицо"))

                .build());
        clients.add(ClientDto.builder()
                .clientid(3)
                .clientName("Рога и Копыта")
                .type(ClientType.getClientType("Юридическое лицо"))

                .build());
    }

    @Override
    public Stream<ClientDto> findAll(){
        return clients.stream();
    }

    @Override
    public Optional findClientById(Integer id){
        return clients.stream().filter(e -> e.getClientid().equals(id)).findFirst();
    }

    @Override
    public void remove(Integer id)  throws EAppException{
        findClientById(id).ifPresentOrElse(
                e -> clients.remove(e),
                () -> {
                    throw new EAppException(String.format("Объект с идентификатором '%d' не найден", id));
                }
        );
    }


    @Override
    public void update(ClientDto clientDto) throws EAppException{
        try {
        remove(clientDto.getClientid());
        clients.add(clientDto);

    } catch (EAppException e){
           throw new EAppException(String.format("Объект с идентификатором '%d' не найден", clientDto.getClientid()));
        }


    }

    @Override
    //public Optional <ClientDto> create(ClientDto clientDto){
    public Optional<ClientDto> create(ClientDto clientDto){
        clients.stream().max(Comparator.comparing(ClientDto::getClientid))
                .map(ClientDto::getClientid)
                .ifPresent(e -> {
                    clientDto.setClientid(e++);
                    clients.add(clientDto);
                });
        return findClientById(clientDto.getClientid());
        //return null;
    }

    @Override
    public Stream<ClientDto> findByClientName(String clientname, String clientType) {
        return null;
    }
}