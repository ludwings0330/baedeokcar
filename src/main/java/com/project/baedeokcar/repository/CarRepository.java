package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select c from Car c where c.owner.loginId = :loginId")
    List<Car> findOwnCar(@Param("loginId") String loginId);

    @Query("select c from Car c where c.id = :carId")
    Car findCarById(@Param("carId") Long carId);
}

