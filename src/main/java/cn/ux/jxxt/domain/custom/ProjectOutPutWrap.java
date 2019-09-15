package cn.ux.jxxt.domain.custom;

import cn.ux.jxxt.domain.WorkGroup;

import java.util.List;

public class ProjectOutPutWrap {
    private Long id;
    private String projectName;
    private String projectType;
    private String projectAuthorize;
    private String projectNote;
    private String projectUserName;
    private String projectPhone;
    private String projectCharge;           //项目负责人
    private String projectSetup;            //项目立项人
    private String projectWriter;           //项目编写人
    private String projectBusiness;         //业务负责人
    private String projectProduce;          //生产负责人
    private String projectStartTime;        //启动时间
    private String projectEndTime;          //完成时间
    private String projectStage;            //项目阶段
    private String projectExe;              //执行标准
    private String projectWorkNote;         //作业内容
    private String projectWorkRequire;      //技术要求
    private String disclosureNote;          //技术交底内容
    private String checkSuggestion;                     //过程检查意见
    private String dataName;                            //上交资料
    private String briefSummary;                        //工作小结
    private String workLoad;                            //工作量
    private String qualityNote;             //质量综述
    private String qualitySuggestion;           //质量检查意见
    private float qualityScore;            //质量分数
    private String qualityLevel;            //质量等级
    private List<OutPutWrap> outputList;     //产值数据
    private List<WorkGroup> groupList;          //部门数据
    private String examineNote;             //审定意见
    private String projectOutPut;               //预计产值
    private String projectOutPutNote;           //产值预算明细
    private String projectWorkLoad;             //预计工作量
    private String projectBegunDate;            //开工日期
    private int shortDateTime;                  //最短工期
    private int lastDateTime;                   //最长日期
    private String workName;                    //所属作业组

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
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

    public int getShortDateTime() {
        return shortDateTime;
    }

    public void setShortDateTime(int shortDateTime) {
        this.shortDateTime = shortDateTime;
    }

    public int getLastDateTime() {
        return lastDateTime;
    }

    public void setLastDateTime(int lastDateTime) {
        this.lastDateTime = lastDateTime;
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

    public String getProjectNote() {
        return projectNote;
    }

    public void setProjectNote(String projectNote) {
        this.projectNote = projectNote;
    }

    public String getProjectUserName() {
        return projectUserName;
    }

    public void setProjectUserName(String projectUserName) {
        this.projectUserName = projectUserName;
    }

    public String getProjectPhone() {
        return projectPhone;
    }

    public void setProjectPhone(String projectPhone) {
        this.projectPhone = projectPhone;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getProjectSetup() {
        return projectSetup;
    }

    public void setProjectSetup(String projectSetup) {
        this.projectSetup = projectSetup;
    }

    public String getProjectWriter() {
        return projectWriter;
    }

    public void setProjectWriter(String projectWriter) {
        this.projectWriter = projectWriter;
    }

    public String getProjectBusiness() {
        return projectBusiness;
    }

    public void setProjectBusiness(String projectBusiness) {
        this.projectBusiness = projectBusiness;
    }

    public String getProjectProduce() {
        return projectProduce;
    }

    public void setProjectProduce(String projectProduce) {
        this.projectProduce = projectProduce;
    }

    public String getProjectStartTime() {
        return projectStartTime;
    }

    public void setProjectStartTime(String projectStartTime) {
        this.projectStartTime = projectStartTime;
    }

    public String getProjectEndTime() {
        return projectEndTime;
    }

    public void setProjectEndTime(String projectEndTime) {
        this.projectEndTime = projectEndTime;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public String getProjectExe() {
        return projectExe;
    }

    public void setProjectExe(String projectExe) {
        this.projectExe = projectExe;
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

    public String getDisclosureNote() {
        return disclosureNote;
    }

    public void setDisclosureNote(String disclosureNote) {
        this.disclosureNote = disclosureNote;
    }

    public String getCheckSuggestion() {
        return checkSuggestion;
    }

    public void setCheckSuggestion(String checkSuggestion) {
        this.checkSuggestion = checkSuggestion;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getBriefSummary() {
        return briefSummary;
    }

    public void setBriefSummary(String briefSummary) {
        this.briefSummary = briefSummary;
    }

    public String getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(String workLoad) {
        this.workLoad = workLoad;
    }

    public String getQualityNote() {
        return qualityNote;
    }

    public void setQualityNote(String qualityNote) {
        this.qualityNote = qualityNote;
    }

    public String getQualitySuggestion() {
        return qualitySuggestion;
    }

    public void setQualitySuggestion(String qualitySuggestion) {
        this.qualitySuggestion = qualitySuggestion;
    }

    public float getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(float qualityScore) {
        this.qualityScore = qualityScore;
    }

    public String getQualityLevel() {
        return qualityLevel;
    }

    public void setQualityLevel(String qualityLevel) {
        this.qualityLevel = qualityLevel;
    }

    public List<OutPutWrap> getOutputList() {
        return outputList;
    }

    public void setOutputList(List<OutPutWrap> outputList) {
        this.outputList = outputList;
    }

    public String getExamineNote() {
        return examineNote;
    }

    public void setExamineNote(String examineNote) {
        this.examineNote = examineNote;
    }

    public List<WorkGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<WorkGroup> groupList) {
        this.groupList = groupList;
    }
}
