/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Tes
 * and open the template in the editor.
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ptuan
 */
public class User {

    int user_id;
    String roll_number;
    String full_name;
    String gender;
    String date_of_birth;
    String email;
    String mobile;
    String avatar_link;
    String facebook_link;
    int role_id;
    int status;
    String password;
    String token_user;

    public User() {
    }

    public User(int user_id, String full_name) {
        this.user_id = user_id;
        this.full_name = full_name;
    }

    public User(String roll_number, String full_name, String gender, String date_of_birth, String email, String mobile, String avatar_link, String facebook_link, int role_id, int status, String password) {
        this.roll_number = roll_number;
        this.full_name = full_name;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.mobile = mobile;
        this.avatar_link = avatar_link;
        this.facebook_link = facebook_link;
        this.role_id = role_id;
        this.status = status;
        this.password = password;
        updateDate();
    }

    public User(int user_id, String roll_number, String full_name, String gender, String date_of_birth, String email, String mobile, String avatar_link, String facebook_link, int role_id, int status, String password) {
        this.user_id = user_id;
        this.roll_number = roll_number;
        this.full_name = full_name;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.mobile = mobile;
        this.avatar_link = avatar_link;
        this.facebook_link = facebook_link;
        this.role_id = role_id;
        this.status = status;
        this.password = password;
        updateDate();
    }

    public String getToken_user() {
        return token_user;
    }

    public void setToken_user(String token_user) {
        this.token_user = token_user;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {

        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
        updateDate();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar_link() {
        return avatar_link == null || "".equals(avatar_link) || !avatar_link.contains("services") ? "services/image/default.jpg" : avatar_link;
    }

    public void setAvatar_link(String avatar_link) {
        this.avatar_link = avatar_link;
    }

    public String getFacebook_link() {
        return facebook_link;
    }

    public void setFacebook_link(String facebook_link) {
        this.facebook_link = facebook_link;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateDate() {
        if (date_of_birth != null && !"".equals(date_of_birth)) {
            try {
                String form = date_of_birth.contains("/") ? "MM/dd/yyyy" : date_of_birth.contains("-") ? "yyyy-MM-dd" : null;

                Date nDate = new SimpleDateFormat(form).parse(date_of_birth);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = formatter.format(nDate);
                date_of_birth = strDate;
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", roll_number=" + roll_number + ", full_name=" + full_name + ", gender=" + gender + ", date_of_birth=" + date_of_birth + ", email=" + email + ", mobile=" + mobile + ", avatar_link=" + avatar_link + ", facebook_link=" + facebook_link + ", role_id=" + role_id + ", status=" + status + ", password=" + password + '}';
    }

}
