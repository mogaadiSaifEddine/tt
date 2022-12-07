package com.example.backendproject.restControllers;


import com.example.backendproject.entities.*;
import com.example.backendproject.payload.request.RedeemCoupon;
import com.example.backendproject.repos.CouponRepository;
import com.example.backendproject.repos.UserRepository;
import com.example.backendproject.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RequestMapping("/coupon")
@RestController
public class CouponController {

    @Autowired
    CouponService couponService ;

     @Autowired
    CouponRepository couponRepository ;
@Autowired
    UserRepository userRepository ;

    @PostMapping("/{number}")
    public List<Coupon> addCoupon (@RequestBody Coupon coupon , @PathVariable int number){
        System.out.println(number);
        List <Coupon> couponList = new ArrayList<>();

        for (int i=0; i<number;i++) {
            Coupon newCoupon =    couponService.addCoupon(coupon , i);
           couponList.add(newCoupon) ;

        }
        return  couponList;
    }




    @GetMapping("/")
    public  List<Coupon> getLisTCoupon(){
        return couponService.getAllCoupon() ;

    }

    @GetMapping("/{id}")
    public Coupon getCouponById(@PathVariable String id ){
        return  couponService.getOneCoupon(id);
    }

    @PostMapping("/verif_token")
    public Coupon getCoupon(@RequestBody RedeemCoupon couponValue){
        return couponRepository.findByValue(couponValue.couponValue);
    }

    @PostMapping("/redeem/{userId}")
    public Coupon insertCoupon(@PathVariable  Long  userId ,@RequestBody RedeemCoupon couponValue){
        System.out.println(couponValue);
        Parent parent = (Parent) userRepository.findById(userId).orElse(null);
Coupon coupon  = couponRepository.findByValue(couponValue.couponValue)  ;
        assert parent != null;
        if (coupon.getStatus() == CouponStatus.unused && coupon.getUsedUsersNumber()!=coupon.getNumberUsers()){
            coupon.setUsedUsersNumber(coupon.getNumberUsers()+1);
            parent.getCouponList().add(coupon);
            userRepository.save(parent);
            coupon.setStatus(CouponStatus.used);
            coupon.setParent(parent);
            couponRepository.save(coupon);
        }

        System.out.println(coupon);

        return coupon;

    }


    @PostMapping("config-student")
    public List<Student> configStudents(@RequestBody List<Student> studentList){





        return  studentList;
    }


}
