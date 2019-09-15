package cn.ux.jxxt.domain;


import java.sql.Timestamp;
import java.util.List;

public class ProjectWork {
    private Long id;
    private String disclosureNote;             //技术交底内容
    private String checkSuggestion;                     //过程检查意见
    private String dataName;                            //上交资料
    private String briefSummary;                        //工作小结
    private String workLoad;                            //工作量
    private Timestamp finishDateTime;                      //完成时间
    private String projectNo;                           //项目编号
    private ProjectPlan projectPlan;                //项目安排数据
    private String projectSetUp;                //项目立项人
    private String projectBusiness;             //业务负责人
    private String projectProduce;              //生产负责人
    private String projectName;                 //项目名称
    private String projectType;                 //项目类型
    private String projectStartTime;            //项目开始时间
    private int projectStage;                   //项目状态   0:正常   1:暂停
    private int groupId;                        //部门id
    private float projectRate;                  //项目进度
    private List<Long> groupsId;                //部门id列表
    private String projectBegunDate;            //开工日期

    public String getProjectBegunDate() {
        return projectBegunDate;
    }

    public void setProjectBegunDate(String projectBegunDate) {
        this.projectBegunDate = projectBegunDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Timestamp getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(Timestamp finishDateTime) {
        this.finishDateTime = finishDateTime;
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

    public String getProjectProduce() {
        return projectProduce;
    }

    public void setProjectProduce(String projectProduce) {
        this.projectProduce = projectProduce;
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

    public String getProjectStartTime() {
        return projectStartTime;
    }

    public void setProjectStartTime(String projectStartTime) {
        this.projectStartTime = projectStartTime;
    }

    public int getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(int projectStage) {
        this.projectStage = projectStage;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Long> getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(List<Long> groupsId) {
        this.groupsId = groupsId;
    }

    public float getProjectRate() {
        return projectRate;
    }

    public void setProjectRate(float projectRate) {
        this.projectRate = projectRate;
    }
}
