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
public class CarDto {
    private Long id;
    private String loginId;
    private int driveDistance;   // 주행거리
    private String year; // 년식
    private String info;
    private int price;
    private String originFileName;
    private String savedFileName;


    public CarDto(Car entity) {
        this.id = entity.getId();
        this.loginId = entity.getOwner().getLoginId();
        this.driveDistance = entity.getDriveDistance();
        this.year = entity.getYear();
        this.info = entity.getInfo();
        this.price = entity.getPrice();
        this.originFileName = entity.getOriginFileName();
        this.savedFileName = entity.getSavedFileName();
    }
}
