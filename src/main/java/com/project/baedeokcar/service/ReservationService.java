package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.reservation.ReservationDelResDto;
import com.project.baedeokcar.domain.dto.reservation.ReservationListResDto;
import com.project.baedeokcar.domain.dto.reservation.ReservationSaveReqDto;

import java.util.List;

public interface ReservationService {
    List<ReservationListResDto> findReservationListByMemberId(String memberId);

    List<ReservationListResDto> findReservationListByCarId(Long carId);

    ReservationListResDto saveReservation(ReservationSaveReqDto request);

    void deleteReservation(Long id);
}
