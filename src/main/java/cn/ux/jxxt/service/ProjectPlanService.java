package cn.ux.jxxt.service;

import cn.ux.jxxt.dto.ProjectPlanDTO;

public interface ProjectPlanService {

    ProjectPlanDTO addProjectPlan(ProjectPlanDTO projectPlanDTO);

    ProjectPlanDTO updateProjectPlan(ProjectPlanDTO projectPlanDTO);

    ProjectPlanDTO getPlanData(String projectNo);
}
