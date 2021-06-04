package com.springboot.spring_boot_1.Dao;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.springboot.spring_boot_1.model.Product;
@Mapper public interface ProductDao {
    @Select("SELECT * FROM product")
    public List<Product> searchProduct();
}