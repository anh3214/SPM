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
public class Issue {
    int issue_id;
    String issue_name;
    int assignee_id;
    String desciption;
    int gitlab_id;
    String gitlab_url;
    String created_at;
    String due_date;
    int team_id;
    int milestone_id;
    String function_ids;
    String labels;
    int status;
    int issue_type;
    int issue_status;
    
    
    int class_id;
    String class_code;
    String assignee_name;
    String team_name;
    String milestone_name;
    String function_name;
    String label_title;
    String issue_status_title;
    String colorOfLabel;
    String colorOfStatus;
    
    
    public Issue() {
    }

    public Issue(int team_id, String class_code, String team_name) {
        this.team_id = team_id;
        this.class_code = class_code;
        this.team_name = team_name;
    }

    public Issue(int issue_id,String issue_name, int assignee_id, String desciption, int gitlab_id, String gitlab_url, String created_at, String due_date, int team_id, int milestone_id, String function_ids, String labels, int status, int issue_type, int issue_status, String assignee_name, String team_name, String milestone_name, String function_name, String label_title, String issue_status_title, String colorOfLabel, String colorOfStatus) {
        this.issue_id = issue_id;
        this.issue_name = issue_name;
        this.assignee_id = assignee_id;
        this.desciption = desciption;
        this.gitlab_id = gitlab_id;
        this.gitlab_url = gitlab_url;
        this.created_at = created_at;
        this.due_date = due_date;
        this.team_id = team_id;
        this.milestone_id = milestone_id;
        this.function_ids = function_ids;
        this.labels = labels;
        this.status = status;
        this.issue_type = issue_type;
        this.issue_status = issue_status;
        this.assignee_name = assignee_name;
        this.team_name = team_name;
        this.milestone_name = milestone_name;
        this.function_name = function_name;
        this.label_title = label_title;
        this.issue_status_title = issue_status_title;
        this.colorOfLabel = colorOfLabel;
        this.colorOfStatus = colorOfStatus;
    }

    public String getColorOfLabel() {
        return colorOfLabel;
    }

    public void setColorOfLabel(String colorOfLabel) {
        this.colorOfLabel = colorOfLabel;
    }

    public String getColorOfStatus() {
        return colorOfStatus;
    }

    public void setColorOfStatus(String colorOfStatus) {
        this.colorOfStatus = colorOfStatus;
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

    public String getAssignee_name() {
        return assignee_name;
    }

    public void setAssignee_name(String assignee_name) {
        this.assignee_name = assignee_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getMilestone_name() {
        return milestone_name;
    }

    public void setMilestone_name(String milestone_name) {
        this.milestone_name = milestone_name;
    }



    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public int getIssue_type() {
        return issue_type;
    }

    public void setIssue_type(int issue_type) {
        this.issue_type = issue_type;
    }

    public int getIssue_status() {
        return issue_status;
    }

    public void setIssue_status(int issue_status) {
        this.issue_status = issue_status;
    }

    public String getLabel_title() {
        return label_title;
    }

    public void setLabel_title(String label_title) {
        this.label_title = label_title;
    }

    public String getIssue_status_title() {
        return issue_status_title;
    }

    public void setIssue_status_title(String issue_status_title) {
        this.issue_status_title = issue_status_title;
    }



    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }
    
    public String getIssue_title() {
        return issue_name;
    }

    public void setIssue_title(String issuename) {
        this.issue_name = issuename;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getGitlab_id() {
        return gitlab_id;
    }

    public void setGitlab_id(int gitlab_id) {
        this.gitlab_id = gitlab_id;
    }

    public String getGitlab_url() {
        return gitlab_url;
    }

    public void setGitlab_url(String gitlab_url) {
        this.gitlab_url = gitlab_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(int milestone_id) {
        this.milestone_id = milestone_id;
    }

    public String getFunction_ids() {
        return function_ids;
    }

    public void setFunction_ids(String function_ids) {
        this.function_ids = function_ids;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    
}
