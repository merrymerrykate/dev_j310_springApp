package com.example.dev_j310_springapp.common.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name ="Client")
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class ClientEntity {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientid")
    private int clientId;

    @Column(name = "client_name",nullable = false, length = 100)
    private String clientName;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "added", nullable = false)
    private LocalDateTime added;

    @OneToMany(mappedBy = "client")
    private Set<AddressEntity> addressEntities = new LinkedHashSet<>();


}
