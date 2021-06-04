

/**
 * @author xinjianli
 * @date 2021/6/4 13:17
 * @Email:835863855@qq.com
 */
package com.springboot.spring_boot_1.Dao;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.springboot.spring_boot_1.model.Student;
@Mapper public interface StudentDao {
    @Select("SELECT * FROM student WHERE name like #{name}")
    List<Student> find(@Param("name") String name);
    @Insert("INSERT INTO student(id,name,email) VALUES (#{id},#{name},#{email})")
    int insert(Student stu);
    @Delete("DELETE FROM student WHERE name=#{name}")
    int delete(@Param("name") String name);
    @Update("Update student set student.name=#{name},student.email=#{email} WHERE student.id=#{id}")
    int updata(Student stu);
    @Select("SELECT * FROM student")
    List<Student> findall();
}