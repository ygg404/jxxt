package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.*;
import cn.ux.jxxt.domain.custom.*;
import cn.ux.jxxt.domain.custom.OutPutWrap;
import cn.ux.jxxt.domain.custom.ProjectWrap;
import cn.ux.jxxt.vo.Pagination;

import java.util.List;

public class ProjectDTO extends Project {
    private Long id;
    private String userName;                                //项目联系人
    private String userPhone;                               //联系人电话
    private String contractNo;                              //合同编号
    private Pagination<ProjectWrap> projectPagination;      //分页数据
    private Pagination<AccountData> dataPagination;         //财务操作分页数据
    private Pagination<ProjectSchedule> schedulePagination; //项目进度分页数据
    private Long projectTypeId;                             //项目类型id
    private List<OutPutWrap> outPutWrap;                          //产值核算
    private List<ProjectAccount> projectAccounts;            //财务数据
    private AccountWrap accountWrap;                        //财务数据输出内容
    private String examineNote;                             //审定内容
    private String error;
    private String success;
    private List<BusinessData> projectCart;                 //业务员统计汇总表数据
    private List<User> roleUser;            //角色用户数据
    private ProjectOutPutWrap projectOutPutWrap;
    private ProjectWrap projectWrap;                                //产值数据
    private float projectOutput;                            //已核算总产值
    private ProjectAccount projectAccount;
    private ProjectSchedule projectSchedule;
    private List<Project> projectList;
    private List<ProjectSchedule> projectSchedules;             //项目进度数据列表
    private List<BackWork> backList;                        //项目返修结果
    private List<WorkGroup> groupList;                  //作业组数据
    private long groupId;                               //作业组id
    private EditeNote editeNote;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public Pagination<ProjectWrap> getProjectPagination() {
        return projectPagination;
    }

    public void setProjectPagination(Pagination<ProjectWrap> projectPagination) {
        this.projectPagination = projectPagination;
    }

    public Long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<OutPutWrap> getOutPutWrap() {
        return outPutWrap;
    }

    public void setOutPutWrap(List<OutPutWrap> outPutWrap) {
        this.outPutWrap = outPutWrap;
    }

    public String getExamineNote() {
        return examineNote;
    }

    public void setExamineNote(String examineNote) {
        this.examineNote = examineNote;
    }

    public List<ProjectAccount> getProjectAccounts() {
        return projectAccounts;
    }

    public void setProjectAccounts(List<ProjectAccount> projectAccounts) {
        this.projectAccounts = projectAccounts;
    }

    public AccountWrap getAccountWrap() {
        return accountWrap;
    }

    public void setAccountWrap(AccountWrap accountWrap) {
        this.accountWrap = accountWrap;
    }

    public Pagination<AccountData> getDataPagination() {
        return dataPagination;
    }

    public void setDataPagination(Pagination<AccountData> dataPagination) {
        this.dataPagination = dataPagination;
    }

    public List<BusinessData> getProjectCart() {
        return projectCart;
    }

    public void setProjectCart(List<BusinessData> projectCart) {
        this.projectCart = projectCart;
    }

    public List<User> getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(List<User> roleUser) {
        this.roleUser = roleUser;
    }

    public ProjectOutPutWrap getProjectOutPutWrap() {
        return projectOutPutWrap;
    }

    public void setProjectOutPutWrap(ProjectOutPutWrap projectOutPutWrap) {
        this.projectOutPutWrap = projectOutPutWrap;
    }

    public ProjectWrap getProjectWrap() {
        return projectWrap;
    }

    public void setProjectWrap(ProjectWrap projectWrap) {
        this.projectWrap = projectWrap;
    }

    public ProjectAccount getProjectAccount() {
        return projectAccount;
    }

    public void setProjectAccount(ProjectAccount projectAccount) {
        this.projectAccount = projectAccount;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<ProjectSchedule> getProjectSchedules() {
        return projectSchedules;
    }

    public void setProjectSchedules(List<ProjectSchedule> projectSchedules) {
        this.projectSchedules = projectSchedules;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public List<BackWork> getBackList() {
        return backList;
    }

    public void setBackList(List<BackWork> backList) {
        this.backList = backList;
    }

    public Pagination<ProjectSchedule> getSchedulePagination() {
        return schedulePagination;
    }

    public void setSchedulePagination(Pagination<ProjectSchedule> schedulePagination) {
        this.schedulePagination = schedulePagination;
    }

    public float getProjectOutput() {
        return projectOutput;
    }

    public void setProjectOutput(float projectOutput) {
        this.projectOutput = projectOutput;
    }

    public List<WorkGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<WorkGroup> groupList) {
        this.groupList = groupList;
    }

    public ProjectSchedule getProjectSchedule() {
        return projectSchedule;
    }

    public void setProjectSchedule(ProjectSchedule projectSchedule) {
        this.projectSchedule = projectSchedule;
    }

    public EditeNote getEditeNote() {
        return editeNote;
    }

    public void setEditeNote(EditeNote editeNote) {
        this.editeNote = editeNote;
    }
}
