package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.Car;
import com.project.baedeokcar.domain.Member;
import com.project.baedeokcar.domain.Reservation;
import com.project.baedeokcar.domain.dto.member.MemberJoinDto;
import com.project.baedeokcar.domain.dto.reservation.ReservationDelResDto;
import com.project.baedeokcar.domain.dto.reservation.ReservationListResDto;
import com.project.baedeokcar.domain.dto.reservation.ReservationSaveReqDto;
import com.project.baedeokcar.repository.CarRepository;
import com.project.baedeokcar.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final MemberService memberService;
    private final CarService carService;
    private final CarRepository carRepository;

    private final ReservationRepository reservationRepository;

    @Override
    public List<ReservationListResDto> findReservationListByMemberId(String memberId) {
        List<Reservation> reservationList = memberService.findOneByLoginId(memberId).getReservationList();

        return reservationList.stream().map(r -> new ReservationListResDto(r))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationListResDto> findReservationListByCarId(Long carId) {
        Car findCar = carRepository.findCarById(carId);
        List<Reservation> reservationList = findCar.getReservationList();

        return reservationList.stream().map(r -> new ReservationListResDto(r))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ReservationListResDto saveReservation(ReservationSaveReqDto request) {

        if (isOverlapping(request.getLoginId(), request.getStart(), request.getEnd())) {
            return null;
        }

        Reservation reservation = request.toEntity();

        Member reqMember = memberService.findOneByLoginId(request.getLoginId());
        Car reqCar = carRepository.findCarById(request.getCarId());

        reservation.setMemberAndCarRelation(reqMember, reqCar);

        Reservation savedReservation = reservationRepository.save(reservation);

        return new ReservationListResDto(savedReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public boolean isOverlapping(String loginId, LocalDate start, LocalDate end) {
        List<Reservation> all = reservationRepository.findAllByDateBetween(loginId, start, end);
        if (all.size() == 0) {
            return false;
        }
        return true;
    }
}
