/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author buitr
 */
public class Feature {
    int feature_id;
    int team_id;
    String feature_name;
    int status;
    String topic_code;
    String topic_name;
    String class_code;
    String trainer_name;
    String subject_code;
    String subject_name;
    String description;

    public Feature() {
    }

    public Feature(int feature_id, int team_id, String feature_name, int status, String description) {
        this.feature_id = feature_id;
        this.team_id = team_id;
        this.feature_name = feature_name;
        this.status = status;
        this.description = description;
    }

    public Feature(int feature_id, int team_id, String feature_name, int status, String topic_code, String class_code, String trainer_name, String subject_code, String subject_name, String description, String topic_name) {
        this.feature_id = feature_id;
        this.team_id = team_id;
        this.feature_name = feature_name;
        this.status = status;
        this.topic_code = topic_code;
        this.topic_name = topic_name;
        this.class_code = class_code;
        this.trainer_name = trainer_name;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.description = description;
    }

    public Feature(int feature_id, int team_id, String feature_name, int status, String topic_code, String class_code, String trainer_name, String subject_code, String subject_name) {
        this.feature_id = feature_id;
        this.team_id = team_id;
        this.feature_name = feature_name;
        this.status = status;
        this.topic_code = topic_code;
        this.class_code = class_code;
        this.trainer_name = trainer_name;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
    }
    
    public String getTopic_code() {
        return topic_code;
    }

    public void setTopic_code(String topic_code) {
        this.topic_code = topic_code;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
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

    
    
    public int getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(int feature_id) {
        this.feature_id = feature_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feature{" + "feature_id=" + feature_id + ", team_id=" + team_id + ", feature_name=" + feature_name + ", status=" + status + '}';
    }
    
            
}
