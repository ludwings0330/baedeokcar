package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
