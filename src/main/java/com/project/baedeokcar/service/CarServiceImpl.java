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
    private final FileUploadService fileUploadService;

    @Transactional
    @Override
    public CarSaveResDto save(CarSaveReqDto car) throws Exception {
        String loginId = car.getLoginId();
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        Car newCar = car.toEntity();
        String savedFileName = fileUploadService.uploadImage(car.getFile());
        newCar.setSaveFileName(savedFileName);

        /* Local 위치에 저장하는 코드
        MultipartFile file = car.getFile();

        Path copyOfLocation = Paths.get("/Users/ludwings/study/baedeokcar/src/main/resources/static/cars" + File.separator + newCar.getSavedFileName());

        try {
            Files.copy(file.getInputStream(), copyOfLocation , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("파일 생성 오류");
        }
        */

        newCar.setOwner(findMember.get());

        carRepository.save(newCar);

        return new CarSaveResDto(newCar);
    }

    @Transactional
    @Override
    public void delete(Long carID) {
        carRepository.deleteById(carID);
    }

    @Transactional
    @Override
    public void modifyCar(CarModReqDto car) {
        Optional<Car> findCar = carRepository.findById(car.getId());
        findCar.get().changeCarInfo(car);
    }

    @Override
    public List<CarListResDto> getAllCars() {
        List<Car> all = carRepository.findAll();

        List<CarListResDto> collect = all.stream()
                .map(c -> new CarListResDto(c))
                .collect(Collectors.toList());
        return collect;
    }

    // 회원이 등록한 자동차 List만 가져오기 -> 동적 쿼리 -> QueryDsl
    @Override
    public List<CarListResDto> getOwnCars(String loginId) {
        List<Car> ownCar = carRepository.findOwnCar(loginId);
        List<CarListResDto> collect = ownCar.stream()
                .map(c -> new CarListResDto(c))
                .collect(Collectors.toList());

        return collect;
    }


    @Override
    public CarReadResDto findOneById(Long carId) {
        Car findCar = carRepository.findCarById(carId);
        return new CarReadResDto(findCar);
    }
}
