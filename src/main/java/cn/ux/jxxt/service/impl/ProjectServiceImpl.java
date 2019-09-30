package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.*;
import cn.ux.jxxt.domain.*;
import cn.ux.jxxt.domain.custom.*;
import cn.ux.jxxt.domain.custom.OutPutWrap;
import cn.ux.jxxt.domain.custom.ProjectWrap;
import cn.ux.jxxt.domain.custom.UserWrap;
import cn.ux.jxxt.dto.OutPutDTO;
import cn.ux.jxxt.dto.ProjectDTO;
import cn.ux.jxxt.dto.ProjectPlanDTO;
import cn.ux.jxxt.dto.WorkGroupDTO;
import cn.ux.jxxt.service.ProjectService;
import cn.ux.jxxt.util.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Value("${file.upContractFolder}")
    private String upContractFolder;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectTypeDao projectTypeDao;

    @Autowired
    private ProjectPlanDao projectPlanDao;

    @Autowired
    private WorkGroupDao workGroupDao;

    @Autowired
    private LogDao logDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 添加项目
     *
     * @param userId
     * @param project
     * @return
     */
    @Override
    public ProjectDTO addProject(Long userId, ProjectDTO project) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> userParams = new HashMap<>();
        Map<String, Object> setUpParams = new HashMap<>();
        Map<String,Object> projectParams = new HashMap<>();
        Project sProject = projectDao.getProjectByNo(project.getProjectNo());
        if (sProject != null) {
            returnDTO.setError("项目编号为: " + project.getProjectNo() + "------已存在，请重新输入");
            return returnDTO;
        }
        project.setpStage(1);                                               //设置项目状态，1.正常;2.回收站
        project.setProjectNo(NumUtil.generateNumber());
        project.setProjectCreateDateTime(Timestamp.valueOf(sdf.format(new Date())));
        projectDao.addProject(project);                                                 //添加项目
        String projectNo = NumUtil.createProjectNo() + projectDao.getLastId();                    //设置唯一标识项目码
        projectParams.put("oldNo",project.getProjectNo());
        projectParams.put("projectNo",projectNo);
        projectDao.updateProjectNo(projectParams);                                  //更新项目编码
        userParams.put("projectNo", projectNo);                // 添加项目编号
        userParams.put("contractNo", project.getContractNo());                  //添加合同编号
        if(TextUtils.isEmpty(projectDao.getUserByProjectNo(project.getProjectNo()))){
            setUpParams.put("user_id", userId);
            setUpParams.put("project_no", projectNo);
            projectDao.addSetUpUser(setUpParams);                                           //添加立项人
        }
        projectDao.updateProjectUserOfContract(userParams);
        returnDTO.setProjectNo(projectNo);//添加项目联系人
