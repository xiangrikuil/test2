
/**
 * @author xinjianli
 * @date 2021/6/4 13:18
 * @Email:835863855@qq.com
 */
package com.springboot.spring_boot_1.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.spring_boot_1.Dao.StudentDao;
import com.springboot.spring_boot_1.model.Student;
@Service public class StudentService {
    @Autowired
    private StudentDao dao;
    public List<Student> findstu(String name)
    {
        return dao.find(name+'%');
    }
    public int insertstu(Student stu)
    {
        return dao.insert(stu);
    }
    public int deletestu(String name)
    {
        return dao.delete(name);
    }
    public int updatestu(Student stu)
    {
        return dao.updata(stu);
    }
    public List<Student> findall()
    {
        return dao.findall();
    }
}