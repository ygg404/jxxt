package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.*;
import cn.ux.jxxt.domain.custom.AccountWrap;
import cn.ux.jxxt.domain.custom.OutPutWrap;
import cn.ux.jxxt.domain.custom.ProjectOutPutWrap;
import cn.ux.jxxt.domain.custom.ProjectWrap;
import cn.ux.jxxt.dto.ProjectDTO;
import cn.ux.jxxt.dto.ProjectPlanDTO;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectDao {

    /**
     * 添加项目
     * @param project
     * @return
     */
    public int addProject(Project project);

    /**
     * 根据项目编号查找项目
     * @param p_no
     * @return
     */
    public Project getProjectByNo(String p_no);

    /**
     * 添加项目联系人
     * @param params
     * @return
     */
    public int addProjectUser(Map<String, Object> params);

    /**
     * 添加项目类型与项目阶段
     * @param params
     * @return
     */
    public int addProjectType(Map<String, Object> params);

    /**
     * 获取项目立项详情
     * @param project_no
     * @return
     */
    public ProjectDTO getProjectBySetUp(String project_no);

    /**
     * 获取项目总数
     * @param params
     * @return
     */
    public Long getProjectCount(Map<String, Object> params);

    /**
     * 获取作业项目总数
     * @param params
     * @return
     */
    public Long getProjectCountByWork(Map<String,Object> params);

    /**
     * 获取财务统计项目总数
     * @param params
     * @return
     */
    public Long getProjectCountByAccount(Map<String,Object> params);

    /**
     * 获取回收站项目总数
     * @param params
     * @return
     */
    public Long getRecycleCount(Map<String, Object> params);

    /**
     * 获取项目分页数据
     * @param params
     * @return
     */
    public List<ProjectWrap> getProjects(Map<String, Object> params);

    /**
     * 获取项目分页作业阶段数据
     * @param params
     * @return
     */
    public List<ProjectWrap> getProjectsByWork(Map<String,Object> params);

    /**
     * 获取财务操作项目
     * @param params
     * @return
     */
    public List<ProjectWrap> getProjectsByAccount(Map<String,Object> params);

    /**
     * 获取回收站项目分页数据
     * @param params
     * @return
     */
    public List<ProjectWrap> getRecycleProject(Map<String, Object> params);

    /**
     * 根据用户账户获取所属项目数据
     * @param params
     * @return
     */
    public List<ProjectWrap> getProjectsFromAccount(Map<String, Object> params);

    /**
     * 添加项目立项人
     * @param params
     * @return
     */
    public int addSetUpUser(Map<String, Object> params);

    /**
     * 添加产值核算数据
     * @param params
     * @return
     */
    public int addOutPutData(Map<String, Object> params);

    /**
     * 删除产值核算数据
     * @param params
     */
    public void deleteOutPutData(Map<String, Object> params);
    /**
     * 添加项目审定数据
     * @param params
     * @return
     */
    public int addExamineNote(Map<String, Object> params);

    /**
     * 更新项目基本内容
     * @param project
     * @return
     */
    public int updateProject(Project project);

    /**
     * 根据合同号获取项目编号
     * @param contractNo
     * @return
     */
    public String getProjectNoByContractNo(String contractNo);

    /**
     * 更新项目联系人
     * @return
     */
    public int updateProjectUser(Map<String, Object> params);

    /**
     * 更新项目类型
     * @return
     */
    public int updateProjectType(Map<String, Object> params);

    /**
     * 删除项目
     * @return
     */
    public int deleteProject(String projectNo);

    /**
     * 删除项目联系人
     * @param projectNo
     * @return
     */
    public int deleteProjectUser(String projectNo);

    /**
     * 删除项目安排内容
     * @param projectNo
     * @return
     */
    public int deleteProjectPlan(String projectNo);

    /**
     * 删除安排内容中的工作组数据
     * @param projectNo
     * @return
     */
    public int deletePlanGroup(String projectNo);

    /**
     * 删除项目作业数据
     * @param projectNo
     * @return
     */
    public int deleteWork(String projectNo);

    /**
     * 删除质量检查数据
     * @param projectNo
     * @return
     */
    public int deleteQuality(String projectNo);

    /**
     * 移至回收站
     * @return
     */
    public int putToRecycle(Map<String, Object> params);

    /***
     * 根据Id获取作业类型数据
     * @param params
     * @return
     */
    public OutPutWrap getOutPutDataByTypeId(Map<String,Object> params);

    /**
     * 更新产值数据
     * @param params
     * @return
     */
    public int updateOutPutData(Map<String, Object> params);

    /**
     * 添加项目收支记录
     * @param params
     * @return
     */
    public int addProjectAccount(Map<String, Object> params);

    /**
     * 获取财务操作数据
     * @param project_no
     * @return
     */
    public AccountWrap getProjectAccount(String project_no);

    /**
     * 获取收支内容
     * @param project_no
     * @return
     */
    public List<ProjectAccount> getAccountNum(String project_no);

    /**
     * 获取作业组产值数据
     * @param project_no
     * @return
     */
    public List<OutPutWrap> getOutPut(String project_no);

    /**
     * 根据项目编码获取项目金额
     * @param p_no
     * @return
     */
    public Float getProjectMoney(String p_no);

    /**
     * 获取收支金额数据
     * @param project_no
     * @return
     */
    public List<ProjectAccount> getProjectAccountData(String project_no);

    /**
     * 根据项目编号获取项目联系人
     * @param project_no
     * @return
     */
    public ProjectUser getProjectUserByNo(String project_no);

    /**
     * 根据合同编号获取项目联系人
     * @param contract_no
     * @return
     */
    public ProjectUser getProjectUserBycNo(String contract_no);

    /**
     * 获取项目信息
     * @return
     */
    public List<BusinessData> getProjectInfo(Map<String,Object> params);

    /**
     * 获取所有业务负责人
     * @return
     */
    public List<String> getBusinessName();

    /**
     * 获取项目总未收款项
     * @return
     */
    public Float getProjectReceipts(String projectNo);

    /**
     * 更新项目阶段
     * @param params
     * @return
     */
    public int updateProjectStage(Map<String,Object> params);

    /**
     * 添加更新阶段数据
     * @param projectNo
     * @return
     */
    public int addProjectWork(String projectNo);

    /**
     * 根据编号查询项目作业数据
     * @param projectNo
     * @return
     */
    public String getProjectWorkByNo(String projectNo);

    /**
     * 新增项目检查质量数据
     * @param projectNo
     * @return
     */
    public int addProjectQuality(String projectNo);

    /**
     * 根据编号查询质量检查数据
     * @param projectNo
     * @return
     */
    public String getProjectQuality(String projectNo);

    /**
     * 新增项目安排数据
     * @param projectNo
     * @return
     */
    public int addProjectPlan(String projectNo);

    /**
     * 根据编号查询项目安排数据
     * @param projectNo
     * @return
     */
    public String getProjectPlan(String projectNo);

    /**
     * 获取角色列表
     * @param roleId
     * @return
     */
    public List<User> getProjectRole(String roleId);

    /**
     * 获取产值核算数据
     * @param projectNo
     * @return
     */
    public ProjectOutPutWrap getProjectOutPut(String projectNo);

    /**
     * 获取产值数据
     * @param projectNo
     * @return
     */
    public List<OutPutWrap> getOutPutList(String projectNo);

    /**
     * 新增产值核算数据
     * @param params
     * @return
     */
    public int addProjectOutput(Map<String,Object> params);

    /**
     * 根据编号查询产值核算数据
     * @param projectNo
     * @return
     */
    public String getProjectOutput(String projectNo);

    /**
     * 获取财务操作详情数据
     * @param projectNo
     * @return
     */
    public ProjectWrap getAccountInfo(String projectNo);

    /**
     * 根据id删除对应财务数据
     * @param accountId
     * @return
     */
    public int deleteAccount(Long accountId);

    /**
     * 根据项目编码获取项目进度数据
     * @param projectNo
     * @return
     */
    public ProjectSchedule getScheduleByNo(String projectNo,int groupId);

    /**
     * 作业阶段根据项目编码获取项目进度数据
     * @param projectNo
     * @return
     */
    public ProjectSchedule getWorkSchedule(String projectNo);

    /**
     * 根据id删除项目进度数据
     * @param scheduleId
     * @return
     */
    public int deleteSchedule(long scheduleId);

    /**
     * 添加项目进度数据
     * @param projectSchedule
     * @return
     */
    public int addSchedule(ProjectSchedule projectSchedule);

    /**
     * 根据id查找对应进度数据
     * @param scheduleId
     * @return
     */
    public ProjectSchedule getScheduleById(long scheduleId);

    /**
     * 更新项目进度数据
     * @param projectSchedule
     * @return
     */
    public int updateSchedule(ProjectSchedule projectSchedule);

    public long getProjectCounts();

    /**
     * 更新联系人信息表
     * @param params
     * @return
     */
    public int updateProjectUserOfContract(Map<String,Object> params);

    /**
     * 获取项目是否重复
     * @param projectNo
     * @return
     */
    public String getUserByProjectNo(String projectNo);

    /**
     * 获取新增的最后id
     * @return
     */
    public long getLastId();

    /**
     * 更换新合同编号
     * @param params
     * @return
     */
    public int updateProjectNo(Map<String,Object> params);

    /**
     * 添加返修意见
     * @param backWork
     * @return
     */
    public int addBackData(BackWork backWork);

    /**
     * 获取返修结果
     * @return
     */
    public List<BackWork> getBackData(String projectNo);

    /**
     * 更新作业返修id
     * @param params
     * @return
     */
    public int updateWorkBackStage(Map<String, Object> params);

    /**
     * 获取作业返修id
     * @param projectNo
     * @return
     */
    public String getWorkBackId(String projectNo);

    /**
     * 更新返修结果
     * @param backWork
     * @return
     */
    public int updateBackData(BackWork backWork);

    /**
     * 更新项目实际完成天数
     * @param project
     * @return
     */
    public int updatectuallyFinishNum(Project project);

    /**
     * 获取已有项目
     * @param params
     * @return
     */
    public BackWork getBackDataById(Map<String,Object> params);

    /**
     * 获取进度信息
     * @param params
     * @return
     */
    public List<ProjectSchedule> getScheduleDatalist(Map<String, Object> params);

    /**
     * 获取进度信息总数
     * @param params
     * @return
     */
    public Long getScheduleDataCount(Map<String, Object> params);

    /**
     * 获取项目返修总时间
     * @param projectNo
     * @return
     */
    public Long getBackNum(String projectNo);

    /**
     * 获取项目阶段在作业和质量检查的项目数量
     * @param params
     * @return
     */
    public Long getScheduleProjectCount(Map<String,Object> params);

    /**
     * 添加项目已核算总产值
     * @param params
     * @return
     */
    public int addAccountingOutput(Map<String,Object> params);

    /**
     * 更新项目已核算总产值
     * @param params
     * @return
     */
    public int updateAccountingOutput(Map<String,Object> params);

    /**
     * 获取已核算总产值
     * @param params
     * @return
     */
    public String getAccountingOutput(Map<String,Object> params);

    /**
     * 获取当月质量检查、产值核算、项目审定阶段的项目编号
     * @return
     */
    public List<OutPutData> getProjectNoByThisMonth();


    /**
     * 获取当月质量检查、产值核算、项目审定阶段的项目编号
     * @return
     */
    public List<OutPutData> getProjectNoBeforeMonth();

    /**
     * 获取作业阶段没有暂停的项目总产值
     * @return
     */
    public List<OutPutData> getProjectAtWorkNoStop();

    /**
     * 获取各组项目所占比例数据
     * @return
     */
    public List<PlanRate> getProjectRate(String projectNo);

    /**
     * 获取所有作业组数据
     * @return
     */
    public List<WorkGroup> getWorkGroup();

    /**
     * 获取当月处于作业阶段跟质检阶段的项目数据
     * @return
     */
    public List<String> getProjectByThisMonth();

    /**
     * 获取当前项目阶段进度小于90的数据
     * @return
     */
    public List<OutPutData> getProjectScheduleByWork(String projectNo);

    /**
     * 获取当前阶段位于作业阶段和质检阶段未暂停的项目
     * @return
     */
    public List<OutPutData> getProjectNoByWork();

    /**
     * 获取当前阶段位于质检阶段的项目
     * @return
     */
    public List<OutPutData> getProjectOnQuality();

    //查询带有返修意见得项目
    public List<String> getWorkBackOnly(int stageId);

    /**
     * 获取项目分页数据
     * @param params
     * @return
     */
    public ProjectWrap getProjectWorks(Map<String, Object> params);

    /**
     * 删除与合同绑定的项目
     * @param projectNo
     * @return
     */
    public int deleteContractToProject(String projectNo);

    /**
     * 获取该项目负责人所属队伍
     * @param chargeAccount
     * @return
     */
    public Long getProjectChargeGroup(String chargeAccount);

    /**
     * 添加实际产值
     * @param params
     * @return
     */
    public int updateActuallyOutPut(Map<String,Object> params);

    /**
     * 获取项目产值表总数
     * @return
     */
    public Long getProjectOutPutCount();

    /**
     * 获取项目分页数据
     * @param params
     * @return
     */
    public List<WorkGroup> getPOutPut(Map<String, Object> params);

    /**
     * 获取对应项目进度
     * @param projectNo
     * @return
     */
    public List<ProjectSchedule> getScheduleNoteByNo(String projectNo);

    /**
     * 保存上传文件的名称
     * @param projectFile
     * @param contractNo
     * @return
     */
    public int updateContractFile(String projectFile,String contractNo);

    /**
     * 获取文件名称
     * @param contractNo
     * @return
     */
    public String getContractFileName(String contractNo);

    /**
     * 获取该文件是否存在
     * @param contractNo
     * @return
     */
    public ContractData getContractByNo(String contractNo);

    /**
     * 根据合同编号添加文件
     * @param contractNo
     * @param fileName
     * @return
     */
    public int addContractByNo(String contractNo,String fileName);

    //根据项目编号查询对应的所在组id
    public String getProjectGroupId(String projectNo);

    /**
     * 查询项目负责人所在队数据
     * @param projectNo
     * @param groupId
     * @return
     */
//    public ProjectOutPutWrap getProjectOutPut(String projectNo,String groupId);
    public ProjectOutPutWrap getProjectOutPutData(String projectNo);

    public ProjectOutPutWrap getPrintWorkData(String projectNo,String groupId);

    /**
     * 获取所有参与部门的产值数据
     * @return
     */
    public List<OutPutWrap> getAllOutPutList(@Param("projectNo") String projectNo, @Param("groupId") long groupId);

    /**
     * 获取所有参与部门的数据
     * @return
     */
    public List<PlanRate> getAllGroupByProject(String projectNo);

    /**
     * 获取所有作业类型
     * @return
     */
    public List<OutPutWrap> getAllOutPutType();

    /**
     * 更新项目安排产值信息
     * @param params
     * @return
     */
    public int updatePlanOutput(Map<String ,Object> params);

    public List<WorkGroup> getProjectGroup(String projectNo);

    public int addNote(Map<String ,Object> params);

    public int updateoutput(ProjectPlan projectPlan);

    public Long getUserIdByUserName(String userName);

    public ProjectPlanDTO getProjectNoByGroup(int id);

    public int updatePOUtput(String projectNo,float output);

    public int addProjectMoney(Map<String,Object> params);

    public int updateRateOfsc(String projectNo);

    public Long getGroupIdByAccount(String userAccount);

    public int updatePlanWorkDate(Map<String,Object> params);

    public String getPlanWorkDate(String projectNo);

    public List<ProjectCoe> getProjectDataCoe();
}
