/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Tuan
 */
public class Class {
    
    int class_id;
    String class_code;
    int trainer_id;
    int subject_id;
    int class_year;
    String class_term;
    int block5_class;
    int status;
    String gitlabID;

    public Class() {
    }

    public Class(int class_id, String class_code, int trainer_id, int subject_id, int class_year, String class_term, int block5_class, int status, String gitlabID) {
        this.class_id = class_id;
        this.class_code = class_code;
        this.trainer_id = trainer_id;
        this.subject_id = subject_id;
        this.class_year = class_year;
        this.class_term = class_term;
        this.block5_class = block5_class;
        this.status = status;
        this.gitlabID = gitlabID;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getClass_year() {
        return class_year;
    }

    public void setClass_year(int class_year) {
        this.class_year = class_year;
    }

    public String getClass_term() {
        return class_term;
    }

    public void setClass_term(String class_term) {
        this.class_term = class_term;
    }

    public int getBlock5_class() {
        return block5_class;
    }

    public void setBlock5_class(int block5_class) {
        this.block5_class = block5_class;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGitlabID() {
        return gitlabID;
    }

    public void setGitlabID(String gitlabID) {
        this.gitlabID = gitlabID;
    }

    
}
