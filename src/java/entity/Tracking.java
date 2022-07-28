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
public class Tracking {
    int tracking_id;
    int team_id;
    int milestone_id;
    int function_id;
    int assigner_id;
    int assignee_id;
    String tracking_note;
    String updates;
    int  status;
    
    
    int class_id;
    String class_code;
    String assignee_name;
    String assigner_name;
    String milestone_name;
    String function_name;
    String access_roles;

    public Tracking(int tracking_id, int team_id, int milestone_id, int function_id, int assigner_id, int assignee_id, String tracking_note, int status, String assignee_name, String assigner_name, String milestone_name, String function_name, String access_roles) {
        this.tracking_id = tracking_id;
        this.team_id = team_id;
        this.milestone_id = milestone_id;
        this.function_id = function_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.tracking_note = tracking_note;
        this.status = status;
        this.assignee_name = assignee_name;
        this.assigner_name = assigner_name;
        this.milestone_name = milestone_name;
        this.function_name = function_name;
        this.access_roles = access_roles;
    }

    public Tracking() {
    }

    
    public Tracking(int tracking_id, int team_id, int milestone_id, int function_id, int assigner_id, int assignee_id, String tracking_note, int status) {
        this.tracking_id = tracking_id;
        this.team_id = team_id;
        this.milestone_id = milestone_id;
        this.function_id = function_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.tracking_note = tracking_note;
        this.status = status;
    }

    public Tracking(int tracking_id, int team_id, int milestone_id, int function_id, int assigner_id, int assignee_id, String tracking_note, String updates, int status) {
        this.tracking_id = tracking_id;
        this.team_id = team_id;
        this.milestone_id = milestone_id;
        this.function_id = function_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.tracking_note = tracking_note;
        this.updates = updates;
        this.status = status;
    }

    public int getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(int tracking_id) {
        this.tracking_id = tracking_id;
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

    public int getFunction_id() {
        return function_id;
    }

    public void setFunction_id(int function_id) {
        this.function_id = function_id;
    }

    public int getAssigner_id() {
        return assigner_id;
    }

    public void setAssigner_id(int assigner_id) {
        this.assigner_id = assigner_id;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getTracking_note() {
        return tracking_note;
    }

    public void setTracking_note(String tracking_note) {
        this.tracking_note = tracking_note;
    }

    public String getUpdates() {
        return updates;
    }

    public void setUpdates(String updates) {
        this.updates = updates;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getAssigner_name() {
        return assigner_name;
    }

    public void setAssigner_name(String assigner_name) {
        this.assigner_name = assigner_name;
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

    public String getAccess_roles() {
        return access_roles;
    }

    public void setAccess_roles(String access_roles) {
        this.access_roles = access_roles;
    }

    

    
}
