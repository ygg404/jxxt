package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.ProjectWork;
import cn.ux.jxxt.dto.ProjectWorkDTO;

public interface ProjectWorkService {
    ProjectWorkDTO addProjectWork(ProjectWork projectWork);

    ProjectWorkDTO updateProjectWork(ProjectWork projectWork);

    ProjectWorkDTO getWorkData(String project_no);

    ProjectWorkDTO addFinishDateTime(ProjectWork projectWork);
}
