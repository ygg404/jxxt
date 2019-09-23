package cn.ux.jxxt.domain;

import java.sql.Timestamp;

public class ProjectSchedule {
    private long id;
    private String projectName;                             //项目名称
    private Timestamp projectCreateTime;                    //创建日期
    private String projectActuallyFinishDate;               //实际总工期
    private int projectActuallyFinishNum;                   //实际总天数
    private int projectRate;                                //进度比率
    private String projectNo;                               //项目编号
    private String contractNo;                              //合同编号
    private String projectNote;                             //进度内容
    private String projectWorkDate;                            //作业天数
    private String projectQualityDate;                         //质量天数
    private long projectTotalDays;                           //实际总天数
    private float projectCountDays;                           //要求总天数
    private long wOutTime;                                       //作业超时间
    private long qOutTime;                                   //质量超时间
    private Timestamp wFinishDateTime;                      //作业首次提交时间
    private Timestamp qFinishDateTime;                      //质检检查完成时间
    private Timestamp cFinishDateTime;                      //结算时间
    private long backNum;                                    //返修时间
    private String scheduleCreateTime;                      //接口返回创建时间数据
    private String wFDateTime;                              //接口返回作业首次提交时间
    private String qFDateTime;                              //接口返回质检检查完成时间
    private String cDateTime;                               //结算时间
    private Timestamp projectBegunDate;                        //项目开工时间
    private String projectStartDate;                        //接口返回开工时间
    private int groupId;                                    //作业组id
    private String userAccount;                             //负责人账号

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Timestamp getProjectCreateTime() {
        return projectCreateTime;
    }

    public void setProjectCreateTime(Timestamp projectCreateTime) {
        this.projectCreateTime = projectCreateTime;
    }

    public int getProjectRate() {
        return projectRate;
    }

    public void setProjectRate(int projectRate) {
        this.projectRate = projectRate;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectNote() {
        return projectNote;
    }

    public void setProjectNote(String projectNote) {
        this.projectNote = projectNote;
    }

    public String getProjectActuallyFinishDate() {
        return projectActuallyFinishDate;
    }

    public void setProjectActuallyFinishDate(String projectActuallyFinishDate) {
        this.projectActuallyFinishDate = projectActuallyFinishDate;
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

    public Timestamp getwFinishDateTime() {
        return wFinishDateTime;
    }

    public void setwFinishDateTime(Timestamp wFinishDateTime) {
        this.wFinishDateTime = wFinishDateTime;
    }

    public Timestamp getqFinishDateTime() {
        return qFinishDateTime;
    }

    public void setqFinishDateTime(Timestamp qFinishDateTime) {
        this.qFinishDateTime = qFinishDateTime;
    }

    public long getBackNum() {
        return backNum;
    }

    public void setBackNum(long backNum) {
        this.backNum = backNum;
    }

    public String getScheduleCreateTime() {
        return scheduleCreateTime;
    }

    public void setScheduleCreateTime(String scheduleCreateTime) {
        this.scheduleCreateTime = scheduleCreateTime;
    }

    public String getwFDateTime() {
        return wFDateTime;
    }

    public void setwFDateTime(String wFDateTime) {
        this.wFDateTime = wFDateTime;
    }

    public String getqFDateTime() {
        return qFDateTime;
    }

    public void setqFDateTime(String qFDateTime) {
        this.qFDateTime = qFDateTime;
    }

    public int getProjectActuallyFinishNum() {
        return projectActuallyFinishNum;
    }

    public void setProjectActuallyFinishNum(int projectActuallyFinishNum) {
        this.projectActuallyFinishNum = projectActuallyFinishNum;
    }

    public Timestamp getProjectBegunDate() {
        return projectBegunDate;
    }

    public void setProjectBegunDate(Timestamp projectBegunDate) {
        this.projectBegunDate = projectBegunDate;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public long getProjectTotalDays() {
        return projectTotalDays;
    }

    public void setProjectTotalDays(long projectTotalDays) {
        this.projectTotalDays = projectTotalDays;
    }

    public float getProjectCountDays() {
        return projectCountDays;
    }

    public void setProjectCountDays(float projectCountDays) {
        this.projectCountDays = projectCountDays;
    }

    public long getwOutTime() {
        return wOutTime;
    }

    public void setwOutTime(long wOutTime) {
        this.wOutTime = wOutTime;
    }

    public long getqOutTime() {
        return qOutTime;
    }

    public void setqOutTime(long qOutTime) {
        this.qOutTime = qOutTime;
    }

    public Timestamp getcFinishDateTime(){return  this.cFinishDateTime;}

    public void setcFinishDateTime(Timestamp cFinishDateTime){this.cFinishDateTime = cFinishDateTime;}

    public String getcDateTime() { return  this.cDateTime;}

    public void setcDateTime(String cDateTime){ this.cDateTime = cDateTime;}
}

