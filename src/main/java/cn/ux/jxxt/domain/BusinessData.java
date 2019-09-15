package cn.ux.jxxt.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class BusinessData {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String projectNo;               //项目编号
    private String projectBusiness;         //业务员
    private int projectNum;                 //项目数量
    private float projectMoney;             //应收款项
    @JsonIgnore
    private float projectAccount;           //实收款项
    private float projectNotReceipts;       //未收款项
    private float projectGetMoney;          //已收款项
    private String projectName;             //项目名称
    private List<BusinessData> list;

    public String getProjectBusiness() {
        return projectBusiness;
    }

    public void setProjectBusiness(String projectBusiness) {
        this.projectBusiness = projectBusiness;
    }

    public int getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }

    public float getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(float projectMoney) {
        this.projectMoney = projectMoney;
    }

    public float getProjectAccount() {
        return projectAccount;
    }

    public void setProjectAccount(float projectAccount) {
        this.projectAccount = projectAccount;
    }

    public float getProjectNotReceipts() {
        return projectNotReceipts;
    }

    public void setProjectNotReceipts(float projectNotReceipts) {
        this.projectNotReceipts = projectNotReceipts;
    }

    public float getProjectGetMoney() {
        return projectGetMoney;
    }

    public void setProjectGetMoney(float projectGetMoney) {
        this.projectGetMoney = projectGetMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<BusinessData> getList() {
        return list;
    }

    public void setList(List<BusinessData> list) {
        this.list = list;
    }
}
