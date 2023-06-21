package com.forex.repositories;

import com.forex.entities.Packet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacketRepository extends JpaRepository<Packet,Long> {
}
