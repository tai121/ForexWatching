package com.forex.entities;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HData {
    private LocalDate date;
    private Float open;

    private Float high;

    private Float low;

    private Float close;

    private Float volume;

    public HData(HistoricalData d) {
        this.date = d.getDate();
        this.open = d.getOpen();
        this.high = d.getHigh();
        this.low = d.getLow();
        this.close = d.getClose();
        this.volume = d.getVolume();
    }
}
