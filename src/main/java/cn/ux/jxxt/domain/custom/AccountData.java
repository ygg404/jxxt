package cn.ux.jxxt.domain.custom;

public class AccountData {
    private Long id;
    private String projectNo;                       //项目编号
    private String contractNo;                      //合同编号
    private String projectNum;                      //项目编码
    private String projectName;                     //项目名称
    private String projectType;                     //项目类型
    private String projectAuthorize;                //委托单位
    private String projectCharge;                   //业务负责人
    private String startDateTime;                   //开始时间
    private String projectStage;                    //项目阶段
    private String userName;                        //项目联系人
    private Float projectOutPut;                    //项目产值
    private Float projectReceivable;                //应收款项
    private Float projectActuallyReceipts;          //实收款项
    private Float projectNotReceipts;               //未收款项
    private Float projectExpenditure;               //支出款项

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

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
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

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
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

    public Float getProjectReceivable() {
        return projectReceivable;
    }

    public void setProjectReceivable(Float projectReceivable) {
        this.projectReceivable = projectReceivable;
    }

    public Float getProjectActuallyReceipts() {
        return projectActuallyReceipts;
    }

    public void setProjectActuallyReceipts(Float projectActuallyReceipts) {
        this.projectActuallyReceipts = projectActuallyReceipts;
    }

    public Float getProjectNotReceipts() {
        return projectNotReceipts;
    }

    public void setProjectNotReceipts(Float projectNotReceipts) {
        this.projectNotReceipts = projectNotReceipts;
    }

    public Float getProjectExpenditure() {
        return projectExpenditure;
    }

    public void setProjectExpenditure(Float projectExpenditure) {
        this.projectExpenditure = projectExpenditure;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
}
