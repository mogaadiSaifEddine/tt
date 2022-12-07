package com.example.backendproject.service;


import com.example.backendproject.entities.Coupon;
import com.example.backendproject.entities.CouponStatus;
import com.example.backendproject.entities.CouponType;
import com.example.backendproject.repos.CouponRepository;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository ;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Coupon addCoupon (Coupon coupon , int order){

        System.out.println(coupon.toString());
        if (coupon.getCouponType()== CouponType.USER_1){
            coupon.setNumberUsers(1);
        }if (coupon.getCouponType()== CouponType.USER_2){
            coupon.setNumberUsers(2);
        }if (coupon.getCouponType()== CouponType.USER_3){
            coupon.setNumberUsers(3);
        }
        String a = String.valueOf(java.time.LocalTime.now())+ order;
        System.out.println(a);
    String j = Hex.encodeHexString(a.getBytes());
    System.out.println(j);
    String encodedString = Hex.encodeHexString((Base64.getEncoder().encodeToString(j.getBytes())).getBytes());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(a);
        Date date = new Date(System.currentTimeMillis());
        String nowDate  = formatter.format(date)  ;
        System.out.println(bCryptPasswordEncoder.encode(a));
        coupon.setId(a+nowDate);
        coupon.setValue(j);
        coupon.setCreationDate(date);
        coupon.setStatus(CouponStatus.unused);
        return  couponRepository.save(coupon) ;
    }

    public void  deleteCoupon(String id){
        couponRepository.deleteById(id);
    }

    public Coupon getOneCoupon(String id){
        return couponRepository.findById(id).orElse(null);
    }


    public Coupon updateCoupon(Coupon coupon){
        Coupon coupon1 = couponRepository.findById(coupon.getId()).orElse(null) ;
        assert coupon1!= null;
        coupon1.setCreationDate(coupon.getCreationDate());
        coupon1.setExpirationDate(coupon.getExpirationDate());
        coupon1.setValue(coupon.getValue());
        coupon1.setStatus(coupon.getStatus());

        return couponRepository.save(coupon1);

    }

    public List<Coupon> getAllCoupon(){

        return  couponRepository.findAll();
    }

    public  void  readCoupon (String couponCode , Long userId) {


    }

}
