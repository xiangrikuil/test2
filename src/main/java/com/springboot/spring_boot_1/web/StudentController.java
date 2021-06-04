
/**
 * @author xinjianli
 * @date 2021/6/4 13:18
 * @Email:835863855@qq.com
 */
package com.springboot.spring_boot_1.web;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.spring_boot_1.Service.*;
import com.springboot.spring_boot_1.model.Student;
@RestController @RequestMapping(value="/demo")
public class StudentController {
    @Autowired
    private StudentService svc;
    @RequestMapping(value="/select")
    public ModelAndView select(@RequestParam("name") String name)
    {
        ModelAndView mav=new ModelAndView("view");
        List<Student>stu=new ArrayList(); stu=svc.findstu(name); mav.addObject("stu",stu); return mav;
    }
    @RequestMapping(value="/insert")
    public int insertstu(@RequestParam("id") String id, @RequestParam("name") String name,@RequestParam("email") String email)
    {
        Student stu=new Student(id, name, email); return svc.insertstu(stu);
    }
    @RequestMapping(value="/delete")
    public int deletestu(@RequestParam("name") String name)
    {
        return svc.deletestu(name);
    }
    @RequestMapping(value="/update")
    public int updatestu(@RequestParam("id") String id, @RequestParam("name") String name,@RequestParam("email") String email)
    {
        Student stu=new Student(id,name,email); return svc.updatestu(stu);
    }
    @RequestMapping(value="/findall")
    public ModelAndView findall()
    {
        ModelAndView mav=new ModelAndView("view");
        List<Student>stu=new ArrayList(); stu=svc.findall(); mav.addObject("stu",stu); return mav;
    }
}