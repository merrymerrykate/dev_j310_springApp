package com.example.dev_j310_springapp.dto;

import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.common.dto.ClientType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ClientAddressDto {
    private Integer clientid;
    private String clientName;
    private ClientType type;
    private Date added;
    private int addressId;
    private String ip;
    private String mac;
    private String model;
    private String address;
}
