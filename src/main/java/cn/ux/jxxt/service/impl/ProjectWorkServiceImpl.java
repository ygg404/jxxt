package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.ProjectDao;
import cn.ux.jxxt.dao.ProjectWorkDao;
import cn.ux.jxxt.domain.Project;
import cn.ux.jxxt.domain.ProjectSchedule;
import cn.ux.jxxt.domain.ProjectWork;
import cn.ux.jxxt.dto.ProjectWorkDTO;
import cn.ux.jxxt.service.ProjectWorkService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ProjectWorkServiceImpl implements ProjectWorkService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectWorkDao projectWorkDao;

    @Autowired
    private LogDao logDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 添加项目工作内容
     * @param projectWork
     * @return
     */
    @Override
    public ProjectWorkDTO addProjectWork(ProjectWork projectWork) {
        ProjectWorkDTO returnDTO = new ProjectWorkDTO();
        Project project = projectDao.getProjectByNo(projectWork.getProjectNo());
        if(project == null){
            returnDTO.setError("项目不存在，请检查输入的项目编码是否正确");
            return returnDTO;
        }
        projectWorkDao.addProjectWork(projectWork);
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "添加项目工作内容"));
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    /**
     * 更新项目作业内容
     * @param projectWork
     * @return
     */
    @Override
    public ProjectWorkDTO updateProjectWork(ProjectWork projectWork) {
        ProjectWorkDTO returnDTO = new ProjectWorkDTO();
        Project project = projectDao.getProjectByNo(projectWork.getProjectNo());
        if(project == null){
            returnDTO.setError("项目不存在，请检查输入的项目编码是否正确");
            return returnDTO;
        }
        if(projectWorkDao.getWorkData(projectWork.getProjectNo()) != null) {
            projectWorkDao.updateWork(projectWork);
        }else{
            projectWorkDao.addProjectWork(projectWork);
        }
        returnDTO.setSuccess("更新成功");
        return returnDTO;
    }

    @Override
    public ProjectWorkDTO getWorkData(String project_no) {
        ProjectWorkDTO returnDTO = new ProjectWorkDTO();
        if(projectDao.getProjectByNo(project_no) == null){
            returnDTO.setError("项目不存在，请检查输入的项目编码是否正确");
            return returnDTO;
        }
        returnDTO.setProjectWork(projectWorkDao.getWorkData(project_no));
        ProjectSchedule projectSchedule =  projectDao.getWorkSchedule(project_no);
        if(projectSchedule != null){
            returnDTO.getProjectWork().setProjectRate(projectSchedule.getProjectRate());
            returnDTO.getProjectWork().setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp( returnDTO.getProjectWork().getProjectStartTime())));
        }
//        returnDTO.getProjectWork().setProjectRate(projectSchedule.getProjectRate());
//        returnDTO.getProjectWork().setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp( returnDTO.getProjectWork().getProjectStartTime())));
        return returnDTO;
    }

    @Override
    public ProjectWorkDTO addFinishDateTime(ProjectWork projectWork) {
        ProjectWorkDTO returnDTO = new ProjectWorkDTO();
        if(!TextUtils.isEmpty(projectWorkDao.getFinishDateTime(projectWork.getProjectNo()))){
            return returnDTO;
        }
        projectWork.setFinishDateTime(Timestamp.valueOf(sdf.format(new Date())));
        projectWorkDao.addFinishDateTime(projectWork);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }
}
