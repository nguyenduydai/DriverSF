package com.example.taixesf.model;

public class UserObject extends AbstractModel {
    private String user_name;
    private String user_phone;
    private int user_age;
    private String user_sex;
    private String user_email;
    private String user_hometown;
    private String user_image;
    private String user_password;
    private int user_status;
    private int user_area_id;
    private int user_role_id;

    private int user_salary;

    public int getUser_salary() {
        return user_salary;
    }

    public void setUser_salary(int user_salary) {
        this.user_salary = user_salary;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_hometown() {
        return user_hometown;
    }

    public void setUser_hometown(String user_hometown) {
        this.user_hometown = user_hometown;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public int getUser_area_id() {
        return user_area_id;
    }

    public void setUser_area_id(int user_area_id) {
        this.user_area_id = user_area_id;
    }

    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }

    @Override
    public String toString() {
        return "UserObject{" +
                "user_id='" + getId() + '\'' +
                "user_name='" + user_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_age='" + user_age + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_hometown='" + user_hometown + '\'' +
                ", user_image='" + user_image + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_status=" + user_status +
                ", user_area_id=" + user_area_id +
                ", user_role_id=" + user_role_id +

                '}';
    }
}
