package cn.ux.jxxt.domain.custom;

import cn.ux.jxxt.domain.BackWork;

import java.util.List;

public class ProjectWrap {
    private Long id;
    private String projectNo;
    private String projectNum;
    private String projectName;
    private String projectType;
    private String projectAuthorize;
    private String projectStartTime;
    private String projectStage;
    private String projectStageId;
    private String projectCharge;
    private String projectUser;
    private String projectSetUp;
    private String projectProduce;
    private String projectUserName;
    private String projectUserPhone;
    private String contractNo;                  //合同编号
    private String contractNote;                //作业内容及要求
    private String contractBusiness;            //业务负责人
    private float contractMoney;                //项目总金额
    private String projectRate;
    private String projectNote;
    private int groupId;
    private String groupName;                   //组名
    private long workStage;                     //项目状态  0:正常 1:暂停
    private Float projectOutPut;
    private String workName;
    private float projectActuallyOutput;
    private String chargeAccount;                   //项目负责人账号
    private boolean isCharge;
    private int group_id;
    private List<BackWork> backWorkList;

    public Float getProjectOutPut() {
        return projectOutPut;
    }

    public void setProjectOutPut(Float projectOutPut) {
        this.projectOutPut = projectOutPut;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public float getProjectActuallyOutput() {
        return projectActuallyOutput;
    }

    public void setProjectActuallyOutput(float projectActuallyOutput) {
        this.projectActuallyOutput = projectActuallyOutput;
    }

    public String getProjectProduce() {
        return projectProduce;
    }

    public void setProjectProduce(String projectProduce) {
        this.projectProduce = projectProduce;
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

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectAuthorize() {
        return projectAuthorize;
    }

    public void setProjectAuthorize(String projectAuthorize) {
        this.projectAuthorize = projectAuthorize;
    }

    public String getProjectStartTime() {
        return projectStartTime;
    }

    public void setProjectStartTime(String projectStartTime) {
        this.projectStartTime = projectStartTime;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getProjectUser() {
        return projectUser;
    }

    public void setProjectUser(String projectUser) {
        this.projectUser = projectUser;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectSetUp() {
        return projectSetUp;
    }

    public void setProjectSetUp(String projectSetUp) {
        this.projectSetUp = projectSetUp;
    }

    public String getProjectStageId() {
        return projectStageId;
    }

    public void setProjectStageId(String projectStageId) {
        this.projectStageId = projectStageId;
    }

    public String getProjectUserName() {
        return projectUserName;
    }

    public void setProjectUserName(String projectUserName) {
        this.projectUserName = projectUserName;
    }

    public String getProjectUserPhone() {
        return projectUserPhone;
    }

    public void setProjectUserPhone(String projectUserPhone) {
        this.projectUserPhone = projectUserPhone;
    }

    public String getContractNote() {
        return contractNote;
    }

    public void setContractNote(String contractNote) {
        this.contractNote = contractNote;
    }

    public String getContractBusiness() {
        return contractBusiness;
    }

    public void setContractBusiness(String contractBusiness) {
        this.contractBusiness = contractBusiness;
    }

    public float getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(float contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getProjectRate() {
        return projectRate;
    }

    public void setProjectRate(String projectRate) {
        this.projectRate = projectRate;
    }

    public String getProjectNote() {
        return projectNote;
    }

    public void setProjectNote(String projectNote) {
        this.projectNote = projectNote;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public long getWorkStage() {
        return workStage;
    }

    public void setWorkStage(long workStage) {
        this.workStage = workStage;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<BackWork> getBackWorkList() {
        return backWorkList;
    }

    public void setBackWorkList(List<BackWork> backWorkList) {
        this.backWorkList = backWorkList;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getChargeAccount() {
        return chargeAccount;
    }

    public void setChargeAccount(String chargeAccount) {
        this.chargeAccount = chargeAccount;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public boolean isCharge() {
        return isCharge;
    }

    public void setCharge(boolean charge) {
        isCharge = charge;
    }
}
