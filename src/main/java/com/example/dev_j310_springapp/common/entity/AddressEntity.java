package com.example.dev_j310_springapp.common.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="Address")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private int id;

    @Column(name = "ip", nullable = false, length = 25)
    private String ip;

    @Column(name = "mac", nullable = false, length = 20)
    private String mac;

    @Column(name = "model",nullable = false, length = 100)
    private String model;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid")
    private ClientEntity client;
}
