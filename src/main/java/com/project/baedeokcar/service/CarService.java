package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.Car;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CarService {
    public void modifyCar(Long id, int price);
}
