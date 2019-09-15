package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.PlanRate;
import cn.ux.jxxt.domain.ProjectPlan;
import cn.ux.jxxt.vo.Pagination;

import java.util.List;

public class ProjectPlanDTO extends ProjectPlan {
    private List<ProjectPlan> lists;
    private Pagination<ProjectPlan> planPagination;
    private List<PlanRate> groupIds;
    private String success;
    private String error;
    private int pId;
    private String pNo;
    private float outpu;
    private ProjectPlan projectPlan;

    public List<ProjectPlan> getLists() {
        return lists;
    }

    public void setLists(List<ProjectPlan> lists) {
        this.lists = lists;
    }

    public Pagination<ProjectPlan> getPlanPagination() {
        return planPagination;
    }

    public void setPlanPagination(Pagination<ProjectPlan> planPagination) {
        this.planPagination = planPagination;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<PlanRate> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<PlanRate> groupIds) {
        this.groupIds = groupIds;
    }

    public ProjectPlan getProjectPlan() {
        return projectPlan;
    }

    public void setProjectPlan(ProjectPlan projectPlan) {
        this.projectPlan = projectPlan;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public float getOutpu() {
        return outpu;
    }

    public void setOutpu(float outpu) {
        this.outpu = outpu;
    }

    public String getpNo() {
        return pNo;
    }

    public void setpNo(String pNo) {
        this.pNo = pNo;
    }
}
