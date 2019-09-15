package cn.ux.jxxt.domain;

public class PlanRate {
    private Long id;
    private String project_no;
    private Long group_id;
    private Long output_rate;
    private float actuallyOutput;        //实际产值
    private String shortDate;           //最短工期
    private String lastDate;         //最迟工期
    private String groupName;
    private String projectName;     //项目名称
    private String projectType;     //项目类型
    private String finishDateTime;  //完成时间
    private String startDateTime;   //项目启动时间
    private String userName;
    private String qUserAccount;        //质检员账号
    private String cUserAccount;        //质量审核人账号
    private Float outputNum;        //产值
    private Float quality_score;    //质量评分
    private float project_output;           //项目预算产值
    private int project_rate;       //项目进度

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Long getOutput_rate() {
        return output_rate;
    }

    public void setOutput_rate(Long output_rate) {
        this.output_rate = output_rate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getProject_no() {
        return project_no;
    }

    public void setProject_no(String project_no) {
        this.project_no = project_no;
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

    public String getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(String finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Float getOutputNum() {
        return outputNum;
    }

    public void setOutputNum(Float outputNum) {
        this.outputNum = outputNum;
    }

    public Float getQuality_score() {
        return quality_score;
    }

    public void setQuality_score(Float quality_score) {
        this.quality_score = quality_score;
    }

    public String getShortDate() {
        return shortDate;
    }

    public void setShortDate(String shortDate) {
        this.shortDate = shortDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public float getProject_output() {
        return project_output;
    }

    public void setProject_output(float project_output) {
        this.project_output = project_output;
    }

    public float getActuallyOutput() {
        return actuallyOutput;
    }

    public void setActuallyOutput(float actuallyOutput) {
        this.actuallyOutput = actuallyOutput;
    }

    public String getqUserAccount() {
        return qUserAccount;
    }

    public void setqUserAccount(String qUserAccount) {
        this.qUserAccount = qUserAccount;
    }

    public String getcUserAccount() {
        return cUserAccount;
    }

    public void setcUserAccount(String cUserAccount) {
        this.cUserAccount = cUserAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProject_rate(){return  this.project_rate;}

    public void setProject_rate(int project_rate) {
        this.project_rate = project_rate;
    }
}
