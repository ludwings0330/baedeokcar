package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.car.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    //차량 등록
    CarSaveResDto save(CarSaveReqDto car) throws Exception;

    //차량 등록 취소
    void delete(Long carID);

    //차량 정보 수정
    void modifyCar(CarModReqDto car);

    //전체 차량 목록
    List<CarListResDto> getAllCars();

    //회원이 소유중인 차량 목록
    List<CarListResDto> getOwnCars(String loginId);

    CarReadResDto findOneById(Long carId);
}
