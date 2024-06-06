package com.example.dev_j310_springapp.controller;


import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.dto.ClientAddressDto;
import com.example.dev_j310_springapp.service.ClientAddressService;
import com.example.dev_j310_springapp.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {


    private final ClientAddressService clientAddressService;

    public ClientController(ClientAddressService clientAddressService) {
        this.clientAddressService = clientAddressService;
    }


    @GetMapping
    public String findAllOrFilter(@RequestParam(value = "clientname", required = false) String clientName,
                                   @RequestParam(value = "clienttype", required = false) String clientType,
                                   @RequestParam(value = "clientaddress", required = false) String clientAddress,
                                       Model model) {
        List<ClientAddressDto> clients = null;
        if ((clientName != null && !clientName.isEmpty()) || (clientAddress != null && !clientAddress.isEmpty()) || (clientType != null && !clientType.isEmpty())) {
            if (clientName != null && !clientName.isEmpty()) {
                clients = clientAddressService.findByClientName(clientName, clientType).toList();
                model.addAttribute("clients", clients);
                return "clients_page";
            }

            if (clientAddress != null && !clientAddress.isEmpty()) {
                clients = clientAddressService.findByAddress(clientAddress, clientType).toList();
                model.addAttribute("clients", clients);
                return "clients_page";
            }

            if (clientType != null && !clientType.isEmpty()) {
                clients = clientAddressService.findByClientName(clientName, clientType).toList();
                model.addAttribute("clients", clients);
                return "clients_page";
            }
        } else {
            clients = clientAddressService.findAll().toList();
        }

        model.addAttribute("clients", clients);
        return "clients_page";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("clientAddressDto", new ClientAddressDto());
        return "create_client_page";
    }

    @PostMapping("/create")
    public String createClient(@Valid @ModelAttribute ClientAddressDto clientAddressDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "create_client_page";
        }

        try {
            clientAddressDto.setAdded(LocalDateTime.now());
            System.out.println("LocalDateTime.now()");
            ClientDto clientDto = ClientDto.builder()
                    .clientName(clientAddressDto.getClientName())
                    .type(clientAddressDto.getType())
                    .added(clientAddressDto.getAdded())
                    .build();


            AddressDto addressDto = AddressDto.builder()
                    .clientDto(clientDto)
                    .ip(clientAddressDto.getIp())
                    .mac(clientAddressDto.getMac())
                    .model(clientAddressDto.getModel())
                    .address(clientAddressDto.getAddress())
                    .build();


            clientAddressService.create(clientDto, addressDto);

            return "redirect:/clients";
        } catch (Exception e) {
            System.out.println( e.getMessage());
            model.addAttribute("error", "Error creating client: " + e.getMessage());
            return "create_client_page";
        }
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable ("id") Integer id, Model model){
        clientAddressService.findById(id).findFirst().ifPresent(e -> model.addAttribute("client", e));
       return "client_page";

    }

    @GetMapping("/remove/{id}")
   public String removeById(@PathVariable ("id") Integer id, Model model){
        clientAddressService.remove(id);
        return "redirect:/clients";

    }

}
