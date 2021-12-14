package com.project.baedeokcar.domain.dto.car;

import com.project.baedeokcar.domain.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarModReqDto {
    private Long carId;
    private String info;
    private int price;
}
