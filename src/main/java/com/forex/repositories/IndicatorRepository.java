package com.forex.repositories;

import com.forex.entities.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorRepository extends JpaRepository<Indicator,Long> {
}
