package com.forex.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "packet")
@Entity
public class Packet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "create_at")
    private LocalDateTime create_at = LocalDateTime.now();

    @Column(name = "update_at")
    private LocalDateTime update_at = LocalDateTime.now();

    @Column(name = "isdeleted")
    private Boolean isdeleted = false;

    @OneToMany(mappedBy = "packet")
    private List<Subscription> subscriptions;
}
