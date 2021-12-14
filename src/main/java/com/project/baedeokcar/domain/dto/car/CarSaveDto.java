package com.project.baedeokcar.domain.dto.car;

import com.project.baedeokcar.domain.Car;
import com.project.baedeokcar.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarSaveDto {
    int price;

    public Car toEntity(Member member) {
        return Car.builder()
                .owner(member)
                .price(price)
                .build();
    }
}
