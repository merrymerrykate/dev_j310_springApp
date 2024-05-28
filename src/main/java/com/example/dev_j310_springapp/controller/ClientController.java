package com.example.dev_j310_springapp.controller;


import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.dto.ClientAddressDto;
import com.example.dev_j310_springapp.service.ClientAddressService;
import com.example.dev_j310_springapp.service.ClientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    //подключение бина
    //private final ClientService clientService;
    private final ClientAddressService clientAddressService;

    public ClientController(ClientAddressService clientAddressService) {
        this.clientAddressService = clientAddressService;
    }

    @GetMapping
    public String findAll(Model model){
        List<ClientAddressDto> clients = clientAddressService.findAll().toList();
        model.addAttribute("clients",clients);
        return "clients_page";
    }

    @GetMapping("/filter/{clientname}")
    public String findByClientName(@PathVariable ("clientname") String clientname, Model model){
        List<ClientAddressDto> clients = clientAddressService.findByClientName(clientname).toList();
        model.addAttribute("clients",clients);
        return "clients_page";
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
