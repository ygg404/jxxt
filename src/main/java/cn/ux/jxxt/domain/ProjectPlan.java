package cn.ux.jxxt.domain;

import java.util.List;

public class ProjectPlan {
    private Long id;
    private String projectExecuteStandard;          //执行标准
    private String projectWorkNote;                 //作业内容
    private String projectWorkRequire;             //技术要求
    private String projectWriter;               //编写人
    private String projectNo;                   //项目编码
    private String projectType;                 //项目类型
    private String projectNote;                 //项目要求
    private String projectAuthorize;            //委托单位
    private String projectCharge;               //项目负责人
    private String projectNum;
    private String userName;                    //联系人姓名
    private String userPhone;                   //联系人电话
    private String projectName;                 //项目名称
    private String projectSetUp;                //立项人
    private String projectBusiness;             //业务负责人
    private String projectAccount;              //负责人账号
    private String projectOutPut;               //预计产值
    private String projectOutPutNote;           //产值预算明细
    private String projectWorkLoad;             //预计工作量
    private String projectBegunDate;            //开工日期
    private String projectWorkDate;             //作业工期
    private String projectQualityDate;          //质检工期
    private List<PlanRate> rateList;
    private Long groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectExecuteStandard() {
        return projectExecuteStandard;
    }

    public void setProjectExecuteStandard(String projectExecuteStandard) {
        this.projectExecuteStandard = projectExecuteStandard;
    }

    public String getProjectWorkNote() {
        return projectWorkNote;
    }

    public void setProjectWorkNote(String projectWorkNote) {
        this.projectWorkNote = projectWorkNote;
    }

    public String getProjectWorkRequire() {
        return projectWorkRequire;
    }

    public void setProjectWorkRequire(String projectWorkRequire) {
        this.projectWorkRequire = projectWorkRequire;
    }

    public String getProjectWriter() {
        return projectWriter;
    }

    public void setProjectWriter(String projectWriter) {
        this.projectWriter = projectWriter;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
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

    public List<PlanRate> getRateList() {
        return rateList;
    }

    public void setRateList(List<PlanRate> rateList) {
        this.rateList = rateList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectSetUp() {
        return projectSetUp;
    }

    public void setProjectSetUp(String projectSetUp) {
        this.projectSetUp = projectSetUp;
    }

    public String getProjectBusiness() {
        return projectBusiness;
    }

    public void setProjectBusiness(String projectBusiness) {
        this.projectBusiness = projectBusiness;
    }

    public String getProjectNote() {
        return projectNote;
    }

    public void setProjectNote(String projectNote) {
        this.projectNote = projectNote;
    }

    public String getProjectAccount() {
        return projectAccount;
    }

    public void setProjectAccount(String projectAccount) {
        this.projectAccount = projectAccount;
    }

    public String getProjectOutPut() {
        return projectOutPut;
    }

    public void setProjectOutPut(String projectOutPut) {
        this.projectOutPut = projectOutPut;
    }

    public String getProjectOutPutNote() {
        return projectOutPutNote;
    }

    public void setProjectOutPutNote(String projectOutPutNote) {
        this.projectOutPutNote = projectOutPutNote;
    }

    public String getProjectWorkLoad() {
        return projectWorkLoad;
    }

    public void setProjectWorkLoad(String projectWorkLoad) {
        this.projectWorkLoad = projectWorkLoad;
    }

    public String getProjectBegunDate() {
        return projectBegunDate;
    }

    public void setProjectBegunDate(String projectBegunDate) {
        this.projectBegunDate = projectBegunDate;
    }

    public String getProjectWorkDate() {
        return projectWorkDate;
    }

    public void setProjectWorkDate(String projectWorkDate) {
        this.projectWorkDate = projectWorkDate;
    }

    public String getProjectQualityDate() {
        return projectQualityDate;
    }

    public void setProjectQualityDate(String projectQualityDate) {
        this.projectQualityDate = projectQualityDate;
    }
}
