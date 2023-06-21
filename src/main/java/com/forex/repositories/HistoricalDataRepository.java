package com.forex.repositories;

import com.forex.entities.HistoricalData;
import com.forex.entities.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricalDataRepository extends JpaRepository<HistoricalData,Long> {
    @Query("SELECT h from HistoricalData h LEFT JOIN h.symbol s where concat(s.first_currency,s.second_currency) =?1")
    public List<HistoricalData> findBySymbol(String symbol);
//    @Query("select h from HistoricalData h where h.symbol = ?1")
//    public List<HistoricalData> findBySymbolObject(Symbol symbol);
}
