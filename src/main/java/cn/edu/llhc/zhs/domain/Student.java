package cn.edu.llhc.zhs.domain;

import java.io.Serializable;

public class Student implements Serializable {

    private Integer stu_id;
    private String stu_name;
    private String stu_pwd;
    private Integer stu_level;
    private Integer stu_money;
    private String stu_email;
    private String photo;

    public Student() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_id=" + stu_id +
                ", stu_name='" + stu_name + '\'' +
                ", stu_pwd='" + stu_pwd + '\'' +
                ", stu_level=" + stu_level +
                ", stu_money=" + stu_money +
                ", stu_email='" + stu_email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Student(Integer stu_id, String stu_name, String stu_pwd, Integer stu_level, Integer stu_money, String stu_email, String photo) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.stu_pwd = stu_pwd;
        this.stu_level = stu_level;
        this.stu_money = stu_money;
        this.stu_email = stu_email;
        this.photo = photo;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_pwd() {
        return stu_pwd;
    }

    public void setStu_pwd(String stu_pwd) {
        this.stu_pwd = stu_pwd;
    }

    public Integer getStu_level() {
        return stu_level;
    }

    public void setStu_level(Integer stu_level) {
        this.stu_level = stu_level;
    }

    public Integer getStu_money() {
        return stu_money;
    }

    public void setStu_money(Integer stu_money) {
        this.stu_money = stu_money;
    }

    public String getStu_email() {
        return stu_email;
    }

    public void setStu_email(String stu_email) {
        this.stu_email = stu_email;
    }

}
