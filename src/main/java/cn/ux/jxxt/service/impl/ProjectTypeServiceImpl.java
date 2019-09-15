package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.ProjectTypeDao;
import cn.ux.jxxt.domain.ProjectType;
import cn.ux.jxxt.dto.ProjectTypeDTO;
import cn.ux.jxxt.service.ProjectTypeService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.PaginationUtil;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    @Autowired
    private ProjectTypeDao projectTypeDao;

    @Autowired
    private LogDao logDao;

    @Override
    public ProjectTypeDTO getType() {
        ProjectTypeDTO projectTypeDTO = new ProjectTypeDTO();
        projectTypeDTO.setProjectLists(projectTypeDao.queryType());
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看项目类型"));
        return projectTypeDTO;
    }

    @Override
    public ProjectTypeDTO addType(ProjectTypeDTO projectType) {
        ProjectTypeDTO returnDTO = new ProjectTypeDTO();
        ProjectType pType = projectTypeDao.queryTypeByName(projectType.getName());
        if (pType != null) {
            returnDTO.setError("该项目类型名称已存在，请重新输入");
            return returnDTO;
        } else {
            projectTypeDao.insertType(projectType);
            returnDTO.setSuccess("新增成功");
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "新增项目类型，类型名称为 : " + projectType.getName()));
        return returnDTO;
    }

    @Override
    public ProjectTypeDTO updateType(Long typeId, ProjectTypeDTO projectTypeDTO) {
        ProjectTypeDTO returnDTO = new ProjectTypeDTO();
        ProjectType type = projectTypeDao.queryTypeById(typeId);
        if (type == null) {
            returnDTO.setError("查无id号为" + typeId + "的项目类型。");
        }
        ProjectType pType = projectTypeDao.queryTypeByName(projectTypeDTO.getName());
        if (pType != null) {
            returnDTO.setError("该项目类型名称已存在，请重新更改输入");
            return returnDTO;
        }
        projectTypeDTO.setId(type.getId());
        projectTypeDao.updateType(projectTypeDTO);
        returnDTO.setProjectType(projectTypeDao.queryTypeById(typeId));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "更改项目类型"));
        return returnDTO;
    }

    @Override
    public ProjectTypeDTO deleteType(Long typeId) {
        ProjectTypeDTO returnDTO = new ProjectTypeDTO();
        if (projectTypeDao.queryTypeById(typeId) == null) {
            returnDTO.setError("查无id号为" + typeId + "的项目类型。");
            return returnDTO;
        } else {
            projectTypeDao.deleteById(typeId);
            returnDTO.setSuccess("删除成功");
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "删除项目类型"));
        return returnDTO;
    }

    @Override
    public ProjectTypeDTO getTypeByPagination(int page, int per_page, String sortBy, boolean descending) {
        ProjectTypeDTO returnDTO = new ProjectTypeDTO();
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("per_page",per_page);
        paramsMap.put("offset",(page-1) * per_page);
        paramsMap.put("sortBy",sortBy);
        paramsMap.put("desc",descending);
        Long total = projectTypeDao.getTypeNum(paramsMap);
        List<ProjectType> types = projectTypeDao.queryTypesPaginated(paramsMap);
        returnDTO.setPagination(PaginationUtil.paginate(page,per_page,total,types));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看所有工作类型"));
        return returnDTO;
    }


}
