package com.project.baedeokcar.controller;

import com.project.baedeokcar.aop.PerfLogging;
import com.project.baedeokcar.domain.dto.reservation.ReservationListResDto;
import com.project.baedeokcar.domain.dto.reservation.ReservationSaveReqDto;
import com.project.baedeokcar.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final ReservationService reservationService;



    @GetMapping("/reservations")
    public String reservationForm(@RequestParam(name="id", defaultValue =  "0") Long carId, Model model) {
        model.addAttribute("carId", carId);
        return "/reservation/reservations";
    }


    @GetMapping("/reservations/member/{memberId}")
    public @ResponseBody List<ReservationListResDto> getReservationListByMember(@PathVariable String memberId) {
        List<ReservationListResDto> response = reservationService.findReservationListByMemberId(memberId);
        return response;
    }

    @GetMapping("/reservations/car/{carId}")
    public @ResponseBody List<ReservationListResDto> getReservationListByCar(@PathVariable Long carId) {
        List<ReservationListResDto> response = reservationService.findReservationListByCarId(carId);
        return response;
    }

    @PostMapping("/save-reservation")
    @PerfLogging
    @ResponseBody
    public ReservationListResDto saveReservation(@ModelAttribute ReservationSaveReqDto request) {
        ReservationListResDto result = reservationService.saveReservation(request);
        return result;
    }

    @PostMapping("/del-reservation")
    public @ResponseBody String deleteReservation(@RequestParam Long id) {
        reservationService.deleteReservation(id);
        return "ok";
    }
}
