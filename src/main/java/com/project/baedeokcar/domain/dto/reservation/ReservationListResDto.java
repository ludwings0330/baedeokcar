package com.project.baedeokcar.domain.dto.reservation;

import com.project.baedeokcar.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationListResDto {
    private Long id;
    private String loginId;
    private Long carId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    public ReservationListResDto(Reservation entity) {
        this.id = entity.getId();
        this.loginId = entity.getMember().getLoginId();
        this.carId = entity.getCar().getId();
        this.start = entity.getStart();
        this.end = entity.getEnd();
    }
}
