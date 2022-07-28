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
public class TeamEvaluation {
    int team_eval_id;
    int evaluation_id;
    int criteria_id;
    int team_id;
    int grade;
    String note;

    public TeamEvaluation() {
    }

    public TeamEvaluation(int team_eval_id, int evaluation_id, int criteria_id, int team_id, int grade, String note) {
        this.team_eval_id = team_eval_id;
        this.evaluation_id = evaluation_id;
        this.criteria_id = criteria_id;
        this.team_id = team_id;
        this.grade = grade;
        this.note = note;
    }

    public int getTeam_eval_id() {
        return team_eval_id;
    }

    public void setTeam_eval_id(int team_eval_id) {
        this.team_eval_id = team_eval_id;
    }

    public int getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(int evaluation_id) {
        this.evaluation_id = evaluation_id;
    }

    public int getCriteria_id() {
        return criteria_id;
    }

    public void setCriteria_id(int criteria_id) {
        this.criteria_id = criteria_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
