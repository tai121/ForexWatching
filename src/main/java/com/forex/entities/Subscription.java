package com.forex.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "subscription")
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Float value;

    @Column(name = "create_at")
    private LocalDateTime create_at = LocalDateTime.now();

    @Column(name = "update_at")
    private LocalDateTime update_at = LocalDateTime.now();

    @Column(name = "iscanceled")
    private Boolean iscanceled=false;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "packet_id")
    private Packet packet;

    public Boolean isExpired(){
        if(create_at.plusDays(30).isAfter(LocalDateTime.now())&&iscanceled==false)
            return false;
        return true;
    }
}
