package com.example.dev_j310_springapp.controller;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.exception.EAppException;
import com.example.dev_j310_springapp.service.AddressService;
import com.example.dev_j310_springapp.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client-address-update")
public class ClientAddressUpdateController {

    private final ClientService clientService;
    private final AddressService addressService;




    public ClientAddressUpdateController(@Qualifier("clientService") ClientService clientService,
                                          AddressService addressService) {
        this.clientService = clientService;
        this.addressService = addressService;

    }


    @GetMapping("update/{clientId}")
    public String showUpdateClientForm(@PathVariable Integer clientId, Model model){
        ClientDto clientDto = clientService.findClientById(clientId)
                .orElseThrow(() -> new EAppException("Клиент с Id: " + clientId));
        model.addAttribute("clientDto", clientDto);
        return "update_client_page";
    }

    @PostMapping("/update/{clientId}")
    public String updateClient(@Valid @PathVariable Integer clientId, @ModelAttribute ClientDto clientDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "update_client_page";
        }

        try{
            clientService.update(clientDto);
            return "redirect:/clients";
        } catch (EAppException e) {
            model.addAttribute("error", "Ошибка при изменении клиента: " + e.getMessage());
            return "update_client_page";
        }
    }

    @GetMapping("/update-address/{addressId}")
    public String showUpdateAddressForm(@PathVariable Integer addressId, Model model){
        AddressDto addressDto = addressService.findAddressById(addressId)
                .orElseThrow(() -> new EAppException("Некорректный id адреса" + addressId));
        model.addAttribute("addressDto", addressDto);
        return "update_address_page";
    }

    @PostMapping("/update-address/{addressId}")
    public String updateAddress(@Valid  @PathVariable Integer addressId,@ModelAttribute AddressDto addressDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "update_address_page";
        }

        try{
            addressService.update(addressDto);
            return "redirect:/clients";
        } catch(EAppException e){
            model.addAttribute("error", "Error updating address: " + e.getMessage());
            return "update_address_page";
        }
    }

    @GetMapping("/delete-address/{addressId}")
    public String deleteAddress(@PathVariable Integer addressId, Model model){
        try{
            addressService.remove(addressId);
            return "redirect:/clients";
        } catch(EAppException e){
            model.addAttribute("error", "Ошибка при удалении адреса" + e.getMessage());
            return "clients_page";
        }
    }
}
