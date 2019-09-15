package cn.ux.jxxt.domain;

import cn.ux.jxxt.domain.custom.OutPutWrap;

import java.sql.Timestamp;
import java.util.List;

public class CheckQuality {
    private Long id;
    private String checkSuggestion;
    private String qualityNote;
    private Float qualityScore;
    private String projectExamineNote;              //审定内容
    private String projectNo;
    private String userAccount;                 //登录账号
    private Timestamp finishDateTime;           //完成时间
    private ProjectPlan projectPlan;
    private ProjectWork projectWork;
    private int groupId;                    //组id
    private List<OutPutWrap> outPutWrap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckSuggestion() {
        return checkSuggestion;
    }

    public void setCheckSuggestion(String checkSuggestion) {
        this.checkSuggestion = checkSuggestion;
    }

    public String getQualityNote() {
        return qualityNote;
    }

    public void setQualityNote(String qualityNote) {
        this.qualityNote = qualityNote;
    }

    public Float getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Float qualityScore) {
        this.qualityScore = qualityScore;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public ProjectPlan getProjectPlan() {
        return projectPlan;
    }

    public void setProjectPlan(ProjectPlan projectPlan) {
        this.projectPlan = projectPlan;
    }

    public ProjectWork getProjectWork() {
        return projectWork;
    }

    public void setProjectWork(ProjectWork projectWork) {
        this.projectWork = projectWork;
    }

    public List<OutPutWrap> getOutPutWrap() {
        return outPutWrap;
    }

    public void setOutPutWrap(List<OutPutWrap> outPutWrap) {
        this.outPutWrap = outPutWrap;
    }

    public String getProjectExamineNote() {
        return projectExamineNote;
    }

    public void setProjectExamineNote(String projectExamineNote) {
        this.projectExamineNote = projectExamineNote;
    }

    public Timestamp getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(Timestamp finishDateTime) {
        this.finishDateTime = finishDateTime;
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
}
