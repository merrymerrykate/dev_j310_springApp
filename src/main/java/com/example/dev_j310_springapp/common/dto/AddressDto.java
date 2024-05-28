package com.example.dev_j310_springapp.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private int addressId;
    private ClientDto clientDto;
    private String ip;
    private String mac;
    private String model;
    private String address;

}
