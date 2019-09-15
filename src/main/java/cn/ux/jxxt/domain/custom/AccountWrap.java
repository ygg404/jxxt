package cn.ux.jxxt.domain.custom;

import cn.ux.jxxt.domain.ProjectAccount;

import java.util.List;

public class AccountWrap {
    private String projectName;
    private Float projectMoney;
    private String projectType;
    private String projectNum;
    private String projectAuthorize;
    private String userName;
    private String userPhone;
    private String projectStage;
    private Float projectOutPut;
    private List<ProjectAccount> accounts;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Float getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(Float projectMoney) {
        this.projectMoney = projectMoney;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectAuthorize() {
        return projectAuthorize;
    }

    public void setProjectAuthorize(String projectAuthorize) {
        this.projectAuthorize = projectAuthorize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public Float getProjectOutPut() {
        return projectOutPut;
    }

    public void setProjectOutPut(Float projectOutPut) {
        this.projectOutPut = projectOutPut;
    }

    public List<ProjectAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<ProjectAccount> accounts) {
        this.accounts = accounts;
    }

}
