package cn.ux.jxxt.domain;

import java.sql.Timestamp;

public class ContractData {
    private Long id;
    private String contractNo;                  //合同编号
    private String projectNo;                   //项目编号
    private String contractAddTime;             //添加时间
    private String contraceCreate;
    private Timestamp contractCreateTime;          //创建时间
    private String contractAuthorize;           //签订单位
    private String contractName;                //合同名称
    private long typeId;                        //0合同委托(有合同)，1一般委托(无合同)
    private String contractNote;                //作业内容及要求
    private String contractBusiness;            //业务负责人
    private float contractMoney;                //项目总金额
    private String contractUserName;            //联系人姓名
    private String contractUserPhone;           //联系人电话
    private String projectType;                  //项目类型
    private String fileName;                    //项目名称
    private int StageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractAddTime() {
        return contractAddTime;
    }

    public void setContractAddTime(String contractAddTime) {
        this.contractAddTime = contractAddTime;
    }

    public Timestamp getContractCreateTime() {
        return contractCreateTime;
    }

    public void setContractCreateTime(Timestamp contractCreateTime) {
        this.contractCreateTime = contractCreateTime;
    }

    public String getContractAuthorize() {
        return contractAuthorize;
    }

    public void setContractAuthorize(String contractAuthorize) {
        this.contractAuthorize = contractAuthorize;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
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

    public String getContractUserName() {
        return contractUserName;
    }

    public void setContractUserName(String contractUserName) {
        this.contractUserName = contractUserName;
    }

    public String getContractUserPhone() {
        return contractUserPhone;
    }

    public void setContractUserPhone(String contractUserPhone) {
        this.contractUserPhone = contractUserPhone;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContraceCreate() {
        return contraceCreate;
    }

    public void setContraceCreate(String contraceCreate) {
        this.contraceCreate = contraceCreate;
    }

    public int getStageId() {
        return StageId;
    }

    public void setStageId(int stageId) {
        StageId = stageId;
    }
}
