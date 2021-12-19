package com.project.baedeokcar.controller;

import com.project.baedeokcar.aop.PerfLogging;
import com.project.baedeokcar.domain.dto.car.CarReadResDto;
import com.project.baedeokcar.domain.dto.car.CarListResDto;
import com.project.baedeokcar.domain.dto.car.CarModReqDto;
import com.project.baedeokcar.domain.dto.car.CarSaveReqDto;
import com.project.baedeokcar.domain.dto.member.MemberDto;
import com.project.baedeokcar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    // 차량 등록
    @PostMapping("/save-car")
    @PerfLogging
    public String save(@ModelAttribute CarSaveReqDto request) throws Exception {
        carService.save(request);

        return "redirect:/";
    }


    @GetMapping("/read-car/{carId}")
    public String carDetails(@PathVariable Long carId, Model model) {
        CarReadResDto findCarDto = carService.findOneById(carId);
        model.addAttribute("car", findCarDto);

        return "car/read";
    }

    // 자동차 Id를 알고 있어야지?
    @GetMapping("del-car/{carId}")
    public String deleteCar(@PathVariable Long carId) {
        carService.delete(carId);

        return "forward:own-car-list";
    }

    // 차량 정보 수정 폼
    @GetMapping("/mod-car")
    @PerfLogging
    public String modifyCarForm(@ModelAttribute CarModReqDto request) throws Exception {
        return "car/car-modify";
    }

    // 차량 정보 수정
    @PostMapping("/mod-car")
    @PerfLogging
    public String modifyCarInfo(@ModelAttribute CarModReqDto request, HttpServletRequest rq) {
        carService.modifyCar(request);

        return "redirect:own-car-list";
    }

    // 전체 차량 목록
    @GetMapping("/car-list")
    public String carList(Model model) {
        List<CarListResDto> allCars = carService.getAllCars();

        model.addAttribute("allCars", allCars);

        return "car/car-list";
    }

    // 회원이 소유중인 차량 목록
    @GetMapping("/own-car-list")
    public String ownCarList(HttpSession session, Model model) {
        MemberDto authInfo = (MemberDto) session.getAttribute("authInfo");
        String loginId = authInfo.getLoginId();
        List<CarListResDto> ownCars = carService.getOwnCars(loginId);
        model.addAttribute("allCars", ownCars);
        return "car/own-car-list";
    }
}
