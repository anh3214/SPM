/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ptuan
 */
public class Subject {
    int subject_id;
    String subject_code;
    String subject_name;
    int author_id;
    int status;
    int user_id;
    String roll_number;
    String full_name;
    String gender;
    String date_of_birth;
    String email;
    String mobile;
    String facebook_link;
    String class_code;
    
    
    int id;
    String name;

    public Subject() {
    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject(int subject_id, String subject_code, String subject_name, int author_id, int status) {
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.author_id = author_id;
        this.status = status;
    }

    public Subject(int subject_id, String subject_code, String subject_name, int author_id, int status, int user_id, String roll_number, String full_name, String gender, String date_of_birth, String email, String mobile, String facebook_link, String class_code) {
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.author_id = author_id;
        this.status = status;
        this.user_id = user_id;
        this.roll_number = roll_number;
        this.full_name = full_name;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.mobile = mobile;
        this.facebook_link = facebook_link;
        this.class_code = class_code;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getFacebook_link() {
        return facebook_link;
    }

    public void setFacebook_link(String facebook_link) {
        this.facebook_link = facebook_link;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }
    
    

   
   
  
    
}
