
/**
 * @author xinjianli
 * @date 2021/6/4 13:17
 * @Email:835863855@qq.com
 */
package com.springboot.spring_boot_1.model;
public class Student {
    private String id;
    private String name;
    private String email;
    public Student(String id, String name, String email) {
        this.id=id;
        this.name=name;
        this.email=email;
    }
    public Student() {}
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    @Override public String toString() {
        return " id:" + id + " name:" + name + " email: " + email;
    }
}