package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.ProjectStage;
import cn.ux.jxxt.dto.StageDTO;

public interface ProjectStageService {

    StageDTO getStage();

    StageDTO insertStage(ProjectStage stage);

    StageDTO updateStage(Long stageId, ProjectStage stage);

    StageDTO deleteStage(Long stageId);
}
