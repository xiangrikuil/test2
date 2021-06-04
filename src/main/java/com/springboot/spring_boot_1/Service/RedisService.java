package com.springboot.spring_boot_1.Service;
import java.util.List;
import com.springboot.spring_boot_1.model.Product;
public interface RedisService {
    public List<Product> searchProduct();
}