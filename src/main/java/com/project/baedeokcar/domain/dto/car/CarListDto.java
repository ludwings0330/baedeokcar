package com.project.baedeokcar.domain.dto.car;

import com.project.baedeokcar.domain.Car;
import com.project.baedeokcar.domain.CarState;
import com.project.baedeokcar.domain.Member;
import com.project.baedeokcar.domain.Reservation;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CarListDto {
    private Long id;
    private String owner;
    private String info;
    private int price;

    public Car toEntity() {
        return Car.builder()
                .id(id)
                .price(price)
                .info(info)
                .build();
    }

    public CarListDto(Car entity) {
        this.id = entity.getId();
        this.owner = entity.getOwner().getLoginId();
        this.info = entity.getInfo();
        this.price = entity.getPrice();
    }
}
