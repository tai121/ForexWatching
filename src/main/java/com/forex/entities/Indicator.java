package com.forex.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "indicator")
@Entity
public class Indicator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rsi")
    private Double rsi;
    @Column(name = "stoch")
    private Double stoch;
    @Column(name = "stochrsi")
    private Double stochRsi;
    @Column(name = "macd")
    private Double macd;
    @Column(name = "adx")
    private double adx;
    @Column(name = "williamsr")
    private double williamsR;
    @Column(name = "cci")
    private double cci;
    @Column(name = "atr")
    private double atr;
    @Column(name = "highslows")
    private double highsLows;
    @Column(name = "ultimateoscillator")
    private double ultimateOscillator;
    @Column(name = "roc")
    private double roc;
    @Column(name = "bullbearpower")
    private double bullBearPower;
    @Column(name = "create_at")
    private LocalDateTime create_at = LocalDateTime.now();

    @Column(name = "update_at")
    private LocalDateTime update_at = LocalDateTime.now();

    @Column(name = "isdeleted")
    private Boolean isdeleted;

    @ManyToOne
    private Symbol symbol;
}
