package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.PlanRate;
import cn.ux.jxxt.domain.ProjectPlan;

import java.util.List;
import java.util.Map;

public interface ProjectPlanDao {

    /**
     * 添加项目安排内容
     * @param projectPlan
     * @return
     */
    public int addProjectPlan(ProjectPlan projectPlan);

    /**
     * 添加项目安排作业组数据
     * @param params
     * @return
     */
    public int addProjectGroups(Map<String, Object> params);

    /**
     * 更新项目安排数据
     * @param projectPlan
     * @return
     */
    public int updatePlan(ProjectPlan projectPlan);

    /**
     * 更新作业组数据
     * @param params
     * @return
     */
    public int updatePlanGroup(Map<String, Object> params);

    public ProjectPlan getPlanData(String project_no);

    public PlanRate getWorkRate(Map<String, Object> params);

    public String getWorkPlanData(String projectNo);

    public int deleteGroup(String projectNo);

    /**
     * 更新项目产值数据
     * @param params
     * @return
     */
    public int updatePlanOutPut(Map<String,Object> params);

    /**
     * 添加项目负责人账号
     * @param projectNo
     * @param chargeAccount
     * @return
     */
    public int updateProjectAccount(String projectNo,String chargeAccount);
}
