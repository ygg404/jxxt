package cn.ux.jxxt.domain;

import java.sql.Timestamp;

public class Project {
    private Long id;
    private String projectName;                 //项目名称
    private String projectNo;                   //项目编号
    private String projectNum;                  //项目编码
    private String projectType;                 //项目类型
    private Float projectMoney;                 //项目金额
    private String projectAuthorize;            //委托单位
    private String projectNote;                 //项目要求
    private String projectCharge;               //业务负责人
    private String projectProduce;              //生产负责人
    private String projectStage;                //项目阶段内容
    private int projectStageId;                //项目阶段代码
    private int pStage;                         //项目状态(1.正常;2.回收站)
    private String projectStartDateTime;        //项目开始时间
    private Timestamp projectCreateDateTime;    //项目创建时间
    private int projectActuallyFinishNum;       //项目实际完成天数
    private String projectCreate;

    public int getProjectActuallyFinishNum() {
        return projectActuallyFinishNum;
    }

    public void setProjectActuallyFinishNum(int projectActuallyFinishNum) {
        this.projectActuallyFinishNum = projectActuallyFinishNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Float getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(Float projectMoney) {
        this.projectMoney = projectMoney;
    }

    public String getProjectAuthorize() {
        return projectAuthorize;
    }

    public void setProjectAuthorize(String projectAuthorize) {
        this.projectAuthorize = projectAuthorize;
    }

    public String getProjectNote() {
        return projectNote;
    }

    public void setProjectNote(String projectNote) {
        this.projectNote = projectNote;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getProjectStartDateTime() {
        return projectStartDateTime;
    }

    public void setProjectStartDateTime(String projectStartDateTime) {
        this.projectStartDateTime = projectStartDateTime;
    }

    public Timestamp getProjectCreateDateTime() {
        return projectCreateDateTime;
    }

    public void setProjectCreateDateTime(Timestamp projectCreateDateTime) {
        this.projectCreateDateTime = projectCreateDateTime;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public int getProjectStageId() {
        return projectStageId;
    }

    public void setProjectStageId(int projectStageId) {
        this.projectStageId = projectStageId;
    }

    public int getpStage() {
        return pStage;
    }

    public void setpStage(int pStage) {
        this.pStage = pStage;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectProduce() {
        return projectProduce;
    }

    public void setProjectProduce(String projectProduce) {
        this.projectProduce = projectProduce;
    }

    public String getProjectCreate() {
        return projectCreate;
    }

    public void setProjectCreate(String projectCreate) {
        this.projectCreate = projectCreate;
    }
}
