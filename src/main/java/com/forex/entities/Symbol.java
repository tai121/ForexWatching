package com.forex.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "symbol")
public class Symbol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_currency")
    @Size(max = 3,min = 3)
    @NotEmpty
    private String first_currency;

    @Column(name = "second_curency")
    @NotEmpty
    @Size(max = 3,min = 3)
    private String second_currency;
    @Column(name = "description")
    private String description;

    @Column(name = "createAt",nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "updateAt",nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    @Column(name = "isdeleted", columnDefinition = "boolean default false")
    private boolean isdeleted = false;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "symbol_id")
    private List<HistoricalData> forex_data;

    @ManyToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "symbol_id")
    private List<Indicator> indicators;
}
