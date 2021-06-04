package com.springboot.spring_boot_1.Service.serviceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.spring_boot_1.Dao.ProductDao;
import com.springboot.spring_boot_1.Service.RedisService;
import com.springboot.spring_boot_1.Util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
@Service @Slf4j
public class RedisServiceImpl implements RedisService{
    @Autowired
    private RedisUtil.redisList redisList;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ProductDao productDao;
    @Override public List searchProduct() {
        List list = new ArrayList<>();
        if (redisUtil.hasKey("productList")) {
            System.out.println("从 redis 中获取数据.");
            list = redisList.get("productList", 0, -1);
        }
        else {
            list = productDao.searchProduct();
            System.out.println("从数据库中获取数据.");
            System.out.println("将数据存入 redis..."); redisList.set("productList", list);
            System.out.println("成功存入 redis.");
        }
        return list;
    }
}