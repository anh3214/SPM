/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Luu Dat
 */
public class Criteria {

    int criteria_id;
    int iteration_id;
    String iteration_name;
    String evalution_title;
    String evalution_weight;
    String team_evaluation; // true - team ; false - member
    String criteria_order;
    String max_loc;
    int status;
    
    int subject_id;
    String subject_code;
    

    public Criteria() {
    }

    public Criteria(int criteria_id, int iteration_id, String iteration_name, String evalution_title, String evalution_weight, String team_evaluation, String criteria_order, String max_loc, int status) {
        this.criteria_id = criteria_id;
        this.iteration_id = iteration_id;
        this.iteration_name = iteration_name;
        this.evalution_title = evalution_title;
        this.evalution_weight = evalution_weight;
        this.team_evaluation = team_evaluation;
        this.criteria_order = criteria_order;
        this.max_loc = max_loc;
        this.status = status;
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

   

    
    public int getCriteria_id() {
        return criteria_id;
    }

    public void setCriteria_id(int criteria_id) {
        this.criteria_id = criteria_id;
    }

    public int getIteration_id() {
        return iteration_id;
    }

    public void setIteration_id(int iteration_id) {
        this.iteration_id = iteration_id;
    }

    public String getIteration_name() {
        return iteration_name;
    }

    public void setIteration_name(String iteration_name) {
        this.iteration_name = iteration_name;
    }

    public String getEvalution_title() {
        return evalution_title;
    }

    public void setEvalution_title(String evalution_title) {
        this.evalution_title = evalution_title;
    }

    public String getEvalution_weight() {
        return evalution_weight;
    }

    public void setEvalution_weight(String evalution_weight) {
        this.evalution_weight = evalution_weight;
    }

    public String getTeam_evaluation() {
        return team_evaluation;
    }

    public void setTeam_evaluation(String team_evaluation) {
        this.team_evaluation = team_evaluation;
    }

    public String getCriteria_order() {
        return criteria_order;
    }

    public void setCriteria_order(String criteria_order) {
        this.criteria_order = criteria_order;
    }

    public String getMax_loc() {
        return max_loc;
    }

    public void setMax_loc(String max_loc) {
        this.max_loc = max_loc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Criteria{" + "criteria_id=" + criteria_id + ", iteration_id=" + iteration_id + ", iteration_name=" + iteration_name + ", evalution_title=" + evalution_title + ", evalution_weight=" + evalution_weight + ", team_evaluation=" + team_evaluation + ", criteria_order=" + criteria_order + ", max_loc=" + max_loc + ", status=" + status + '}';
    }

}
