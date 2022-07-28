/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.sql.Date;
/**
 *
 * @author RxZ
 */
public class ClassUser {
    private int classId, teamId, userId, teamLeader;
    private Date dropOutDate;
    private String userNotes;
    private String onGoingEval, finalPresEval, finalTopicEval;
    private int status;
    
    // Other
    private String className, rollNumber, fullName, gender, dateOfBirth, Email, avatarLink, teamName;

    public ClassUser() {
    }

    public ClassUser(int classId, int teamId, int userId, int teamLeader, Date dropOutDate, String userNotes, String onGoingEval, String finalPresEval, String finalTopicEval, int status, String className, String rollNumber, String fullName, String gender, String dateOfBirth, String Email) {
        this.classId = classId;
        this.teamId = teamId;
        this.userId = userId;
        this.teamLeader = teamLeader;
        this.dropOutDate = dropOutDate;
        this.userNotes = userNotes;
        this.onGoingEval = onGoingEval;
        this.finalPresEval = finalPresEval;
        this.finalTopicEval = finalTopicEval;
        this.status = status;
        this.className = className;
        this.rollNumber = rollNumber;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.Email = Email;
    }

    public ClassUser(int classId, int teamId, int userId, int teamLeader, Date dropOutDate, String userNotes, String onGoingEval, String finalPresEval, String finalTopicEval, int status, String className, String rollNumber, String fullName, String gender, String dateOfBirth, String Email, String avatarLink) {
        this.classId = classId;
        this.teamId = teamId;
        this.userId = userId;
        this.teamLeader = teamLeader;
        this.dropOutDate = dropOutDate;
        this.userNotes = userNotes;
        this.onGoingEval = onGoingEval;
        this.finalPresEval = finalPresEval;
        this.finalTopicEval = finalTopicEval;
        this.status = status;
        this.className = className;
        this.rollNumber = rollNumber;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.Email = Email;
        this.avatarLink = avatarLink;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    
    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(int teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Date getDropOutDate() {
        return dropOutDate;
    }

    public void setDropOutDate(Date dropOutDate) {
        this.dropOutDate = dropOutDate;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }

    public String getOnGoingEval() {
        return (onGoingEval == null || "".equals(onGoingEval)) ? "0" : onGoingEval;
    }

    public void setOnGoingEval(String onGoingEval) {
        this.onGoingEval = onGoingEval;
    }

    public String getFinalPresEval() {
        return (finalPresEval == null || "".equals(finalPresEval)) ? "0" : finalPresEval;
    }

    public void setFinalPresEval(String finalPresEval) {
        this.finalPresEval = finalPresEval;
    }

    public String getFinalTopicEval() {
        return (finalTopicEval == null || "".equals(finalTopicEval)) ? "0" : finalTopicEval;
    }

    public void setFinalTopicEval(String finalTopicEval) {
        this.finalTopicEval = finalTopicEval;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "ClassUser{" + "classId=" + classId + ", teamId=" + teamId + ", userId=" + userId + ", teamLeader=" + teamLeader + ", dropOutDate=" + dropOutDate + ", userNotes=" + userNotes + ", onGoingEval=" + onGoingEval + ", finalPresEval=" + finalPresEval + ", finalTopicEval=" + finalTopicEval + ", status=" + status + ", className=" + className + ", rollNumber=" + rollNumber + ", fullName=" + fullName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", Email=" + Email + ", avatarLink=" + avatarLink + '}';
    }

}
