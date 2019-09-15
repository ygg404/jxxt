package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.ProjectDao;
import cn.ux.jxxt.dao.ProjectPlanDao;
import cn.ux.jxxt.dao.UserDao;
import cn.ux.jxxt.dao.WorkGroupDao;
import cn.ux.jxxt.domain.PlanRate;
import cn.ux.jxxt.domain.Project;
import cn.ux.jxxt.domain.ProjectPlan;
import cn.ux.jxxt.domain.WorkGroup;
import cn.ux.jxxt.domain.custom.UserWrap;
import cn.ux.jxxt.dto.ProjectPlanDTO;
import cn.ux.jxxt.service.ProjectPlanService;
import cn.ux.jxxt.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectPlanServiceImpl implements ProjectPlanService {

    @Autowired
    private ProjectPlanDao projectPlanDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private WorkGroupDao workGroupDao;

    @Autowired
    private UserDao userDao;

    @Override
    public ProjectPlanDTO addProjectPlan(ProjectPlanDTO projectPlanDTO) {
        ProjectPlanDTO returnDTO = new ProjectPlanDTO();
        Project project = projectDao.getProjectByNo(projectPlanDTO.getProjectNo());
        if (project == null) {
            returnDTO.setError("该项目不存在，请检查项目编码: " + projectPlanDTO.getProjectNo() + "  是否正确");
            return returnDTO;
        }
        Map<String, Object> groupParams = new HashMap<>();
        groupParams.put("p_no", projectPlanDTO.getProjectNo());
        if (projectPlanDTO.getGroupIds() != null) {                  //判断是否有布置作业组
            for (PlanRate planRate : projectPlanDTO.getGroupIds()) {    //遍历取数作业组数据
                WorkGroup workGroup = workGroupDao.queryGroupById(planRate.getId());    //根据id查询对应作业组数据
                if (workGroup == null) {
                    returnDTO.setError("查询不到id为 : " + planRate.getId() + "的作业组");
                    return returnDTO;
                }
                float output = (float)(planRate.getOutput_rate() * 0.01) * Float.parseFloat(projectPlanDTO.getProjectOutPut());
                groupParams.put("g_Id", planRate.getId());                  //添加组id
                groupParams.put("o_rate", planRate.getOutput_rate());       //添加比例
                groupParams.put("output", output);
                groupParams.put("shortDate",planRate.getShortDate());       //最短工期
                groupParams.put("lastDate",planRate.getLastDate());         //最迟工期
                projectPlanDao.addProjectGroups(groupParams);
            }
        }
        projectPlanDao.addProjectPlan(projectPlanDTO);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    @Override
    public ProjectPlanDTO updateProjectPlan(ProjectPlanDTO projectPlanDTO) {
        ProjectPlanDTO returnDTO = new ProjectPlanDTO();
        float chatOutput = 0;                                                                   //负责人所在队伍产值
        float allOutput = Float.parseFloat(projectPlanDTO.getProjectOutPut());
        String project = projectPlanDao.getWorkPlanData(projectPlanDTO.getProjectNo());         //获取项目数据
        List<WorkGroup> workList = workGroupDao.queryGroup();                               //获取所有作业组
        Map<String, Object> planParams = new HashMap<>();
        planParams.put("p_no", projectPlanDTO.getProjectNo());                          //添加项目编号
        for(UserWrap user : userDao.getUser()){                                     //遍历所有用户
            if(user.getUserName().equals(projectPlanDTO.getProjectCharge())) {          //根据用户名获取用户账号
                projectPlanDTO.setProjectAccount(user.getUserAccount());            //获取用户账号
            }
        }

        if (projectPlanDTO.getRateList() != null) {                         //判断是否有更新作业组比例
            projectPlanDao.deleteGroup(projectPlanDTO.getProjectNo());          //删除原有数据
            for (PlanRate planRate : projectPlanDTO.getRateList()) {                //获取比例数组
                if (workGroupDao.queryGroupById(planRate.getGroup_id()) == null) {    //获取单个作业组                                                    //判断是否存在该作业组
                    returnDTO.setError("查询不到id为 : " + planRate.getGroup_id() + "的作业组");
                    return returnDTO;
                }
//                if(Float.parseFloat(projectPlanDTO.getProjectOutPut()) > 20000){                //判断当前预算产值是否大于20000
//                    for(WorkGroup group : workList){                                            //遍历作业组
//                        if(projectPlanDTO.getProjectCharge().equals(group.getHeadMan())){           //如果负责人是这个队伍的
//                            projectPlanDTO.setGroupId(group.getId());                           //设置负责人所在作业组id
//                            //计算10%产值数据给负责人所在作业组
//                            chatOutput = (float)0.1 * Float.parseFloat(projectPlanDTO.getProjectOutPut());
//                            allOutput = Float.parseFloat(projectPlanDTO.getProjectOutPut()) - chatOutput;           //更新预算总产值
//                        }
//                    }
//                }
                float output = (float)(planRate.getOutput_rate() * 0.01) * allOutput; //根据各队所持有比例计算各队产值
                planParams.put("g_Id", planRate.getGroup_id());
                planParams.put("o_rate", planRate.getOutput_rate());
                if(projectPlanDTO.getGroupId() !=null && planRate.getGroup_id() == projectPlanDTO.getGroupId()){
                    planParams.put("output", output + chatOutput);
                }else{
                    planParams.put("output", output);
                }
                planParams.put("shortDate",planRate.getShortDate());       //最短工期
                planParams.put("lastDate",planRate.getLastDate());         //最迟工期
                projectPlanDao.addProjectGroups(planParams);
            }
        }
        if (TextUtils.isEmpty(project)) {                       //判断安排表里有没有存在数据，存在则更新，不存在则添加
            projectPlanDao.addProjectPlan(projectPlanDTO);
        }else{
            projectPlanDao.updatePlan(projectPlanDTO);
        }
        projectPlanDao.updateProjectAccount(projectPlanDTO.getProjectNo(),projectPlanDTO.getProjectAccount());
        returnDTO.setSuccess("更新成功");
        return returnDTO;
    }

    @Override
    public ProjectPlanDTO getPlanData(String projectNo) {
        ProjectPlanDTO returnDTO = new ProjectPlanDTO();
        if (projectDao.getProjectByNo(projectNo) == null) {
            returnDTO.setError("该项目不存在，请检查项目编码: " + projectNo + "  是否正确");
            return returnDTO;
        }
        returnDTO.setProjectPlan(projectPlanDao.getPlanData(projectNo));
        return returnDTO;
    }

}
