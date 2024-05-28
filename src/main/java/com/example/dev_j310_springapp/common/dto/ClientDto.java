package com.example.dev_j310_springapp.common.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ClientDto {

    private Integer clientid;
    private String clientName;
    private ClientType type;
    private Date added;




}
