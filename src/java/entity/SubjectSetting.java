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
public class SubjectSetting {
    int setting_id;
    int subject_id;
    int type_id;
    String setting_tile;
    String setting_value;
    String display_order;
    int status;

    public SubjectSetting() {
    }

    public SubjectSetting(int setting_id, int subject_id, int type_id, String setting_tile, String setting_value, String display_order, int status) {
        this.setting_id = setting_id;
        this.subject_id = subject_id;
        this.type_id = type_id;
        this.setting_tile = setting_tile;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
    }

    public SubjectSetting(int subject_id, int type_id, String setting_tile, String setting_value, String display_order, int status) {
        this.subject_id = subject_id;
        this.type_id = type_id;
        this.setting_tile = setting_tile;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
    }
    
    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getSetting_tile() {
        return setting_tile;
    }

    public void setSetting_tile(String setting_tile) {
        this.setting_tile = setting_tile;
    }

    public String getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(String setting_value) {
        this.setting_value = setting_value;
    }

    public String getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
