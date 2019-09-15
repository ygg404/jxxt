package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.*;
import cn.ux.jxxt.domain.custom.AccountData;
import cn.ux.jxxt.domain.custom.AccountWrap;
import cn.ux.jxxt.dto.OutPutDTO;
import cn.ux.jxxt.dto.ProjectDTO;
import cn.ux.jxxt.dto.WorkGroupDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

public interface ProjectService {

    ProjectDTO addProject(Long userId, ProjectDTO project);

    ProjectDTO getProjectBySetUp(String project_no);

    ProjectDTO getProjects(int page, int per_page, String sortBy,
                           boolean descending, String search,
                           int p_stage, String startDate,String endDate,
                           String stageId,String account);

    ProjectDTO getProjectsByWork(int page, int per_page, String sortBy,
                           boolean descending, String search,
                           int p_stage, String startDate,String endDate,
                           String stageId,String account);

    ProjectDTO getRecycleProjects(int page, int per_page, String sortBy,
                           boolean descending, String search,
                           int p_stage, String startDate,String endDate,
                           String stageId,String account);

    ProjectDTO getProjectsByAccounts(int page, int per_page, String sortBy, boolean descending, String search, int p_stage, String startDate,String endDate,String stageId,String userAccount);

    ProjectDTO getProjectAccounts(int page, int per_page, String sortBy, boolean descending, String search, int p_stage, String startDate,String endDate,String stageId);

    ProjectDTO addOutPut(ProjectDTO projectDTO);

    ProjectDTO updateOutPut(ProjectDTO projectDTO);

    ProjectDTO addExamineNote(ProjectDTO projectDTO);

    ProjectDTO updateProject(ProjectDTO projectDTO);

    ProjectDTO deleteProject(String projectNo);

    ProjectDTO putProjectToRecycle(String projectNo, int stageId);

    ProjectDTO addProjectAccount(ProjectDTO projectDTO);

    ProjectDTO getProjectAccount(String project_no);

    ProjectDTO getProjectBusiness(String startDate,String endDate);

    ProjectDTO updateProjectStage(ProjectWork projectWork);

    ProjectDTO getProjectRole(String roleId);

    ProjectDTO getProjectOutPutData(String projectNo);

    //通过项目号 获取所有组的预算产值
    ProjectDTO getAllGroupOutPut(String projectNo);

    ProjectDTO PrintWorkData(String projectNo);

    ProjectDTO getAccountInfo(String projectNo);

    ProjectDTO addProjectAccountInfo(ProjectAccount projectAccount);

    ProjectDTO deleteAccount(Long id);

    ProjectDTO addSchedule(ProjectSchedule projectSchedule);

    ProjectDTO getScheduleByNo(String projectNo);

    ProjectDTO getWorkSchedule(String projectNo);

    ProjectDTO deleteSchedule(long scheduleId);

    ProjectDTO addBackWork(BackWork backWork);

    ProjectDTO getBackData(String projectNo);

    ProjectDTO updateBackData(BackWork backWork);

    ProjectDTO updatectuallyFinishNum(Project project);

    ProjectDTO getScheduleList(int page, int per_page, String sortBy, boolean descending, String search, String startDate,String endDate,String userAccount) throws Exception;

    ProjectDTO getProjectCoe();

    ProjectDTO getProjectDataCoe();

    ProjectDTO getProjectWorks(int page, int per_page, String sortBy, boolean descending, String search, int p_stage, String startDate,String endDate,int stageId);

    OutPutDTO getProjectOutPut(int page, int per_page, String sortBy, boolean descending, String search, String startDate, String endDate, String userGroup,String userAccount);

    ProjectDTO getProjectSchedule(String projectNo);

    ProjectDTO uploadFile(String contractNo,MultipartFile file);

    WorkGroupDTO getProjectGroup(String projectNo);

    ProjectDTO updateoutput(ProjectPlan projectPlan);

    ProjectDTO addNote(EditeNote editeNote);

//    ProjectDTO addSql();
}
