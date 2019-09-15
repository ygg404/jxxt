package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.StageDao;
import cn.ux.jxxt.domain.ProjectStage;
import cn.ux.jxxt.dto.StageDTO;
import cn.ux.jxxt.service.ProjectStageService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectStageServiceImpl implements ProjectStageService {

    @Autowired
    private StageDao stageDao;

    @Autowired
    private LogDao logDao;

    @Override
    public StageDTO getStage() {
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看项目阶段"));
        StageDTO returnDTO = new StageDTO();
        returnDTO.setList(stageDao.queryStage());
        return returnDTO;
    }

    @Override
    public StageDTO insertStage(ProjectStage stage) {
        StageDTO returnDTO = new StageDTO();
        ProjectStage pStage = stageDao.queryByName(stage.getName());
        if(pStage != null){
            returnDTO.setError("该阶段名称已存在，请不要重复添加");
        }else{
            stageDao.insertStage(stage);
            returnDTO.setSuccess("新增成功");
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "添加项目阶段," + "阶段名称 : " + stage.getName()));
        return returnDTO;
    }

    @Override
    public StageDTO updateStage(Long stageId, ProjectStage stage) {
        StageDTO returnDTO = new StageDTO();
        ProjectStage pStage = stageDao.queryById(stageId);
        ProjectStage nStage = stageDao.queryByName(stage.getName());
        if(pStage == null){
            returnDTO.setError("查无id号为------" + stageId + "------的项目阶段。");
            return returnDTO;
        }else if(nStage != null){
            if(!stageId.equals(nStage.getId())){
                returnDTO.setError("名称为------" + stage.getName() + "------的项目阶段已存在");
                return returnDTO;
            }
        }
        stage.setId(pStage.getId());
        stageDao.updateStage(stage);
        returnDTO.setProjectStage(stageDao.queryById(stageId));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "更改项目阶段," + "原阶段名称 : " + stage.getName() + ",更改为 : " + returnDTO.getName()));
        return returnDTO;
    }

    @Override
    public StageDTO deleteStage(Long stageId) {
        StageDTO returnDTO = new StageDTO();
        if(stageDao.queryById(stageId) == null){
            returnDTO.setError("查无id号为------" + stageId + "------的项目阶段。");
        }else{
            stageDao.deleteById(stageId);
            returnDTO.setSuccess("删除成功");
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "删除项目阶段"));
        return returnDTO;
    }
}
