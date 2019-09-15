package cn.ux.jxxt.domain;

public class ChartData {
    private int id;
    private String projectNo;                       //项目编号
    private String projectName;                     //项目名称
    private String projectAuthorize;                //委托单位
    private String userName;                        //联系人姓名
    private String userPhone;                       //联系电话
    private Float projectReceivable;                //应收款项
    private Float projectActuallyReceipts;          //实收款项
    private Float projectNotReceipts;               //未收款项
    private String projectCharge;                   //业务负责人
    private Float projectMoney;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public Float getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(Float projectMoney) {
        this.projectMoney = projectMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }
}
