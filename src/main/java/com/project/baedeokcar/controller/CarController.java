package com.project.baedeokcar.controller;

import com.project.baedeokcar.aop.PerfLogging;
import com.project.baedeokcar.domain.dto.car.CarDto;
import com.project.baedeokcar.domain.dto.car.CarListDto;
import com.project.baedeokcar.domain.dto.car.CarModReqDto;
import com.project.baedeokcar.domain.dto.car.CarSaveReqDto;
import com.project.baedeokcar.domain.dto.member.MemberDto;
import com.project.baedeokcar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        System.out.println("request.toString() = " + request.toString());
        carService.save(request);

        return "redirect:/";
    }

    @GetMapping("/read-car/{carId}")
    public String carDetails(@PathVariable Long carId, Model model) {
        CarDto findCarDto = carService.findOneById(carId);
        model.addAttribute("car", findCarDto);

        return "car/read";
    }

    // 차량 등록 취소
    // 자동차 Id를 알고 있어야지?
    public String deleteCar() {

        return "/";
    }



    // 차량 정보 수정
    @GetMapping("/mod-car")
    @PerfLogging
    public String modifyCarForm() {
        return "car-modify";
    }

    @PostMapping("/mod-car")
    @PerfLogging
    public String modifyCarInfo(@ModelAttribute CarModReqDto request, HttpServletRequest rq) {
        carService.modifyCar(request);

        return "redirect:car/own-car-list";
    }

    // 전체 차량 목록
    @GetMapping("/car-list")
    public String carList(Model model) {
        List<CarListDto> allCars = carService.getAllCars();

        model.addAttribute("allCars", allCars);

        return "car/car-list";
    }

    // 회원이 소유중인 차량 목록
    @GetMapping("/own-car-list")
    public String ownCarList(HttpSession session, Model model) {
        MemberDto authInfo = (MemberDto) session.getAttribute("authInfo");
        String loginId = authInfo.getLoginId();
        List<CarListDto> ownCars = carService.getOwnCars(loginId);
        model.addAttribute("allCars", ownCars);
        return "car/own-car-list";
    }
}
