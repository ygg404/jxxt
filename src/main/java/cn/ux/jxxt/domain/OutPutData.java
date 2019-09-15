package cn.ux.jxxt.domain;


public class OutPutData {
    private String projectNo;               //项目编号
    private String projectName;             //项目名称
    private float projectOutPut;           //项目预算产值
    private long groupId;                   //作业组id
    private String groupName;               //作业组名
    private String groupHeadMan;            //作业组队长
    private int projectRate;                    //当前项目进度
    private float groupOutput;              //组总产值
    private int projectCount;               //未完成项目总数
    private float successOutPut;                //已完成产值
    private float groupNotSuccessOutPut;        //未完成产值

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public float getProjectOutPut() {
        return projectOutPut;
    }

    public void setProjectOutPut(float projectOutPut) {
        this.projectOutPut = projectOutPut;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupHeadMan() {
        return groupHeadMan;
    }

    public void setGroupHeadMan(String groupHeadMan) {
        this.groupHeadMan = groupHeadMan;
    }

    public int getProjectRate() {
        return projectRate;
    }

    public void setProjectRate(int projectRate) {
        this.projectRate = projectRate;
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

    public float getSuccessOutPut() {
        return successOutPut;
    }

    public void setSuccessOutPut(float successOutPut) {
        this.successOutPut = successOutPut;
    }

    public float getGroupNotSuccessOutPut() {
        return groupNotSuccessOutPut;
    }

    public void setGroupNotSuccessOutPut(float groupNotSuccessOutPut) {
        this.groupNotSuccessOutPut = groupNotSuccessOutPut;
    }
}
