package com.project.baedeokcar.domain.dto.car;

import com.project.baedeokcar.domain.Car;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CarSaveReqDto {
    private String loginId;
    private String info;
    private String year;
    private int driveDistance;
    private int price;
    private MultipartFile file;

    public Car toEntity() {
        LocalDateTime now = LocalDateTime.now();

        String originFileName = file.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf('.'));

        return Car.builder()
                .info(info)
                .price(price)
                .driveDistance(driveDistance)
                .originFileName(file.getOriginalFilename())
                .savedFileName(now.toString() + ext )
                .year(year)
                .build();
    }

    public CarSaveReqDto(Car entity) {
        this.loginId = entity.getOwner().getLoginId();
        this.year = entity.getYear();
        this.driveDistance = entity.getDriveDistance();
        this.info = entity.getInfo();
        this.price = entity.getPrice();
    }
}