//        returnDTO.setSuccess("" + project.getProjectNo());
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "添加项目"));
        return returnDTO;
    }

    /**
     * 获取项目立项数据
     * @return
     */

    @Override
    public ProjectDTO getProjectBySetUp(String project_no) {
        ProjectDTO returnDTO = new ProjectDTO();
        if (projectDao.getProjectByNo(project_no) == null) {
            returnDTO.setError("项目编号为: " + project_no + "的项目不存在，请重新检查是否正确");
            return returnDTO;
        }
        returnDTO = projectDao.getProjectBySetUp(project_no);
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看项目立项"));
        return returnDTO;
    }

    @Override
    public ProjectDTO getProjects(int page, int per_page, String sortBy, boolean descending, String search,
                                  int p_stage,String startDate,String endDate,String stageId,String account) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if(!TextUtils.isEmpty(stageId.trim())){
            params.put("stageId",stageId);
        }
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        if(!TextUtils.isEmpty(account.trim())){
            params.put("userAccount",account);
        }
        params.put("p_stage", p_stage);                                //项目是否在回收站判断，1.正常，2.回收站
        Long total = projectDao.getProjectCount(params);
        List<ProjectWrap> projects = projectDao.getProjects(params);
        List<BackWork> backWorkList = new ArrayList<>();
        for(ProjectWrap projectWrap :projects){
            projectWrap.setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(projectWrap.getProjectStartTime())));
            if(projectDao.getBackData(projectWrap.getProjectNo()) .size()>0) {
                backWorkList = projectDao.getBackData(projectWrap.getProjectNo());
                for(BackWork backWork : backWorkList){
                    backWork.setBackcreatedtime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(backWork.getBackCreatedTime().toString())));
                }
                projectWrap.setBackWorkList(backWorkList);
            }
        }
        returnDTO.setProjectPagination(PaginationUtil.paginate(page, per_page, total, projects));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看项目"));
        return returnDTO;
    }

    @Override
    public ProjectDTO getProjectsByWork(int page, int per_page, String sortBy, boolean descending, String search, int p_stage, String startDate, String endDate, String stageId, String account) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if(!TextUtils.isEmpty(stageId.trim())){
            params.put("stageId",stageId);
        }
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        if(!TextUtils.isEmpty(account.trim())){
            params.put("groupId",projectDao.getGroupIdByAccount(account));
        }else{
            params.put("groupId",0);
        }
        params.put("p_stage", p_stage);                                //项目是否在回收站判断，1.正常，2.回收站
        Long total = projectDao.getProjectCountByWork(params);
        List<ProjectWrap> projects = projectDao.getProjectsByWork(params);
        List<BackWork> backWorkList = new ArrayList<>();
        for(ProjectWrap projectWrap :projects){
            projectWrap.setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(projectWrap.getProjectStartTime())));
            if(projectDao.getBackData(projectWrap.getProjectNo()) .size()>0) {
                backWorkList = projectDao.getBackData(projectWrap.getProjectNo());
                for(BackWork backWork : backWorkList){
                    backWork.setBackcreatedtime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(backWork.getBackCreatedTime().toString())));
                }
                projectWrap.setBackWorkList(backWorkList);
            }

            account = account.toLowerCase().trim();
            if(!TextUtils.isEmpty(account)){
                if(account.equals(projectWrap.getChargeAccount())){
                    projectWrap.setCharge(true);
                }else{
                    projectWrap.setCharge(false);
                }
            }else{
                projectWrap.setCharge(true);
            }
        }
        returnDTO.setProjectPagination(PaginationUtil.paginate(page, per_page, total, projects));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看项目"));
        return returnDTO;
    }

    @Override
    public ProjectDTO getRecycleProjects(int page, int per_page, String sortBy, boolean descending, String search, int p_stage, String startDate, String endDate, String stageId, String account) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if(!TextUtils.isEmpty(stageId.trim())){
            params.put("stageId",stageId);
        }
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        if(!TextUtils.isEmpty(account.trim())){
            params.put("userAccount",account);
        }
        params.put("p_stage", p_stage);                                //项目是否在回收站判断，1.正常，2.回收站
        Long total = projectDao.getRecycleCount(params);
        List<ProjectWrap> projects = projectDao.getRecycleProject(params);
        List<BackWork> backWorkList = new ArrayList<>();
        for(ProjectWrap projectWrap :projects){
            projectWrap.setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(projectWrap.getProjectStartTime())));
            if(projectDao.getBackData(projectWrap.getProjectNo()) .size()>0) {
                backWorkList = projectDao.getBackData(projectWrap.getProjectNo());
                for(BackWork backWork : backWorkList){
                    backWork.setBackcreatedtime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(backWork.getBackCreatedTime().toString())));
                }
                projectWrap.setBackWorkList(backWorkList);
            }
        }
        returnDTO.setProjectPagination(PaginationUtil.paginate(page, per_page, total, projects));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看回收站项目"));
        return returnDTO;
    }

    @Override
    public ProjectDTO getProjectsByAccounts(int page, int per_page, String sortBy, boolean descending, String search, int p_stage, String startDate, String endDate, String stageId, String userAccount) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if(!TextUtils.isEmpty(stageId.trim())){
            params.put("stageId",stageId);
        }
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        params.put("userAccount",userAccount);
        params.put("p_stage", p_stage);                                //项目是否在回收站判断，1.正常，2.回收站
        Long total = projectDao.getProjectCount(params);
        List<ProjectWrap> projects = projectDao.getProjectsFromAccount(params);
        for(ProjectWrap projectWrap :projects){
            projectWrap.setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(projectWrap.getProjectStartTime())));
        }
        returnDTO.setProjectPagination(PaginationUtil.paginate(page, per_page, total, projects));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看项目"));
        return returnDTO;
    }

    @Override
    public ProjectDTO getProjectAccounts(int page, int per_page, String sortBy, boolean descending, String search,
                                         int p_stage,String startDate,String endDate,String stageId) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if(!TextUtils.isEmpty(stageId.trim())){
            params.put("stageId",stageId);
        }
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        params.put("p_stage", p_stage);                                //项目是否在回收站判断，1.正常，2.回收站
        Long total = projectDao.getProjectCountByAccount(params);
        List<ProjectWrap> projects = projectDao.getProjectsByAccount(params);
        List<AccountData> accountData = new ArrayList<>();
        for(ProjectWrap projectWrap : projects){
            Float totalMoney = projectDao.getProjectMoney(projectWrap.getProjectNo());       //总金额
            if(totalMoney == null){
                totalMoney = 0.0f;
            }
            ProjectUser projectUser = projectDao.getProjectUserByNo(projectWrap.getProjectNo());
            Float getMoney = 0f;        //已收
            Float expenditure = 0f;     //支出
            Float projectOutPut = 0f;
            for(ProjectAccount projectAccount : projectDao.getProjectAccountData(projectWrap.getProjectNo())){
                if(projectAccount.getAccountType() == 0){           //0为收款数据，1为支出数据
                    getMoney += projectAccount.getAccountNum();
                }else{
                    expenditure += projectAccount.getAccountNum();
                }
            }
            for(OutPutWrap outPutWrap : projectDao.getOutPut(projectWrap.getProjectNo())){
                float output =  outPutWrap.getWorkLoad() * outPutWrap.getProjectRatio();
                projectOutPut += output;
            }
            AccountData data = new AccountData();
            data.setId(projectWrap.getId());
            data.setProjectNo(projectWrap.getProjectNo());
            data.setContractNo(projectWrap.getContractNo());
            data.setProjectNum(projectWrap.getProjectNum());
            data.setProjectName(projectWrap.getProjectName());
            data.setProjectType(projectWrap.getProjectType());
            data.setProjectAuthorize(projectWrap.getProjectAuthorize());
            data.setProjectCharge(projectWrap.getProjectSetUp());
            data.setProjectStage(projectWrap.getProjectStage());
            data.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(projectWrap.getProjectStartTime())));
            data.setProjectOutPut(projectOutPut);
            data.setUserName(projectUser.getUserName());
            data.setProjectReceivable(totalMoney);
            if(totalMoney == null){
                data.setProjectNotReceipts(0.0f);
            }else{
                data.setProjectNotReceipts(totalMoney - getMoney);
            }
            data.setProjectActuallyReceipts(getMoney);
            data.setProjectExpenditure(expenditure);
            accountData.add(data);

        }
        returnDTO.setDataPagination(PaginationUtil.paginate(page, per_page, total, accountData));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看项目"));
        return returnDTO;
    }

    /**
     * 添加项目产值
     *
     * @param projectDTO
     * @return
     */
    @Override
    public ProjectDTO addOutPut(ProjectDTO projectDTO) {
        ProjectDTO returnDTO = new ProjectDTO();
        float output = 0;
        float allOutPut = 0;
        ProjectPlan projectPlan = projectPlanDao.getPlanData(projectDTO.getProjectNo());
        List<PlanRate> rateList = projectDao.getProjectRate(projectDTO.getProjectNo());
        if (projectDao.getProjectByNo(projectDTO.getProjectNo()) == null) {
            returnDTO.setError("项目编号为: " + projectDTO.getProjectNo() + "的项目不存在，请重新检查是否正确");
            return returnDTO;
        }
        Map<String, Object> params = new HashMap<>();
        for(WorkGroup workGroup : projectDTO.getGroupList()){
            for(OutPutWrap outPutWrap : workGroup.getOutPutWraps()){
                params.put("typeId", outPutWrap.getId());
                params.put("projectNo", projectDTO.getProjectNo());
                params.put("projectRatio", outPutWrap.getProjectRatio());
                params.put("workLoad", outPutWrap.getWorkLoad());
                params.put("groupId", workGroup.getId());
                if(outPutWrap.isCheck()) {
                    if(projectDao.getOutPutDataByTypeId(params) == null){                   //判断该项目下的各个子项产值
                        projectDao.addOutPutData(params);
                    }else{
                        projectDao.updateOutPutData(params);
                    }
                }else{
                    //作业类型没有勾选则删除
                    if(projectDao.getOutPutDataByTypeId(params) != null) {                   //判断该项目下的各个子项产值
                        projectDao.deleteOutPutData(params);
                    }
                }
            }
        }
        for (OutPutWrap putWrap : projectDTO.getOutPutWrap()) {
//            if(projectPlan != null) {         //如果项目安排表格存在数据
                Map<String,Object> outputWrap = new HashMap<>();
                outputWrap.put("projectNo",projectDTO.getProjectNo());
                outputWrap.put("groupId",putWrap.getGroupId());
                outputWrap.put("actuallyOutPut",putWrap.getProjectOutPut());
                projectDao.updateActuallyOutPut(outputWrap);
                allOutPut += putWrap.getProjectOutPut();
        }
        Map<String,Object> planOut = new HashMap<>();
        planOut.put("projectNo",projectDTO.getProjectNo());
        planOut.put("actuallyOutPut",allOutPut);
        projectDao.updatePlanOutput(planOut);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    /**
     * 更新产值数据
     * @param projectDTO
     * @return
     */
    @Override
    public ProjectDTO updateOutPut(ProjectDTO projectDTO) {
        ProjectDTO returnDTO = new ProjectDTO();
        if (projectDao.getProjectByNo(projectDTO.getProjectNo()) == null) {
            returnDTO.setError("项目编号为: " + projectDTO.getProjectNo() + "的项目不存在，请重新检查是否正确");
            return returnDTO;
        }
        Map<String, Object> params = new HashMap<>();
        for (OutPutWrap putWrap : projectDTO.getOutPutWrap()) {
            params.put("typeId", putWrap.getTypeId());
            params.put("projectNo", projectDTO.getProjectNo());
            params.put("projectRatio", putWrap.getProjectRatio());
            params.put("workLoad", putWrap.getWorkLoad());
            if(putWrap.isCheck()) {
                if(projectDao.getOutPutDataByTypeId(params) == null){                   //判断该项目下的各个子项产值
                    projectDao.addOutPutData(params);
                }else{
                    projectDao.updateOutPutData(params);
                }
            }else{
                //作业类型没有勾选则删除
                if(projectDao.getOutPutDataByTypeId(params) != null) {                   //判断该项目下的各个子项产值
                    projectDao.deleteOutPutData(params);
                }
            }
        }
        returnDTO.setSuccess("更新成功");
        return returnDTO;
    }

    /**
     * 添加领导核算
     *
     * @param projectDTO
     * @return
     */
    @Override
    public ProjectDTO addExamineNote(ProjectDTO projectDTO) {
        ProjectDTO returnDTO = new ProjectDTO();
        if (projectDao.getProjectByNo(projectDTO.getProjectNo()) == null) {
            returnDTO.setError("项目编号为: " + projectDTO.getProjectNo() + "的项目不存在，请重新检查是否正确");
            return returnDTO;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("p_no", projectDTO.getProjectNo());
        params.put("project_note", projectDTO.getExamineNote());
        projectDao.addExamineNote(params);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    /**
     * 更新项目
     *
     * @param projectDTO
     * @return
     */
    @Override
    public ProjectDTO updateProject(ProjectDTO projectDTO) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> userParams = new HashMap<>();
        Map<String, Object> typeParams = new HashMap<>();
        if (projectDao.getProjectByNo(projectDTO.getProjectNo()) == null) {
            returnDTO.setError("项目编号为: " + projectDTO.getProjectNo() + " 不存在，请重新检查");
        }
        projectDao.updateProject(projectDTO);
        //根据项目类型名称判断是否更新项目类型
        if (projectDTO.getProjectType() != null) {
            boolean isType = true;
            String[] typelist = projectDTO.getProjectType().split(",");
            for(String type : typelist){
                if(projectTypeDao.queryTypeByName(type) == null){
                    isType = false;
                    break;
                }
            }

            if (isType) {
                typeParams.put("type_name", projectDTO.getProjectType());                          //项目类型
                typeParams.put("p_no", projectDTO.getProjectNo());
                projectDao.updateProjectType(typeParams);
            } else {
                returnDTO.setError("项目类型出错");
                return returnDTO;
            }
        }
        //根据联系人判断是否要更新联系人数据
        if (!TextUtils.isEmpty(projectDTO.getUserName()) || !TextUtils.isEmpty(projectDTO.getUserPhone())) {
            userParams.put("userName", projectDTO.getUserName());
            userParams.put("userPhone", projectDTO.getUserPhone());
            userParams.put("projectNo", projectDTO.getProjectNo());
            projectDao.updateProjectUser(userParams);
        }

        projectDTO.setSuccess("更新数据成功");
        return projectDTO;
    }

    /**
     * 删除项目数据
     *
     * @param projectNo
     * @return
     */
    @Override
    public ProjectDTO deleteProject(String projectNo) {
        ProjectDTO returnDTO = new ProjectDTO();
        if (projectDao.getProjectByNo(projectNo) == null) {
            returnDTO.setError("项目编号为: " + projectNo + " 不存在，请重新检查");
            return returnDTO;
        }
        projectDao.deleteProject(projectNo);
        projectDao.deleteProjectUser(projectNo);
        projectDao.deleteProjectPlan(projectNo);
        projectDao.deletePlanGroup(projectNo);
        projectDao.deleteWork(projectNo);
        projectDao.deleteQuality(projectNo);
        projectDao.deleteContractToProject(projectNo);
        returnDTO.setSuccess("删除成功");
        return returnDTO;
    }

    /**
     * 回收站操作
     *
     * @param projectNo
     * @param stageId
     * @return
     */
    @Override
    public ProjectDTO putProjectToRecycle(String projectNo, int stageId) {
        ProjectDTO returnDTO = new ProjectDTO();
        if (projectDao.getProjectByNo(projectNo) == null) {
            returnDTO.setError("项目编号为: " + projectNo + " 不存在，请重新检查");
            return returnDTO;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("pStage", stageId);
        params.put("projectNo", projectNo);
        projectDao.putToRecycle(params);
        returnDTO.setSuccess("修改成功");
        return returnDTO;
    }

    /**
     * 添加项目财务数据
     * @param projectDTO
     * @return
     */
    @Override
    public ProjectDTO addProjectAccount(ProjectDTO projectDTO) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String,Object> params = new HashMap<>();
        if(projectDao.getProjectByNo(projectDTO.getProjectNo()) == null){
            returnDTO.setError("项目编号为: " + projectDTO.getProjectNo() + " 不存在，请重新检查");
            return returnDTO;
        }
        params.put("projectNo",projectDTO.getProjectNo());
        params.put("createDateTime",Timestamp.valueOf(sdf.format(new Date())));
        for(ProjectAccount projectAccount : projectDTO.getProjectAccounts()){
            if(projectAccount.getAccountType() == 0){
                params.put("typeName","收款");
            }else{
                params.put("typeName","支出");
            }
            params.put("accountType",projectAccount.getAccountType());
            params.put("accountNote",projectAccount.getAccountNote());
            params.put("accountNum",projectAccount.getAccountNum());
            projectDao.addProjectAccount(params);
        }
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    /**
     * 获取财务编辑数据
     * @param project_no
     * @return
     */
    @Override
    public ProjectDTO getProjectAccount(String project_no) {
        ProjectDTO returnDTO = new ProjectDTO();
        float projectOutPut = 0;
        if(projectDao.getProjectByNo(project_no) == null){
            returnDTO.setError("项目编号为: " + project_no + " 不存在，请重新检查");
            return returnDTO;
        }
        AccountWrap accountWrap = projectDao.getProjectAccount(project_no);
        accountWrap.setAccounts(projectDao.getAccountNum(project_no));
        for(OutPutWrap outPutWrap : projectDao.getOutPut(project_no)){
            float output =  outPutWrap.getWorkLoad() * outPutWrap.getProjectRatio();
            projectOutPut += output;
        }
        for(ProjectAccount account : accountWrap.getAccounts()){
            account.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(account.getStartDateTime())));
        }
        accountWrap.setProjectOutPut(projectOutPut);
        returnDTO.setAccountWrap(accountWrap);
        return returnDTO;
    }

    /**
     * 获取业务员统计汇总表数据
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public ProjectDTO getProjectBusiness(String startDate, String endDate) {
        ProjectDTO returnDTO = new ProjectDTO();
        List<String> businessName = projectDao.getBusinessName();           //获取所有业务员姓名

        List<BusinessData> newData = new ArrayList<>();

        for(String name : businessName){            //遍历姓名List数据，根据业务员查找对应项目数据
            List<BusinessData> newData2 = new ArrayList<>();
            float totalMoney = 0f;       //总项目款项
            float totalAccount = 0f;     //总实收款项
            float noReceipts = 0f;      //总项目未收款项
            Map<String,Object> params = new HashMap<>();
            if(!TextUtils.isEmpty(startDate.trim()) && !TextUtils.isEmpty(endDate.trim())) {    //判断是否有日期查询数据
                if (startDate.trim().equals(endDate.trim())) {
                    params.put("oneDate", startDate);
                } else {
                    params.put("startDate", startDate);
                    params.put("endDate", endDate);
                }
            }
            params.put("projectCharge",name);
            List<BusinessData> businessData = projectDao.getProjectInfo(params);        //查询项目
            BusinessData data = new BusinessData();
            data.setProjectNum(businessData.size());                    //设置项目总数
            data.setProjectBusiness(name);                              //设置业务员姓名
            System.out.println(name);
            if(businessData.size() >0) {
                for (BusinessData business : businessData) {
                    BusinessData b = new BusinessData();
                    float Money = 0f;       //项目款项
                    float Account = 0f;     //实收款项
                    float noReceipt = 0f;      //项目未收款项
                    Money = business.getProjectMoney();
                    totalMoney += business.getProjectMoney();               //计算总款项
                    if (projectDao.getProjectReceipts(business.getProjectNo()) != null) {
                        totalAccount += projectDao.getProjectReceipts(business.getProjectNo());     //计算已收总款项
                        Account = projectDao.getProjectReceipts(business.getProjectNo());
                    }
                    noReceipt = Money - Account;
                    b.setProjectName(business.getProjectName());
                    b.setProjectMoney(Money);
                    b.setProjectGetMoney(Account);
                    b.setProjectNotReceipts(noReceipt);
                    newData2.add(b);
                }
            }
            data.setList(newData2);
            noReceipts = totalMoney - totalAccount;
            data.setProjectMoney(totalMoney);           //设置项目总款项
            data.setProjectGetMoney(totalAccount);      //设置已收总款项
            data.setProjectNotReceipts(noReceipts);     //设置未收总款项
            newData.add(data);
        }
        returnDTO.setProjectCart(newData);
        return returnDTO;
    }

    @Override
    public ProjectDTO updateProjectStage(ProjectWork projectWork) {
        ProjectDTO returnDTO = new ProjectDTO();
        if(projectDao.getProjectByNo(projectWork.getProjectNo()) == null){
            returnDTO.setError("项目编号为: " + projectWork.getProjectNo() + " 不存在，请重新检查");
            return returnDTO;
        }
        if(projectWork.getProjectStage() == 2){
            if(TextUtils.isEmpty(projectDao.getProjectPlan(projectWork.getProjectNo()))) {
                projectDao.addProjectPlan(projectWork.getProjectNo());
            }
        }else if(projectWork.getProjectStage() == 3){
//            for(long group : projectWork.getGroupsId()) {
                if (TextUtils.isEmpty(projectDao.getProjectWorkByNo(projectWork.getProjectNo()))) {
                    projectDao.addProjectWork(projectWork.getProjectNo());
                }
                Map<String,Object> params = new HashMap<>();
                params.put("projectNo",projectWork.getProjectNo());
                params.put("planWorkDate",Timestamp.valueOf(sdf.format(new Date())));
                projectDao.updatePlanWorkDate(params);
//            }
        }else if(projectWork.getProjectStage() == 4){
            String backDataId = projectDao.getWorkBackId(projectWork.getProjectNo());                //获取返修记录id
            if(!TextUtils.isEmpty(backDataId)){                                     //判断项目作业内是否有该条记录，如若没有则不修改提交时间
                Map<String,Object> params = new HashMap<>();
                params.put("id",backDataId);
                params.put("projectNo",projectWork.getProjectNo());
                if(projectDao.getBackDataById(params) != null){                         //判断返修记录是否存在
                    BackWork backWork = projectDao.getBackDataById(params);             //获取记录数据
                    backWork.setSubmitCreatedTime(Timestamp.valueOf(sdf.format(new Date())));       //添加提交时间
                    backWork.setBackDateNum(NumUtil.DateNum(backWork.getBackCreatedTime(),backWork.getSubmitCreatedTime()));    //添加返修天数
                    projectDao.updateBackData(backWork);
                }
            }
            if (TextUtils.isEmpty(projectDao.getProjectQuality(projectWork.getProjectNo()))) {
                    projectDao.addProjectQuality(projectWork.getProjectNo());
            }
        }else if(projectWork.getProjectStage() == 9){
            projectDao.updateRateOfsc(projectWork.getProjectNo());
        }
        Map<String,Object> params = new HashMap<>();
        params.put("projectNo",projectWork.getProjectNo());
        params.put("stageId",projectWork.getProjectStage());
        projectDao.updateProjectStage(params);
        returnDTO.setSuccess("更新成功");
        return returnDTO;
    }

    @Override
    public ProjectDTO getProjectRole(String roleId) {
        ProjectDTO returnDTO = new ProjectDTO();
        returnDTO.setRoleUser(projectDao.getProjectRole(roleId));
        return returnDTO;
    }

    @Override
    public ProjectDTO PrintWorkData(String projectNo) {
        ProjectDTO returnDTO = new ProjectDTO();
        ProjectOutPutWrap output = new ProjectOutPutWrap();
        returnDTO.setProjectOutPutWrap(projectDao.getProjectOutPutData(projectNo));
        String groupId = projectDao.getProjectGroupId(projectNo);                     //获取项目负责人所在队id
        output = projectDao.getPrintWorkData(projectNo,groupId);
        returnDTO.getProjectOutPutWrap().setLastDateTime(output.getLastDateTime());   //查询负责人所在队数据
        returnDTO.getProjectOutPutWrap().setShortDateTime(output.getShortDateTime());


        return returnDTO;
    }

    @Override
    public ProjectDTO getProjectOutPutData(String projectNo) {
        ProjectDTO returnDTO = new ProjectDTO();
        String level = "";
//        String groupId = projectDao.getProjectGroupId(projectNo);                     //获取项目负责人所在队id
//        returnDTO.setProjectOutPutWrap(projectDao.getProjectOutPut(projectNo,groupId));   //查询负责人所在队数据
        returnDTO.setProjectOutPutWrap(projectDao.getProjectOutPutData(projectNo));
        List<PlanRate> rateList = projectDao.getAllGroupByProject(projectNo);               //获取参与项目的所有部门
        List<WorkGroup> groupList = new ArrayList<>();
        if(returnDTO.getProjectOutPutWrap().getQualityScore() > 0.0){
            float score = returnDTO.getProjectOutPutWrap().getQualityScore();
            if(score < 60){
                level = "不合格";
            }else if(score >= 60 && score <= 70){
                level = "合格";
            }else if(score > 70 && score < 90){
                level = "良";
            }else if(score >= 90){
                level = "优";
            }
        }
        for(PlanRate rate : rateList){
            WorkGroup group = new WorkGroup();
            List<OutPutWrap> outPutWraps = projectDao.getAllOutPutList(projectNo,rate.getGroup_id());
            List<OutPutWrap> typeList = projectDao.getAllOutPutType();
            for(OutPutWrap output : typeList){
                output.setGroupId(rate.getGroup_id());
                if(outPutWraps.size() > 0) {
                    for (OutPutWrap outPutWrap : outPutWraps) {
                        if (output.getId() == outPutWrap.getTypeId()) {
                            output.setCheck(true);
                            if (outPutWrap.getProjectRatio() != null) {
                                output.setProjectRatio(outPutWrap.getProjectRatio());
                            }
                            if (outPutWrap.getWorkLoad() != null) {
                                output.setWorkLoad(outPutWrap.getWorkLoad());
                            }
                        }
//                        output.setTypeName(outPutWrap.getTypeName());
//                        output.setTypeUnit(outPutWrap.getTypeUnit());
//                        output.setTypeOutput(outPutWrap.getTypeOutput());
                        output.setGroupId(rate.getGroup_id());
                    }
                }
            }
            if(rate.getActuallyOutput() > 0){
                group.setOutPutNum(rate.getActuallyOutput());
            }else{
                group.setOutPutNum(rate.getProject_output());
            }
            group.setgName(rate.getGroupName());
            group.setId(rate.getGroup_id());
            group.setOutPutWraps(typeList);
            groupList.add(group);
        }
        returnDTO.getProjectOutPutWrap().setGroupList(groupList);
        returnDTO.getProjectOutPutWrap().setQualityLevel(level);
//        returnDTO.getProjectOutPutWrap().setOutputList(projectDao.getOutPutList(projectNo));
        returnDTO.getProjectOutPutWrap().setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(returnDTO.getProjectOutPutWrap().getProjectStartTime())));
        returnDTO.getProjectOutPutWrap().setProjectEndTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(returnDTO.getProjectOutPutWrap().getProjectEndTime())));
        return returnDTO;
    }

    @Override
    public ProjectDTO getAllGroupOutPut(String projectNo)
    {
        ProjectDTO returnDTO = new ProjectDTO();
        List<WorkGroup> groupList = workGroupDao.queryGroup();
        for (WorkGroup group: groupList) {
            List<OutPutWrap> outPutWraps = projectDao.getAllOutPutList(projectNo,group.getId());
            List<OutPutWrap> typeList = projectDao.getAllOutPutType();
            for(OutPutWrap output : typeList) {
                if(outPutWraps.size() > 0) {
                    for (OutPutWrap outPutWrap : outPutWraps) {
                        if (output.getId() == outPutWrap.getTypeId()) {
                            output.setCheck(true);
                            if (outPutWrap.getProjectRatio() != null) {
                                output.setProjectRatio(outPutWrap.getProjectRatio());
                            }
                            if (outPutWrap.getWorkLoad() != null) {
                                output.setWorkLoad(outPutWrap.getWorkLoad());
                            }
                        }

                    }
                }
            }
            group.setOutPutWraps(typeList);
        }
        returnDTO.setGroupList(groupList);
        return returnDTO;
    }


    @Override
    public ProjectDTO getAccountInfo(String projectNo) {
        ProjectDTO returnDTO = new ProjectDTO();
        returnDTO.setProjectWrap(projectDao.getAccountInfo(projectNo));
        return returnDTO;
    }

    @Override
    public ProjectDTO addProjectAccountInfo(ProjectAccount projectAccount) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String,Object> params = new HashMap<>();
        if(projectDao.getProjectByNo(projectAccount.getProjectNo()) == null){
            returnDTO.setError("项目编号为: " + projectAccount.getProjectNo() + " 不存在，请重新检查");
            return returnDTO;
        }
        params.put("projectNo",projectAccount.getProjectNo());
        params.put("createDateTime",Timestamp.valueOf(sdf.format(new Date())));
        if(projectAccount.getAccountType() == 0){
            params.put("typeName","收款");
        }else{
            params.put("typeName","支出");
        }
        params.put("accountType",projectAccount.getAccountType());
        params.put("accountNote",projectAccount.getAccountNote());
        params.put("accountNum",projectAccount.getAccountNum());
        params.put("startDateTime",TextUtils.VueDateToString(projectAccount.getStartDateTime()));
        projectDao.addProjectAccount(params);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    @Override
    public ProjectDTO deleteAccount(Long id) {
        ProjectDTO returnDTO = new ProjectDTO();
        projectDao.deleteAccount(id);
        returnDTO.setSuccess("删除成功");
        return returnDTO;
    }

    @Override
    public ProjectDTO addSchedule(ProjectSchedule projectSchedule) {
        ProjectDTO returnDTO = new ProjectDTO();
        projectSchedule.setProjectCreateTime(Timestamp.valueOf(sdf.format(new Date())));
        projectDao.addSchedule(projectSchedule);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    @Override
    public ProjectDTO getScheduleByNo(String projectNo) {
        ProjectDTO returnDTO = new ProjectDTO();
//        returnDTO.setProjectSchedules(projectDao.getScheduleByNo(projectNo));
//        for(ProjectSchedule projectSchedule : returnDTO.getProjectSchedules()){
//            projectSchedule.setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(projectSchedule.getProjectStartTime())));
//        }
        return returnDTO;
    }

    @Override
    public ProjectDTO getWorkSchedule(String projectNo) {
        ProjectDTO returnDTO = new ProjectDTO();
        returnDTO.setProjectSchedule(projectDao.getWorkSchedule(projectNo));
        return returnDTO;
    }

    @Override
    public ProjectDTO deleteSchedule(long scheduleId) {
        ProjectDTO returnDTO = new ProjectDTO();
        if(projectDao.getScheduleById(scheduleId) == null){
            returnDTO.setError("查找不到该条数据，请重新检查");
            return returnDTO;
        }
        projectDao.deleteSchedule(scheduleId);
        returnDTO.setSuccess("删除成功");
        return returnDTO;
    }

    @Override
    public ProjectDTO addBackWork(BackWork backWork) {
        ProjectDTO returnDTO = new ProjectDTO();
        backWork.setBackCreatedTime(Timestamp.valueOf(sdf.format(new Date())));
        projectDao.addBackData(backWork);
        Map<String,Object> params = new HashMap<>();
        params.put("id",projectDao.getLastId());
        params.put("projectNo",backWork.getProjectNo());
        projectDao.updateWorkBackStage(params);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    @Override
    public ProjectDTO getBackData(String projectNo) {
        ProjectDTO returnDTO = new ProjectDTO();
        List<BackWork> backWorkList = projectDao.getBackData(projectNo);
        for(BackWork backWork : backWorkList){
            backWork.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(backWork.getBackCreatedTime()));
        }
        returnDTO.setBackList(backWorkList);
        return returnDTO;
    }

    @Override
    public ProjectDTO updateBackData(BackWork backWork) {
        ProjectDTO returnDTO = new ProjectDTO();
//        if(projectDao.getBackDataById(backWork) == null){
//            returnDTO.setError("查无此返修记录");
//            return returnDTO;
//        }
        backWork.setBackCreatedTime(Timestamp.valueOf(sdf.format(new Date())));
        projectDao.updateBackData(backWork);
        returnDTO.setSuccess("修改成功");
        return returnDTO;
    }

    @Override
    public ProjectDTO updatectuallyFinishNum(Project project) {
        ProjectDTO returnDTO = new ProjectDTO();
        projectDao.updatectuallyFinishNum(project);
        returnDTO.setSuccess("修改成功");
        return returnDTO;
    }

    @Override
    public ProjectDTO getScheduleList(int page, int per_page, String sortBy, boolean descending, String search, String startDate, String endDate,String userAccount) throws Exception {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        if(!TextUtils.isEmpty(userAccount.trim())){
            params.put("userAccount",userAccount);
        }
        Long total = projectDao.getScheduleDataCount(params);                            //获取所有进度项目数量
        List<ProjectSchedule> schedules = projectDao.getScheduleDatalist(params);           //获取进度数据

        for(ProjectSchedule schedule :schedules) {                                               //遍历进度数据
            if (schedule.getProjectCreateTime() != null && schedule.getProjectBegunDate() != null) {        //判断项目创建时间和项目开始时间是否为空
                schedule.setScheduleCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(schedule.getProjectCreateTime()));     //格式化项目创建时间
                schedule.setProjectStartDate(new SimpleDateFormat("yyyy-MM-dd").format(schedule.getProjectBegunDate()));        //格式化项目开始时间
            }
            if (schedule.getqFinishDateTime() != null && schedule.getwFinishDateTime() != null) {                                 //判断质量检查结束时间和作业结束时间是否为空
                schedule.setqFDateTime(new SimpleDateFormat("yyyy-MM-dd").format(schedule.getqFinishDateTime()));
                schedule.setwFDateTime(new SimpleDateFormat("yyyy-MM-dd").format(schedule.getwFinishDateTime()));
            }

            if(schedule.getcFinishDateTime() != null){
                schedule.setcDateTime(new SimpleDateFormat("yyyy-MM-dd").format(schedule.getcFinishDateTime()));           //格式化项目结算时间
            }

            if (projectDao.getBackNum(schedule.getProjectNo()) != null) {
                schedule.setBackNum(projectDao.getBackNum(schedule.getProjectNo()));                                    //添加返修次数
            }else{
                schedule.setBackNum(0);
            }
            schedule.setProjectCountDays(Float.parseFloat(schedule.getProjectWorkDate()) + Float.parseFloat(schedule.getProjectQualityDate()));         //设置项目要求总天数
            if (schedule.getProjectBegunDate() != null && schedule.getqFinishDateTime() != null) {                                 //判断质量检查结束时间和作业结束时间是否为空
                schedule.setProjectTotalDays(TextUtils.getDateNum(new SimpleDateFormat("yyyyMMdd").format(schedule.getProjectBegunDate()),
                        new SimpleDateFormat("yyyyMMdd").format(schedule.getqFinishDateTime())));               //设置项目实际总天数
            } else {
                schedule.setProjectTotalDays(0);
            }

            if (schedule.getqFinishDateTime() != null && schedule.getwFinishDateTime() != null) {
                long outTime = (long)(schedule.getProjectTotalDays() - schedule.getProjectCountDays());
                String qFinishTime = new SimpleDateFormat("yyyy-MM-dd").format(schedule.getqFinishDateTime());
                String wFinishTime = new SimpleDateFormat("yyyy-MM-dd").format(schedule.getwFinishDateTime());
                long qfinish = TextUtils.getDateNum(wFinishTime,qFinishTime);                                               //计算质量检查结束时间减去作业结束的时间差
                long qOutTime = (long)(qfinish - schedule.getBackNum() - Float.parseFloat(schedule.getProjectQualityDate())); //计算质量超时天数
                long wOutTime = outTime - schedule.getqOutTime();//计算作业超时天数
                if(qOutTime <= 0){                      //判断是否是负数或者是0
                    schedule.setqOutTime(0);
                }else{
                    schedule.setqOutTime(qOutTime);
                }
                if(wOutTime <= 0){                      //判断是否是负数或者是0
                    schedule.setwOutTime(0);
                }else{
                    schedule.setwOutTime(wOutTime);
                }
            }else{
                schedule.setwOutTime(0);
                schedule.setqOutTime(0);
            }
        }
        returnDTO.setSchedulePagination(PaginationUtil.paginate(page, per_page, total, schedules));
        return returnDTO;
    }

    @Override
    public ProjectDTO getProjectCoe() {
        ProjectDTO returnDTO = new ProjectDTO();
        List<OutPutData> outputList = projectDao.getProjectNoByThisMonth();             //取出当月按提交到作业的安排时间为准的所有项目（除去暂停项目与删除项目）
        List<OutPutData> beforeMonth = projectDao.getProjectNoBeforeMonth();            //上个月及以前未暂停作业阶段的所有项目 + 上个月及以前质量检查阶段的所有项目
        List<String> workAtThisMonth = projectDao.getProjectByThisMonth();
        List<OutPutData> workOutPutList = projectDao.getProjectAtWorkNoStop();          //取出位于作业阶段未暂停的所有项目
        List<OutPutData> projectList = projectDao.getProjectNoByWork();             //获取当前阶段位于作业阶段未暂停的项目数据
        List<OutPutData> qualityList = projectDao.getProjectOnQuality();            //获取当前阶段位于质量检查阶段的项目数据

        if(beforeMonth.size() > 0){
            outputList.addAll(beforeMonth);                                          //取得所有已安排产值需要计算的项目产值数据
        }
        List<WorkGroup> groupList = projectDao.getWorkGroup();                          //获取所有作业组数据
        //获取已安排产值
        for(OutPutData outPutData : outputList){                                        //遍历取出各条项目数据
            List<PlanRate> ratesList = projectDao.getProjectRate(outPutData.getProjectNo());               //取出这个项目下各组所占产值的比例
            for(WorkGroup workGroup : groupList){                                       //遍历所有作业组
                for(PlanRate rate : ratesList){                                         //遍历对应项目取得的比例数据
                    if(rate.getGroup_id() == workGroup.getId()){
                        float output;
                        if(rate.getActuallyOutput() > 0){
                             output = rate.getActuallyOutput() + workGroup.getGroupOutput();
                        }else{
                             output = rate.getProject_output() + workGroup.getGroupOutput();
                        }
                        if(workGroup.getPlanRateList() ==null){
                            List<PlanRate> tempList = new ArrayList<>();
                            tempList.add(rate);
                            workGroup.setPlanRateList(tempList);
                        }else {
                            workGroup.getPlanRateList().add(rate);
                            workGroup.setPlanRateList(workGroup.getPlanRateList());
                        }
                        workGroup.setGroupOutput((float)(Math.round(output*100)/100));                               //添加已安排产值信息
                    }
                }
            }
        }

        for(OutPutData rateData :projectList){                     //遍历当前阶段位于作业阶段未暂停的项目数据
            List<OutPutData> rateList = projectDao.getProjectScheduleByWork(rateData.getProjectNo());              //获取当前项目进度小于等于90的项目数
            List<PlanRate> ratesList = projectDao.getProjectRate(rateData.getProjectNo());               //取出这个项目下各组所占产值
            if(rateList.size() > 0){                                    //判断是否有项目
                for(OutPutData rate : rateList){                        //遍历比例数据
                    for(WorkGroup workGroup : groupList){               //遍历各组数据
                        if(rate.getGroupId() == workGroup.getId()){
                            for(PlanRate output : ratesList){                                         //遍历对应项目取得的产值数据
                                if(output.getGroup_id() == workGroup.getId()){
                                    float success = output.getProject_output() * ((100 - rate.getProjectRate()))/100 + workGroup.getSuccessOutPut();
                                    workGroup.setSuccessOutPut((float)(Math.round(success*100)/100));                               //当前阶段位于作业阶段未暂停的项目产值数据

                                    if(workGroup.getPlanRateList() ==null){
                                        List<PlanRate> tempList = new ArrayList<>();
                                        tempList.add(output);
                                        workGroup.setPlanRateList(tempList);
                                    }else {
                                        workGroup.getPlanRateList().add(output);
                                        workGroup.setPlanRateList(workGroup.getPlanRateList());
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
//                for(WorkGroup workGroup : groupList){               //遍历各组数据
//                    if(rate.getGroupId() == workGroup.getId()){
                        for(PlanRate output : ratesList){                                         //遍历对应项目取得的产值数据
                            for(WorkGroup workGroup : groupList){               //遍历各组数据
                                if(output.getGroup_id() == workGroup.getId()){
                                    float success = output.getProject_output()  + workGroup.getSuccessOutPut();
                                    workGroup.setSuccessOutPut((float)(Math.round(success*100)/100));                               //当前阶段位于作业阶段未暂停的项目产值数据

                                    if(workGroup.getPlanRateList() ==null){
                                        List<PlanRate> tempList = new ArrayList<>();
                                        tempList.add(output);
                                        workGroup.setPlanRateList(tempList);
                                    }else {
                                        workGroup.getPlanRateList().add(output);
                                        workGroup.setPlanRateList(workGroup.getPlanRateList());
                                    }
                                }
//                        }
//                        }
                    }
                }
            }
        }

        for(OutPutData rateData : qualityList){                     //遍历当前阶段位于质量检查的项目数据
//            List<OutPutData> rateList = projectDao.getProjectScheduleByWork(rateData.getProjectNo());              //获取当前项目进度小于等于90的项目数
            List<PlanRate> ratesList = projectDao.getProjectRate(rateData.getProjectNo());               //取出这个项目下各组所占产值
            if(ratesList.size() > 0){                                    //判断是否有项目
                for(PlanRate rate : ratesList){                        //遍历比例数据
                    for(WorkGroup workGroup : groupList){               //遍历各组数据
                        if(rate.getGroup_id() == workGroup.getId()){
//                            for(PlanRate output : ratesList){                                         //遍历对应项目取得的产值数据
//                                if(output.getGroup_id() == workGroup.getId()){
                                    float success = (float) (rate.getProject_output() * 0.1);      //在质量检查阶段的所有项目预算产值*10%
                                    workGroup.setGroupAtQuality((float)(Math.round(success*100)/100));                               //当前阶段位于质量检查的项目产值数据

                                    if(workGroup.getPlanRateList() ==null){
                                        List<PlanRate> tempList = new ArrayList<>();
                                        tempList.add(rate);
                                        workGroup.setPlanRateList(tempList);
                                    }else {
                                        workGroup.getPlanRateList().add(rate);
                                        workGroup.setPlanRateList(workGroup.getPlanRateList());
                                    }
//                                }
//                            }
                        }
                    }
                }
            }
        }

        for(WorkGroup workGroup : groupList){
            workGroup.setGroupNotSuccessOutPut(workGroup.getSuccessOutPut() + workGroup.getGroupAtQuality());           //计算未完成产值
        }

        //获取未完成项目数
        for(String projectNo : workAtThisMonth){                                    //遍历取得未完成项目编号
            List<PlanRate> ratesList = projectDao.getProjectRate(projectNo);               //取出这个项目下各组数据
            for(WorkGroup workGroup : groupList){                                       //遍历所有作业组
                for(PlanRate rate : ratesList){                                         //遍历对应项目取得分组数据
                    if(workGroup.getId() == rate.getGroup_id()){
                        workGroup.setProjectCount(workGroup.getProjectCount() + 1);         //计算未完成项目总数
                    }
                }
            }
        }
        for(WorkGroup workGroup : groupList){           //遍历作业组数据
//            float output = workGroup.getGroupOutput() - workGroup.getSuccessOutPut();               //计算未完成产值
            double rate = 0;
//            workGroup.setGroupNotSuccessOutPut(output);
//            if(output > 0) {
                if(workGroup.getGroupOutput() > 0) {
                    rate = 60000 / workGroup.getGroupOutput() * 1.2;                //60000 / 已安排产值 * 1.2
                }
                if(workGroup.getGroupNotSuccessOutPut() > 0){
                    rate += 5 / Math.floor(workGroup.getGroupNotSuccessOutPut() / 2500 + 1);            //5/INT（未完成产值 / 2500 + 1
                }
                if(workGroup.getProjectCount() > 0){
                    rate += 3 / workGroup.getProjectCount();                    //3 / 未完成项目数
                }

                workGroup.setGroupRate((double) Math.round(rate * 100) / 100);
//            }
        }
        returnDTO.setGroupList(groupList);
        return returnDTO;
    }

    @Override
    public ProjectDTO getProjectDataCoe(){
        ProjectDTO returnDTO = new ProjectDTO();
        List<ProjectCoe> projectCoeList = projectDao.getProjectDataCoe();
        List<WorkGroup> groupList = projectDao.getWorkGroup();                          //获取所有作业组数据
        for(WorkGroup group : groupList){
            List<PlanRate> tempList = new ArrayList<>();
            group.setPlanRateList(tempList);
        }

        for(ProjectCoe projectcoe : projectCoeList){
            List<PlanRate> ratesList = projectDao.getProjectRate(projectcoe.getProject_no());               //取出这个项目下各组数据
            for(PlanRate planrate : ratesList){
                for(WorkGroup group : groupList){
                    if(group.getId() == planrate.getGroup_id()){

                        //计算未完成的项目数
                        if(projectcoe.getProject_rate() < 100) group.setProjectCount(group.getProjectCount() + 1);
                        //有实际产值算实际产值，没有则算预算产值
                        if(planrate.getActuallyOutput() > 0){
                            //计算已经安排的产值
                            group.setGroupOutput(group.getGroupOutput()+ planrate.getActuallyOutput());
                            //计算未完成的产值
                            group.setGroupNotSuccessOutPut( group.getGroupNotSuccessOutPut() + (100 - projectcoe.getProject_rate()) * planrate.getActuallyOutput()/100);

                        }else{
                            group.setGroupOutput(group.getGroupOutput()+ planrate.getProject_output());
                            group.setGroupNotSuccessOutPut( group.getGroupNotSuccessOutPut() + (100 - projectcoe.getProject_rate())* planrate.getProject_output()/100 );
                        }

                        PlanRate plan = new PlanRate();
                        plan.setProject_no(projectcoe.getProject_no());
                        plan.setProjectName(projectcoe.getProject_name());
                        plan.setProject_output(planrate.getProject_output());
                        plan.setActuallyOutput(planrate.getActuallyOutput());
                        plan.setStartDateTime(projectcoe.getProject_start_date_time().replace(" 00:00:00",""));
                        plan.setProject_rate(projectcoe.getProject_rate());

                        List<PlanRate> templist = group.getPlanRateList();
                        templist.add(plan);
                        group.setPlanRateList( templist );

                    }
                }
            }
        }
        returnDTO.setGroupList(groupList);
        return returnDTO;
    }

    @Override
    public ProjectDTO getProjectWorks(int page, int per_page, String sortBy, boolean descending, String search, int p_stage, String startDate, String endDate, int stageId) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if(stageId > 0){
            params.put("stageId",stageId);
        }
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        params.put("p_stage", p_stage);                                //项目是否在回收站判断，1.正常，2.回收站
        Long total = projectDao.getProjectCount(params);
        List<String> backWork = projectDao.getWorkBackOnly(stageId);
        List<ProjectWrap> projects = new ArrayList<>();
        if(backWork.size() > 0){
            for (String bk : backWork){
                params.put("projectNo", bk);
                ProjectWrap projectWrap = projectDao.getProjectWorks(params);
                projectWrap.setProjectStartTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(projectWrap.getProjectStartTime())));
                projects.add(projectWrap);
            }
            returnDTO.setProjectPagination(PaginationUtil.paginate(page, per_page, total, projects));
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看项目返修"));
        return returnDTO;
    }

    @Override
    public OutPutDTO getProjectOutPut(int page, int per_page, String sortBy, boolean descending, String search, String startDate, String endDate, String userGroup,String userAccount) {
        OutPutDTO returnDTO = new OutPutDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        if(!TextUtils.isEmpty(userGroup.trim())){
            params.put("userGroup",userGroup);
        }
        if(!TextUtils.isEmpty(userAccount.trim())){
            params.put("userAccount",userAccount);
            Long groupId = projectDao.getGroupIdByAccount(userAccount);
            if(!TextUtils.isEmpty(userAccount.trim())){
                params.put("groupId",groupId);
            }
        }
        Long total = projectDao.getProjectOutPutCount();
        List<WorkGroup> projects = projectDao.getPOutPut(params);
        returnDTO.setProjectwoirkPagination(PaginationUtil.paginate(page, per_page, total, projects));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看项目产值"));
        return returnDTO;
    }

    @Override
    public ProjectDTO getProjectSchedule(String projectNo) {
        ProjectDTO returnDTO = new ProjectDTO();
        List<ProjectSchedule> schedules = projectDao.getScheduleNoteByNo(projectNo);
        for(ProjectSchedule p : schedules){
            p.setScheduleCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(p.getProjectCreateTime()));
        }
        returnDTO.setProjectSchedules(schedules);
        return returnDTO;
    }

    @Override
    public ProjectDTO uploadFile(String contractNo,MultipartFile file) {
        ProjectDTO returnDTO = new ProjectDTO();
        try {
            if (file.isEmpty()) {
                returnDTO.setError("文件为空");
                return returnDTO;
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();

            // 设置文件存储路径
            String filePath = upContractFolder;
            String path = filePath + fileName;

            File dest = new File(path);
            // 检测是否存在目录

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            if(projectDao.getContractByNo(contractNo) != null){
                projectDao.updateContractFile(fileName,contractNo);
            }else{
                projectDao.addContractByNo(contractNo,fileName);
            }
            returnDTO.setSuccess("上传成功");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnDTO;
    }

    @Override
    public WorkGroupDTO getProjectGroup(String projectNo) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        List<WorkGroup> workGroupList = projectDao.getProjectGroup(projectNo);
        returnDTO.setWorkGroups(workGroupList);
        return returnDTO;
    }

    /**
     * 更新项目安排产值数据
     * @param projectPlan
     * @return
     */
    @Override
    public ProjectDTO updateoutput(ProjectPlan projectPlan) {
        ProjectDTO returnDTO = new ProjectDTO();
        projectDao.updateoutput(projectPlan);
        returnDTO.setSuccess("更新成功");
        return returnDTO;
    }

    @Override
    public ProjectDTO addNote(EditeNote editeNote) {
        ProjectDTO returnDTO = new ProjectDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("back_id", editeNote.getBack_id());
        params.put("note", editeNote.getNote());
        projectDao.addNote(params);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

//    @Override
//    public ProjectDTO addSql() {
//            File excelFile = null;// Excel文件对象
//            InputStream is = null;// 输入流对象
//            String cellStr = null;// 单元格，最终按字符串处理
//            NumberFormat nf = NumberFormat.getInstance();
//            List<BusinessData> contract = new ArrayList<BusinessData>();// 返回封装数据的List
//            BusinessData planData = null;// 每一个合同信息对象
//            try {
//                excelFile = new File("E:\\yc_project_balance.xlsx");
//                is = new FileInputStream(excelFile);// 获取文件输入流
////            XSSFWorkbook workbook2007 = new XSSFWorkbook(is);// 创建Excel2007文件对象
//                org.apache.poi.ss.usermodel.Workbook workbook2007 = WorkbookFactory.create(is);
////            XSSFSheet sheet = workbook2007.getSheetAt(0);// 取出第一个工作表，索引是0
//                org.apache.poi.ss.usermodel.Sheet sheet = workbook2007.getSheetAt(0);
//                // 开始循环遍历行，表头不处理，从1开始
//                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//                    planData = new BusinessData();// 实例化Student对象
////            	HSSFRow row = sheet.getRow(i);// 获取行对象
//                    Row row = sheet.getRow(i);// 获取行对象
//                    if (row == null) {// 如果为空，不处理
//                        continue;
//                    }
//                    // 循环遍历单元格
//                    for (int j = 0; j < row.getLastCellNum(); j++) {
////                    XSSFCell cell = row.getCell(j);// 获取单元格对象
//                        Cell cell = row.getCell(j);// 获取单元格对象
//                        if (cell == null) {// 单元格为空设置cellStr为空串
//                            cellStr = "";
//                        }
//                        else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
//                            cellStr = String.valueOf(cell.getBooleanCellValue());
//                        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
//                            cellStr = nf.format(cell.getNumericCellValue());
////                        cellStr = cell.getNumericCellValue() + "";
//                        } else {// 其余按照字符串处理
//                            cellStr = cell.getStringCellValue();
//                        }
//                         //下面按照数据出现位置封装到bean中
//                        if(j == 0){
////                            planData.setpId(Integer.parseInt(cellStr));
//                            if(projectDao.getProjectNoByGroup(Integer.parseInt(cellStr)) !=null) {
//                                ProjectPlanDTO dto = projectDao.getProjectNoByGroup(Integer.parseInt(cellStr));
//                                planData.setProjectNo(dto.getpNo());
//                            }
//                        }
////                        else if(j == 1){
////
////                        }
//                        else if(j == 1){
//                            planData.setProjectMoney(Float.parseFloat(cellStr));
//
//                        }
//                        else if(j == 3){
//                            planData.setProjectNotReceipts(Float.parseFloat(cellStr));
//                        }
//                        else if(j == 4){
//                            if(Float.parseFloat(cellStr) > 0) {
//                                planData.setProjectNum(1);
//                                planData.setProjectMoney(Float.parseFloat(cellStr));
//                            }
//                        }


//                        else if (j == 6) {
//                            if (cellStr.indexOf(",") >= 0) {
//                                cellStr = cellStr.replace(",", "");
//                            }
//                            planData.setOutpu(Float.parseFloat(cellStr));
//                        }

//                        else if (j == 4) {
//                            planData.setProjectWorkRequire(cellStr);
//                        } else if(j == 5){
//                            planData.setProjectWriter(cellStr);
//                        } else if(j == 6){
//                            //这种方法对于自动加".0"的数字可直接解决
//                            //但如果是科学计数法的数字就转换成了带逗号的，例如：12345678912345的科学计数法是1.23457E+13，经过这个格式化后就变成了字符串“12,345,678,912,345”，这也并不是想要的结果，所以要将逗号去掉
////                            if (cellStr.indexOf(",") >= 0) {
////                                cellStr = cellStr.replace(",", "");
////                            }
//                            for(UserWrap user : userDao.getUser()){                                     //遍历所有用户
//                                if(user.getUserName().equals(cellStr)) {          //根据用户名获取用户账号
//                                    planData.setProjectAccount(user.getUserAccount());            //获取用户账号
//                                }
////                                }else{
////                                    planData.setProjectAccount("admin");
//                                }
//                            }
//                            planData.setProjectCharge(cellStr);
////                            contractData.setContractUserPhone(cellStr);
//                        }

//                    }
//                    contract.add(planData);// 数据装入List
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InvalidFormatException e) {
//                // TODO Auto-generated catch block
//            }finally {// 关闭文件流
//                if (is != null) {
//                    try {
//                        is.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        for (BusinessData planRate : contract) {                //获取比例数组
//            Map<String,Object> params = new HashMap<>();
//            params.put("projectNo",planRate.getProjectNo());
//            params.put("money",planRate.getProjectMoney());
//            projectDao.addProjectMoney(params);
//
//        }
//        return null;
//    }


}
