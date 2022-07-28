/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author RxZ
 */
public class Function {
    private int id, teamId, featureId, ownerId, statusId, complexityId, classId, subjectId;
    private String name, classCode, subjectCode, teamName, accessRoles, ownerName, statusName, statusColor, featureName, priority, description;
    
    public Function() {
    }

    public Function(int id, int teamId, int featureId, int ownerId, int statusId, int complexityId, int classId, String name, String classCode, String subjectCode, String teamName, String accessRoles, String ownerName, String statusName, String featureName, String priority, String description) {
        this.id = id;
        this.teamId = teamId;
        this.featureId = featureId;
        this.ownerId = ownerId;
        this.statusId = statusId;
        this.complexityId = complexityId;
        this.classId = classId;
        this.name = name;
        this.classCode = classCode;
        this.subjectCode = subjectCode;
        this.teamName = teamName;
        this.accessRoles = accessRoles;
        this.ownerName = ownerName;
        this.statusName = statusName;
        this.featureName = featureName;
        this.priority = priority;
        this.description = description;
        
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }
    
    

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
    
    

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
    
    

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getComplexityId() {
        return complexityId;
    }
    
    public String getComplexity() {
        if (complexityId == 2) return "Medium";
        if (complexityId == 3) return "Complex";
        return "Simple";
    }
    
    public void setComplexity(String complexity) {
        if ("complex".equalsIgnoreCase(complexity)) complexityId = 3;
        if ("medium".equalsIgnoreCase(complexity)) complexityId = 2;
        if ("simple".equalsIgnoreCase(complexity)) complexityId = 1;
    }

    public void setComplexityId(int complexityId) {
        this.complexityId = complexityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getAccessRoles() {
        return accessRoles;
    }

    public void setAccessRoles(String accessRoles) {
        this.accessRoles = accessRoles;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Function{" + "id=" + id + ", teamId=" + teamId + ", featureId=" + featureId + ", ownerId=" + ownerId + ", statusId=" + statusId + ", complexityId=" + complexityId + ", classId=" + classId + ", name=" + name + ", classCode=" + classCode + ", subjectCode=" + subjectCode + ", teamName=" + teamName + ", accessRoles=" + accessRoles + ", ownerName=" + ownerName + ", statusName=" + statusName + ", featureName=" + featureName + ", priority=" + priority + ", description=" + description + '}';
    }

    
    
}
