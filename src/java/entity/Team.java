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
public class Team {
    int team_id;
    int class_id;
    String team_name;
    String topic_code;
    String topic_name;
    String gitlab_url;
    String project_id;
    String team_token;
    String class_code;
    int class_year;
    int block5_class;
    int status;
    

    public Team() {
    }


    public Team(int team_id, int class_id, String team_name, String topic_code, String topic_name, String gitlab_url, String class_code, int class_year, int block5_class, int status) {
        this.team_id = team_id;
        this.class_id = class_id;
        this.team_name = team_name;
        this.topic_code = topic_code;
        this.topic_name = topic_name;
        this.gitlab_url = gitlab_url;
        this.class_code = class_code;
        this.class_year = class_year;
        this.block5_class = block5_class;
        this.status = status;
    }

//    public Team(int team_id, int _class_id, String _topic_code, String _topic_name, String _gitlab_url, String _class_code, int _class_year, int _block5_class, int _status) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getTeam_token() {
        return team_token;
    }

    public void setTeam_token(String team_token) {
        this.team_token = team_token;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
    
    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public int getClass_year() {
        return class_year;
    }

    public void setClass_year(int class_year) {
        this.class_year = class_year;
    }

    public int getBlock5_class() {
        return block5_class;
    }

    public void setBlock5_class(int block5_class) {
        this.block5_class = block5_class;
    }

    

    public Team(int team_id, int class_id, String topic_code, String topic_name, String gitlab_url, int status) {
        this.team_id = team_id;
        this.class_id = class_id;
        this.topic_code = topic_code;
        this.topic_name = topic_name;
        this.gitlab_url = gitlab_url;
        this.status = status;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
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

    public String getGitlab_url() {
        return gitlab_url;
    }

    public void setGitlab_url(String gitlab_url) {
        this.gitlab_url = gitlab_url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
