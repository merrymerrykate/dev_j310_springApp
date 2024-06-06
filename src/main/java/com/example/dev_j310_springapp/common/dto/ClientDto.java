package com.example.dev_j310_springapp.common.dto;

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
public class ClientDto {

    private Integer clientid;

    @NotBlank(message = "Client name cannot be empty")
    //@Pattern(regexp = "^$|[а-яА-ЯёЁ]+", message = "Имя клиента должно содержать только русские буквы")
    @Size(max = 255, message = "Client name cannot exceed 255 characters")
    private String clientName;


    private ClientType type;
    private LocalDateTime added;




}
