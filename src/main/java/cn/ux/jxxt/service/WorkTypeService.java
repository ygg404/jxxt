package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.WorkType;
import cn.ux.jxxt.dto.WorkTypeDTO;

import java.util.List;

public interface WorkTypeService {
    WorkTypeDTO getAllType();

    WorkTypeDTO addType(WorkType typeData);

    WorkTypeDTO updateType(Long typeId, WorkType typeData);

    WorkTypeDTO deleteType(Long typeId);

    WorkTypeDTO getAllTypeByPaginated(int page, int per_page, String sortBy, boolean descending, String search);
}
