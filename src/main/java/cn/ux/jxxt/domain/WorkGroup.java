package cn.ux.jxxt.domain;

import cn.ux.jxxt.domain.custom.OutPutWrap;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class WorkGroup {
    private Long id;
    private String gName;                   //作业组名称
    @JsonIgnore
    private Timestamp createTime;              //创建时间
    private String TimeData;
    private String headMan;                 //组长
    private String deputyLeader;            //副组长
    private int projectSum;                 //项目数
    private String projectName;              //项目名称
    private Float OutPutNum;                //总产值
    private Float quality_score;            //质量评分
    private String quality_level;           //质量等级
    private int excellent;           //优
    private int good;                //良
    private int qualified;           //合格
    private Long fId;
    private String Excellent_rate;      //优良率
    private int Excellent_number;       //优良项目数
    private List<WorkGroup> workGroupList;
    private List<PlanRate> planRateList;
    private long deputyId;               //副队长id
    private long headId;                 //队长id
    private float groupOutput;              //组总产值
    private int projectCount;               //未完成项目总数
    private float successOutPut;                //已完成产值
    private float groupNotSuccessOutPut;        //未完成产值
    private float groupAtQuality;               //位于质检阶段的产值
    private double groupRate;                    //安排系数
    private String qUserName;
    private String cUserName;
    private String userName;
    private List<OutPutWrap> outPutWraps;           //各组产值数据

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getHeadMan() {
        return headMan;
    }

    public void setHeadMan(String headMan) {
        this.headMan = headMan;
    }

    public String getDeputyLeader() {
        return deputyLeader;
    }

    public void setDeputyLeader(String deputyLeader) {
        this.deputyLeader = deputyLeader;
    }

    public String getTimeData() {
        return TimeData;
    }

    public void setTimeData(String timeData) {
        TimeData = timeData;
    }

    public int getProjectSum() {
        return projectSum;
    }

    public void setProjectSum(int projectSum) {
        this.projectSum = projectSum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Float getOutPutNum() {
        return OutPutNum;
    }

    public void setOutPutNum(Float outPutNum) {
        OutPutNum = outPutNum;
    }

    public Float getQuality_score() {
        return quality_score;
    }

    public void setQuality_score(Float quality_score) {
        this.quality_score = quality_score;
    }

    public String getQuality_level() {
        return quality_level;
    }

    public void setQuality_level(String quality_level) {
        this.quality_level = quality_level;
    }

    public int getExcellent() {
        return excellent;
    }

    public void setExcellent(int excellent) {
        this.excellent = excellent;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getQualified() {
        return qualified;
    }

    public void setQualified(int qualified) {
        this.qualified = qualified;
    }

    public String getExcellent_rate() {
        return Excellent_rate;
    }

    public void setExcellent_rate(String excellent_rate) {
        Excellent_rate = excellent_rate;
    }

    public int getExcellent_number() {
        return Excellent_number;
    }

    public void setExcellent_number(int excellent_number) {
        Excellent_number = excellent_number;
    }

    public List<WorkGroup> getWorkGroupList() {
        return workGroupList;
    }

    public void setWorkGroupList(List<WorkGroup> workGroupList) {
        this.workGroupList = workGroupList;
    }

    public List<PlanRate> getPlanRateList() {
        return planRateList;
    }

    public void setPlanRateList(List<PlanRate> planRateList) {
        this.planRateList = planRateList;
    }

    public long getDeputyId() {
        return deputyId;
    }

    public void setDeputyId(long deputyId) {
        this.deputyId = deputyId;
    }

    public long getHeadId() {
        return headId;
    }

    public void setHeadId(long headId) {
        this.headId = headId;
    }

    public float getGroupOutput() {
        return groupOutput;
    }

    public void setGroupOutput(float groupOutput) {
        this.groupOutput = groupOutput;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public float getGroupNotSuccessOutPut() {
        return groupNotSuccessOutPut;
    }

    public void setGroupNotSuccessOutPut(float groupNotSuccessOutPut) {
        this.groupNotSuccessOutPut = groupNotSuccessOutPut;
    }

    public float getSuccessOutPut() {
        return successOutPut;
    }

    public void setSuccessOutPut(float successOutPut) {
        this.successOutPut = successOutPut;
    }

    public double getGroupRate() {
        return groupRate;
    }

    public void setGroupRate(double groupRate) {
        this.groupRate = groupRate;
    }

    public List<OutPutWrap> getOutPutWraps() {
        return outPutWraps;
    }

    public void setOutPutWraps(List<OutPutWrap> outPutWraps) {
        this.outPutWraps = outPutWraps;
    }

    public String getqUserName() {
        return qUserName;
    }

    public void setqUserName(String qUserName) {
        this.qUserName = qUserName;
    }

    public String getcUserName() {
        return cUserName;
    }

    public void setcUserName(String cUserName) {
        this.cUserName = cUserName;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getGroupAtQuality() {
        return groupAtQuality;
    }

    public void setGroupAtQuality(float groupAtQuality) {
        this.groupAtQuality = groupAtQuality;
    }
}
