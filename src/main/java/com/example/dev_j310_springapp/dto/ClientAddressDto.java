package com.example.dev_j310_springapp.dto;

import com.example.dev_j310_springapp.common.dto.ClientDto;
import com.example.dev_j310_springapp.common.dto.ClientType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientAddressDto {


    private Integer clientid;

    @NotBlank(message = "Client name cannot be empty")
    //@Pattern(regexp = "^$|[а-яА-ЯёЁ]+", message = "Имя клиента должно содержать только русские буквы")
    @Size(max = 255, message = "Client name cannot exceed 255 characters")
    private String clientName;


    private ClientType type;
    private LocalDateTime added;
    private int addressId;
    @NotBlank(message = "IP address cannot be empty")
    @Pattern(
            regexp = "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$",
            message = "Invalid IP address format"
    )
    private String ip;
    @NotBlank(message = "MAC address cannot be empty")
    @Pattern(
            regexp = "^([0-9A-Fa-f]{2}-){5}([0-9A-Fa-f]{2})$",
            message = "Invalid MAC address format"
    )
    private String mac;
    @NotBlank(message = "Model cannot be empty")
    @Size(max = 100, message = "Model length must be less than or equal to 100 characters")
    private String model;
    @NotBlank(message = "Address cannot be empty")
    @Size(max = 200, message = "Address length must be less than or equal to 200 characters")
    private String address;
}
