package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.Car;
import com.project.baedeokcar.domain.Member;
import com.project.baedeokcar.domain.dto.CarSaveDto;
import com.project.baedeokcar.domain.dto.MemberJoinDto;
import com.project.baedeokcar.repository.CarRepository;
import com.project.baedeokcar.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void registerCar(CarSaveDto car, Long id) throws Exception {
        Optional<Member> findMember = memberRepository.findById(id);
        Car instance = car.toEntity(findMember.get());

        carRepository.save(instance);
    }

    @Override
    public void modifyCar(Long id, int price) {
        Optional<Car> findCar = carRepository.findById(id);
        findCar.get().changePricePerHour(price);
    }

    public void 등록취소(Long id) {
        Optional<Car> findCar = carRepository.findById(id);
        if (findCar.isPresent()) {
            carRepository.delete(findCar.get());
        }


    }

    public void 차량예약(Long id, LocalDateTime reserveTime) {

    }

    public void 예약취소(Long id) {

    }

    public void 반납(Long id) {

    }
}
