package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.ProjectTypeDao;
import cn.ux.jxxt.dao.WorkTypeDao;
import cn.ux.jxxt.domain.ProjectType;
import cn.ux.jxxt.domain.WorkProjectType;
import cn.ux.jxxt.domain.WorkType;
import cn.ux.jxxt.dto.WorkTypeDTO;
import cn.ux.jxxt.service.WorkTypeService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.PaginationUtil;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkTypeDao workTypeDao;

    @Autowired
    private LogDao logDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取所有类型
     * @return
     */
    @Override
    public WorkTypeDTO getAllType() {
        WorkTypeDTO returnDTO = new WorkTypeDTO();
        returnDTO.setTypeList(workTypeDao.getAllType());
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看所有工作类型"));
        return returnDTO;
    }

    /**
     * 添加工作类型
     * @param typeData
     * @return
     */
    @Override
    public WorkTypeDTO addType(WorkType typeData) {
        WorkTypeDTO returnDTO = new WorkTypeDTO();
        if (workTypeDao.getTypeByName(typeData.getTypeName()) != null) {
            returnDTO.setError("该工作类型已存在，请勿重复操作");
        } else {
            typeData.setStartTime(Timestamp.valueOf(sdf.format(new Date())));
            workTypeDao.insertWorkType(typeData);
            Long workid = workTypeDao.getTypeByName(typeData.getTypeName()).getId();
            //添加项目和工作类型之间的关系
            for(String ptype : typeData.getPtypeIdList()){
                WorkProjectType type = new WorkProjectType();
                type.setWtypeId(workid.intValue());
                type.setPtypeId(Integer.parseInt(ptype));
                workTypeDao.insertWorkProjectType(type);
            }
            returnDTO.setWorkType(workTypeDao.getTypeByName(typeData.getTypeName()));
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "添加工作类型"));
        return returnDTO;
    }

    /**
     * 更新工作类型
     * @param typeId
     * @param typeData
     * @return
     */
    @Override
    public WorkTypeDTO updateType(Long typeId, WorkType typeData) {
        WorkTypeDTO returnDTO = new WorkTypeDTO();
        WorkType type = workTypeDao.getTypeById(typeId);
        if (type == null) {
            returnDTO.setError("查无id号为-----" + typeId + "------的工作类型。");
            return returnDTO;
        }
        if (TextUtils.isEmpty(typeData.getTypeName())) {
            typeData.setTypeName(type.getTypeName());
        } else if (TextUtils.isEmpty(typeData.getTypeUnit())) {
            typeData.setTypeUnit(type.getTypeUnit());
        }
        typeData.setId(typeId);
        typeData.setUpdateTime(Timestamp.valueOf(sdf.format(new Date())));
        workTypeDao.updateById(typeData);

        //获取工作类型 与项目类型之间的关系数据 并删除
        workTypeDao.deleteWorkProjectType( Integer.parseInt( String.valueOf(typeId) ) );
        //更新工作类型 与项目类型之间的关系
        for(String ptypeId : typeData.getPtypeIdList()){
            WorkProjectType wProjectType = new WorkProjectType();
            wProjectType.setWtypeId(Integer.parseInt( String.valueOf(typeId)));
            wProjectType.setPtypeId(Integer.parseInt(ptypeId));
            workTypeDao.insertWorkProjectType(wProjectType);
        }

        returnDTO.setWorkType(workTypeDao.getTypeById(typeId));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "更新工作类型"));
        return returnDTO;
    }

    /**
     * 删除工作类型
     * @param typeId
     * @return
     */
    @Override
    public WorkTypeDTO deleteType(Long typeId) {
        WorkTypeDTO returnDTO = new WorkTypeDTO();
        if (workTypeDao.getTypeById(typeId) == null) {
            returnDTO.setError("查无id号为------" + typeId + "的工作类型");
        } else {
            workTypeDao.deleteType(typeId);
            returnDTO.setSuccess("删除成功");
        }
        //工作类型和项目类型关系删除
        workTypeDao.deleteWorkProjectType( Integer.parseInt(String.valueOf(typeId) ) ) ;

        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "删除工作类型"));
        return returnDTO;
    }

    /**
     * 获取所有工作类型(分页)
     * @param page          页数
     * @param per_page      条数
     * @param sortBy        排序条件
     * @param descending    排序规则
     * @param search        搜索项
     * @return
     */
    @Override
    public WorkTypeDTO getAllTypeByPaginated(int page, int per_page, String sortBy, boolean descending, String search) {
        WorkTypeDTO returnDTO = new WorkTypeDTO();
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("per_page",per_page);
        paramsMap.put("offset",(page-1) * per_page);
        paramsMap.put("sortBy",sortBy);
        paramsMap.put("desc",descending);
        if(!TextUtils.isEmpty(search)){
            paramsMap.put("search",search);
        }
        Long total = workTypeDao.getTypeNum(paramsMap);
        List<WorkType> types = workTypeDao.queryTypesPaginated(paramsMap);
        for(WorkType type : types){
            type.setTimeData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(type.getStartTime()));
            type.setTypeOut(TextUtils.saveTwoNum(type.getTypeOutput()));
            type.setPtypeList( workTypeDao.getWorkTypeById( Integer.parseInt(String.valueOf(type.getId()))) );
            //List<WorkProjectType> projectTypeList = workTypeDao.getWorkTypeById( Integer.parseInt(String.valueOf(type.getId())));
        }
        returnDTO.setTypePagination(PaginationUtil.paginate(page,per_page,total,types));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看所有工作类型"));
        return returnDTO;
    }
}
