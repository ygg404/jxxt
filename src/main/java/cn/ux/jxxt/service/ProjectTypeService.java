package cn.ux.jxxt.service;

import cn.ux.jxxt.dto.ProjectTypeDTO;

public interface ProjectTypeService {
    ProjectTypeDTO getType();

    ProjectTypeDTO addType(ProjectTypeDTO projectType);

    ProjectTypeDTO updateType(Long typeId, ProjectTypeDTO projectType);

    ProjectTypeDTO deleteType(Long typeId);

    ProjectTypeDTO getTypeByPagination(int page, int per_page, String sortBy, boolean descending);
}
