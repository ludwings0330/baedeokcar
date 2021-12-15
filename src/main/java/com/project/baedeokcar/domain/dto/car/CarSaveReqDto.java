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
    private String carModel;
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
                .carModel(carModel)
                .year(year)
                .build();
    }
}
