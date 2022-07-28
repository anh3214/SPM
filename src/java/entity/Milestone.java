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
public class Milestone {

    int milestone_id;
    String milestone_name;
    int iteration_id;
    int class_id;
    String from_date;
    String to_date;
    String description;
    int status;
    String class_code;
    String iteration_name;
    int subject_id;
    String subject_code;
    String subject_name;
    String trainer_name;
    int duration;

    public String getMilestone_name() {
        return milestone_name;
    }

    public void setMilestone_name(String milestone_name) {
        this.milestone_name = milestone_name;
    }
    
    public Milestone() {
    }

    public Milestone(int milestone_id, int iteration_id, int class_id, String from_date, String to_date, String description, int status) {
        this.milestone_id = milestone_id;
        this.iteration_id = iteration_id;
        this.class_id = class_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.description = description;
        this.status = status;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Milestone(int milestone_id, int iteration_id, int class_id, String from_date, String to_date, int status, String class_code, String iteration_name, int subject_id, String subject_code, String subject_name) {
        this.milestone_id = milestone_id;
        this.iteration_id = iteration_id;
        this.class_id = class_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
        this.class_code = class_code;
        this.iteration_name = iteration_name;
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getIteration_name() {
        return iteration_name;
    }

    public void setIteration_name(String iteration_name) {
        this.iteration_name = iteration_name;
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

    public int getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(int milestone_id) {
        this.milestone_id = milestone_id;
    }

    public int getIteration_id() {
        return iteration_id;
    }

    public void setIteration_id(int iteration_id) {
        this.iteration_id = iteration_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Milestone{" + "milestone_id=" + milestone_id + ", iteration_id=" + iteration_id + ", class_id=" + class_id + ", from_date=" + from_date + ", to_date=" + to_date + ", description=" + description + ", status=" + status + ", class_code=" + class_code + ", iteration_name=" + iteration_name + ", subject_id=" + subject_id + ", subject_code=" + subject_code + ", subject_name=" + subject_name + ", trainer_name=" + trainer_name + ", duration=" + duration + '}';
    }
}
