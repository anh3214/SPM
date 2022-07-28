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
public class Setting {
    int setting_id;
    int type_id;
    String setting_title;
    String setting_value;
    String display_order;
    int status;

    public Setting() {
    }

    public Setting(int setting_id, String setting_title) {
        this.setting_id = setting_id;
        this.setting_title = setting_title;
    }

    public Setting(int setting_id, int type_id, String setting_title, String setting_value, String display_order, int status) {
        this.setting_id = setting_id;
        this.type_id = type_id;
        this.setting_title = setting_title;
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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getSetting_title() {
        return setting_title;
    }

    public void setSetting_tile(String setting_title) {
        this.setting_title = setting_title;
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

    @Override
    public String toString() {
        return "setting{" + "setting_id=" + setting_id + ", type_id=" + type_id + ", setting_tile=" + setting_title + ", setting_value=" + setting_value + ", display_order=" + display_order + ", status=" + status + '}';
    }
    
}
