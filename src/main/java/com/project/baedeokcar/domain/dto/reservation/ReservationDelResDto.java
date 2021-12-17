package com.project.baedeokcar.domain.dto.reservation;

import com.project.baedeokcar.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDelResDto {
    private Long id;

    public ReservationDelResDto(Reservation entity) {
        this.id = entity.getId();
    }
}
