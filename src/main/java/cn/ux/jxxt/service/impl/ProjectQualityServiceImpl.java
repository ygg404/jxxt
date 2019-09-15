package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.CheckQualityDao;
import cn.ux.jxxt.dao.ProjectDao;
import cn.ux.jxxt.domain.CheckQuality;
import cn.ux.jxxt.domain.Project;
import cn.ux.jxxt.domain.ProjectSchedule;
import cn.ux.jxxt.domain.QualityScore;
import cn.ux.jxxt.dto.CheckQualityDTO;
import cn.ux.jxxt.service.ProjectQualityService;
import cn.ux.jxxt.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectQualityServiceImpl implements ProjectQualityService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private CheckQualityDao checkQualityDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 添加质量检查数据
     *
     * @param checkQuality
     * @return
     */
    @Override
    public CheckQualityDTO addQuality(CheckQuality checkQuality) {
        CheckQualityDTO returnDTO = new CheckQualityDTO();
        Project project = projectDao.getProjectByNo(checkQuality.getProjectNo());
        if (project == null) {
            returnDTO.setError("该项目不存在，请检查项目编码是否正确");
            return returnDTO;
        }
        checkQualityDao.addQualityData(checkQuality);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    /**
     * 更新质量检查数据
     *
     * @param checkQuality
     * @return
     */
    @Override
    public CheckQualityDTO updateQuality(CheckQuality checkQuality) {
        CheckQualityDTO returnDTO = new CheckQualityDTO();
        Project project = projectDao.getProjectByNo(checkQuality.getProjectNo());
        if (project == null) {
            returnDTO.setError("该项目不存在，请检查项目编码是否正确");
            return returnDTO;
        }
        checkQualityDao.updateQuality(checkQuality);
        if(TextUtils.isEmpty(checkQualityDao.getQualityAccount(checkQuality.getProjectNo()))){
            Map<String,Object> params = new HashMap<>();
            params.put("projectNo",checkQuality.getProjectNo());
            params.put("userAccount",checkQuality.getUserAccount());
            checkQualityDao.updateQualityFromQuser(params);
        }
        returnDTO.setSuccess("更新成功");
        return returnDTO;
    }

    /**
     * 获取质量检查内容
     * @param project_no
     * @return
     */
    @Override
    public CheckQualityDTO getQualityData(String project_no) {
        CheckQualityDTO returnDTO = new CheckQualityDTO();
        Project project = projectDao.getProjectByNo(project_no);
        if (project == null) {
            returnDTO.setError("该项目不存在，请检查项目编码是否正确");
            return returnDTO;
        }
        CheckQuality checkQuality = checkQualityDao.getQualityData(project_no);
        checkQuality.setProjectPlan(checkQualityDao.getPlanData(project_no));
        checkQuality.setProjectWork(checkQualityDao.getWorkData(project_no));
        returnDTO.setCheckQuality(checkQuality);
//        returnDTO.getCheckQuality().getProjectWork().setFinishDateTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp( returnDTO.getCheckQuality().getProjectWork().getFinishDateTime())));
//        returnDTO.getCheckQuality().getProjectWork().setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp( returnDTO.getCheckQuality().getProjectWork().getProjectStartTime())));
        return returnDTO;
    }

    @Override
    public CheckQualityDTO addQualityScore(CheckQualityDTO scores) {
        CheckQualityDTO returnDTO = new CheckQualityDTO();
        for(QualityScore qualityScore : scores.getScoreList()){
            checkQualityDao.addQualityScore(qualityScore);
        }
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    @Override
    public CheckQualityDTO updateQualityScore(CheckQualityDTO scores) {
//        CheckQualityDTO returnDTO = new CheckQualityDTO();
//        for(QualityScore qualityScore : scores.getScoreList()){
//            Map<String,Object> params = new HashMap<>();
//            params.put("project_no",qualityScore.getProject_no());
//            params.put("type_id",qualityScore.getType_id());
//            if(checkQualityDao.getQualityScoreByTypeId(params) != null){
//                checkQualityDao.updateQualityScore(qualityScore);
//            }else{
//                checkQualityDao.addQualityScore(qualityScore);
//            }
//        }
//        returnDTO.setSuccess("修改成功");
//        return returnDTO;
        CheckQualityDTO returnDTO = new CheckQualityDTO();
        //QualityScore qualityScore = scores.getQualityScore();
        QualityScore qualityScore = new QualityScore();
        qualityScore.setCheck_a(scores.getCheck_a());
        qualityScore.setCheck_b(scores.getCheck_b());
        qualityScore.setCheck_c(scores.getCheck_c());
        qualityScore.setCheck_d(scores.getCheck_d());
        qualityScore.setProject_no(scores.getProject_no());
        qualityScore.setCheck_result(scores.getCheck_result());
        qualityScore.setCheck_type(scores.getCheck_type());
        qualityScore.setType_id(scores.getType_id());
        Map<String,Object> params = new HashMap<>();
        params.put("project_no",qualityScore.getProject_no());
        params.put("type_id",qualityScore.getType_id());
        if(checkQualityDao.getQualityScoreByTypeId(params) != null){
            checkQualityDao.updateQualityScore(qualityScore);
        }else{
            checkQualityDao.addQualityScore(qualityScore);
        }

        returnDTO.setSuccess("修改成功");
        return returnDTO;
    }

    @Override
    public CheckQualityDTO getQualityScore(String projectNo) {
        CheckQualityDTO returnDTO = new CheckQualityDTO();
        returnDTO.setScoreList(checkQualityDao.getQualityScore(projectNo));
        return returnDTO;
    }

    @Override
    public CheckQualityDTO addFinishDateTime(CheckQuality checkQuality) {
        CheckQualityDTO returnDTO = new CheckQualityDTO();
        if(!TextUtils.isEmpty(checkQualityDao.getFinishDateTime(checkQuality.getProjectNo()))){
            returnDTO.setError("已成功添加时间");
            return returnDTO;
        }
        checkQuality.setFinishDateTime(Timestamp.valueOf(sdf.format(new Date())));
        checkQualityDao.addFinishDateTime(checkQuality);
        ProjectSchedule schedule = new ProjectSchedule();
        schedule.setProjectRate(100);
        schedule.setProjectNo(checkQuality.getProjectNo());
        projectDao.updateSchedule(schedule);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    @Override
    public CheckQualityDTO putToOutPut(CheckQuality checkQuality) {
        CheckQualityDTO returnDTO = new CheckQualityDTO();
        Map<String,Object> params = new HashMap<>();
        params.put("projectNo",checkQuality.getProjectNo());
        params.put("userAccount",checkQuality.getUserAccount());
        checkQualityDao.updateQualityFromUser(params);
        return returnDTO;
    }

    @Override
    public CheckQualityDTO changeFinishDateTime(CheckQuality checkQuality){
        CheckQualityDTO returnDTO = new CheckQualityDTO();
        Map<String,Object> params = new HashMap<>();
        checkQualityDao.addFinishDateTime(checkQuality);
        return returnDTO;
    }
}
