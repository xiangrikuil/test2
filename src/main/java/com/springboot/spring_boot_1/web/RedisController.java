package com.springboot.spring_boot_1.web;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.spring_boot_1.model.Product;
import com.springboot.spring_boot_1.Service.serviceImpl.RedisServiceImpl;
@RestController @RequestMapping(value="/demo")
public class RedisController {
    @Autowired
    private RedisServiceImpl redisServiceImpl;
    @SuppressWarnings("unchecked")
// @PostMapping(value = "/test")
    @RequestMapping(value="/test")
//@ResponseBody
    public List<Product> testRedis() {
        return redisServiceImpl.searchProduct();
    }
}