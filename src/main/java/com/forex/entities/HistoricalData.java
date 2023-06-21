package com.forex.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Historical_data")
@Data
public class HistoricalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "open")
    private Float open;

    @Column(name = "high")
    private Float high;

    @Column(name = "low")
    private Float low;

    @Column(name = "close")
    private Float close;

    @Column(name = "volume")
    private Float volume;

    @Column(name = "isdeleted", columnDefinition = "boolean default false")
    private boolean isdeleted = false;

    @ManyToOne
    private Symbol symbol;
}
