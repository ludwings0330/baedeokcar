package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /***
     * 시작일이 같으면 안된다.
     * 마지막날은 하루가 더 있다.
     * 즉, 시작일과 마지막날은 같아도 상관 없다.
     * @param start
     * @param end
     * @return
     */
    @Query("select r from Reservation r where r.member.loginId=:loginId AND (r.start <= :start AND :start < r.end) " +
            "or (r.start < :end AND :end <= r.end)" +
            "or (:start <= r.start AND r.end <= :end)")
    List<Reservation> findAllByDateBetween(@Param("loginId") String loginId, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
