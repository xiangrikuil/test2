
/**
 * @author xinjianli
 * @date 2021/6/4 12:58
 * @Email:835863855@qq.com
 */
package com.springboot.spring_boot_1.web;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController public class HelloController {
    @RequestMapping("/hello2")
    public String hello() {
        return "helloworld2";
    }
}