package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.Car;
import com.project.baedeokcar.domain.Member;
import com.project.baedeokcar.domain.dto.car.*;
import com.project.baedeokcar.repository.CarRepository;
import com.project.baedeokcar.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public CarSaveResDto save(CarSaveReqDto car) throws Exception {
        String loginId = car.getLoginId();
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        Car newCar = car.toEntity();
        MultipartFile file = car.getFile();


        Path copyOfLocation = Paths.get("/Users/ludwings/study/baedeokcar/src/main/resources/static/cars" + File.separator + newCar.getSavedFileName());

        try {
            Files.copy(file.getInputStream(), copyOfLocation , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("파일 생성 오류");
        }

        newCar.setOwner(findMember.get());

        carRepository.save(newCar);

        return new CarSaveResDto(newCar);
    }

    @Transactional
    @Override
    public void delete(CarDelReqDto car) {
        Optional<Car> findCar = carRepository.findById(car.getCarId());
        carRepository.delete(findCar.get());
    }

    @Override
    public void modifyCar(CarModReqDto car) {
        Optional<Car> findCar = carRepository.findById(car.getCarId());
        findCar.get().changeCarInfo(car);
    }

    @Override
    public List<CarListDto> getAllCars() {
        List<Car> all = carRepository.findAll();

        List<CarListDto> collect = all.stream()
                .map(c -> new CarListDto(c))
                .collect(Collectors.toList());
        return collect;
    }

    // 회원이 등록한 자동차 List만 가져오기 -> 동적 쿼리 -> QueryDsl
    @Override
    public List<CarListDto> getOwnCars(String loginId) {
        List<Car> ownCar = carRepository.findOwnCar(loginId);
        List<CarListDto> collect = ownCar.stream()
                .map(c -> new CarListDto(c))
                .collect(Collectors.toList());

        return collect;
    }


    @Override
    public CarDto findOneById(Long carId) {
        Car findCar = carRepository.findCarById(carId);
        return new CarDto(findCar);
    }
}