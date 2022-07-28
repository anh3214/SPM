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
public class ClassSetting {
    int class_setting_id;
    int type_id;
    String type_title;
    int type_value;
    String color;
    int class_id;
    String description;
    String class_code;
    String setting_title;

    public ClassSetting() {
    }

    public ClassSetting(int class_setting_id, int type_id, String type_title, int type_value, String color, int class_id, String description) {
        this.class_setting_id = class_setting_id;
        this.type_id = type_id;
        this.type_title = type_title;
        this.type_value = type_value;
        this.color = color;
        this.class_id = class_id;
        this.description = description;
    }

    public ClassSetting(int class_setting_id, int type_id, String type_title, int type_value, String color, int class_id, String description, String class_code, String setting_title) {
        this.class_setting_id = class_setting_id;
        this.type_id = type_id;
        this.type_title = type_title;
        this.type_value = type_value;
        this.color = color;
        this.class_id = class_id;
        this.description = description;
        this.class_code = class_code;
        this.setting_title = setting_title;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getSetting_title() {
        return setting_title;
    }

    public void setSetting_title(String setting_title) {
        this.setting_title = setting_title;
    }

 

    public int getClass_setting_id() {
        return class_setting_id;
    }

    public void setClass_setting_id(int class_setting_id) {
        this.class_setting_id = class_setting_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_title() {
        return type_title;
    }

    public void setType_title(String type_title) {
        this.type_title = type_title;
    }

    public int getType_value() {
        return type_value;
    }

    public void setType_value(int type_value) {
        this.type_value = type_value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
