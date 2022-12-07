package com.example.backendproject.repos;

import com.example.backendproject.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon , String> {
        Coupon findByValue(String value) ;
@Query(
        value = "SELECT * FROM Coupon c WHERE c.value = ?1",
        nativeQuery = true)
    List<Coupon> findCouponByValue(String    status);
}
