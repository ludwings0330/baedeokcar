package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
