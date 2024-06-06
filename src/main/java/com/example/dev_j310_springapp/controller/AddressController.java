package com.example.dev_j310_springapp.controller;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.exception.EAppException;
import com.example.dev_j310_springapp.service.AddressService;
import com.example.dev_j310_springapp.service.ClientAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addresses")
public class AddressController {

    private final ClientAddressService clientAddressService;
    private final AddressService addressService;

    public AddressController(ClientAddressService clientAddressService, AddressService addressService) {
        this.clientAddressService = clientAddressService;
        this.addressService = addressService;
    }


    @GetMapping("/create/{clientId}")
    public String showAddAddressForm(@PathVariable Integer clientId, Model model) {
        model.addAttribute("addressDto", new AddressDto());
        model.addAttribute("clientId", clientId);
        return "add_address_page";
    }

    @PostMapping("/create/{clientId}")
    public String addAddressToClient(@PathVariable Integer clientId, @Valid @ModelAttribute AddressDto addressDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("clientId", clientId);
            return "add_address_page";
        }

        try {
            clientAddressService.createAddressForClient(clientId, addressDto);
            return "redirect:/clients";
        } catch (EAppException e) {
            model.addAttribute("error", "Error adding address: " + e.getMessage());
            model.addAttribute("clientId", clientId);
            return "add_address_page";
        }
    }


}
