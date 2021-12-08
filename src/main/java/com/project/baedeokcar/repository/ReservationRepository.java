package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
