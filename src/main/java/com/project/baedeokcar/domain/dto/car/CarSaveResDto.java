package com.project.baedeokcar.domain.dto.car;

import com.project.baedeokcar.domain.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarSaveResDto {
    private String loginId;
    private String info;
    private int price;

    public Car toEntity() {
        return Car.builder()
                .info(info)
                .price(price)
                .build();
    }

    public CarSaveResDto(Car entity) {
        this.loginId = entity.getOwner().getLoginId();
        this.info = entity.getInfo();
        this.price = entity.getPrice();
    }
}
