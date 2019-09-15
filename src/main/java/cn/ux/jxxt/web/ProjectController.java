package cn.ux.jxxt.web;

import cn.ux.jxxt.dao.ProjectDao;
import cn.ux.jxxt.domain.*;
import cn.ux.jxxt.dto.OutPutDTO;
import cn.ux.jxxt.dto.ProjectDTO;
import cn.ux.jxxt.dto.WorkGroupDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.ProjectService;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.vo.GeneralMessage;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private GeneralMessage message;

    @Autowired
    private ProjectDao projectDao;

    @Value("${file.upContractFolder}")
    private String upContractFolder;
    /**
     * 添加项目
     *
     * @param userId
     * @param projectDTO
     * @param bindingResult
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/project/{userId}/")
    public ResponseEntity<?> addProject(@PathVariable("userId") Long userId, @Valid @RequestBody ProjectDTO projectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("添加项目失败，请重新添加", bindingResult);
        }
        ProjectDTO returnDTO = projectService.addProject(userId, projectDTO);
        if (returnDTO.getError() != null) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(returnDTO.getProjectNo());
        }
    }

    /**
     * 获取项目数据(分页)
     *
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @return
     */
    @GetMapping(value = "/projectSetUp/", params = {"page", "rowsPerPage", "sortBy", "descending","p_stage","search","startDate","endDate","stageId","account"})
    public ResponseEntity<?> getProjects(@RequestParam("page") int page,
                                         @RequestParam("rowsPerPage") int per_page,
                                         @RequestParam("sortBy") String sortBy,
                                         @RequestParam("descending") boolean descending,
                                         @RequestParam("p_stage") int p_stage,
                                         @RequestParam("search") String search,
                                         @RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate,
                                         @RequestParam("stageId") String stageId,
                                         @RequestParam("account") String account) {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        ProjectDTO returnDTO = projectService.getProjects(page, per_page, sortBy, descending, search,p_stage,start,end,stageId,account);
        return ResponseEntity.ok(returnDTO.getProjectPagination());
    }

    /**
     * 获取项目作业数据
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param p_stage
     * @param search
     * @param startDate
     * @param endDate
     * @param stageId
     * @param account
     * @return
     */
    @GetMapping(value = "/projectByWork/", params = {"page", "rowsPerPage", "sortBy", "descending","p_stage","search","startDate","endDate","stageId","account"})
    public ResponseEntity<?> getProjectsByWork(@RequestParam("page") int page,
                                         @RequestParam("rowsPerPage") int per_page,
                                         @RequestParam("sortBy") String sortBy,
                                         @RequestParam("descending") boolean descending,
                                         @RequestParam("p_stage") int p_stage,
                                         @RequestParam("search") String search,
                                         @RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate,
                                         @RequestParam("stageId") String stageId,
                                         @RequestParam("account") String account) {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        ProjectDTO returnDTO = projectService.getProjectsByWork(page, per_page, sortBy, descending, search,p_stage,start,end,stageId,account);
        return ResponseEntity.ok(returnDTO.getProjectPagination());
    }

    /**
     * 获取回收站项目数据(分页)
     *
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @return
     */
    @GetMapping(value = "/projectRecycle/", params = {"page", "rowsPerPage", "sortBy", "descending","p_stage","search","startDate","endDate","stageId","account"})
    public ResponseEntity<?> getRecycleProject(@RequestParam("page") int page,
                                         @RequestParam("rowsPerPage") int per_page,
                                         @RequestParam("sortBy") String sortBy,
                                         @RequestParam("descending") boolean descending,
                                         @RequestParam("p_stage") int p_stage,
                                         @RequestParam("search") String search,
                                         @RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate,
                                         @RequestParam("stageId") String stageId,
                                         @RequestParam("account") String account) {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        ProjectDTO returnDTO = projectService.getRecycleProjects(page, per_page, sortBy, descending, search,p_stage,start,end,stageId,account);
        return ResponseEntity.ok(returnDTO.getProjectPagination());
    }


    /*@GetMapping(value = "/projectBackList/", params = {"projectNo"})
    public ResponseEntity<?> getProjects(@RequestParam("projectNo") String projectNo) {
        ProjectDTO returnDTO = projectService.getBackData(projectNo);
        return ResponseEntity.ok(returnDTO.getBackList());
    }*/

    /**
     * 获取项目数据(分页)
     *
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @return
     */
    @GetMapping(value = "/projects/user/", params = {"page", "rowsPerPage", "sortBy", "descending","p_stage","search","startDate","endDate","stageId","userAccount"})
    public ResponseEntity<?> getProjectByAccount(@RequestParam("page") int page,
                                         @RequestParam("rowsPerPage") int per_page,
                                         @RequestParam("sortBy") String sortBy,
                                         @RequestParam("descending") boolean descending,
                                         @RequestParam("p_stage") int p_stage,
                                         @RequestParam("search") String search,
                                         @RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate,
                                         @RequestParam("stageId") String stageId,
                                         @RequestParam("userAccount") String userAccount) {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        ProjectDTO returnDTO = projectService.getProjectsByAccounts(page, per_page, sortBy, descending, search,p_stage,start,end,stageId,userAccount);
        return ResponseEntity.ok(returnDTO.getProjectPagination());
    }

    /**
     * 获取财务操作数据
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param p_stage
     * @param search
     * @return
     */
    @GetMapping(value = "/project/account/",params = {"page","rowsPerPage","sortBy","descending","p_stage","search","startDate","endDate","stageId"})
    public ResponseEntity<?> getAccountData(@RequestParam("page") int page,
                                            @RequestParam("rowsPerPage") int per_page,
                                            @RequestParam("sortBy") String sortBy,
                                            @RequestParam("descending") boolean descending,
                                            @RequestParam("p_stage") int p_stage,
                                            @RequestParam("search") String search,
                                            @RequestParam("startDate") String startDate,
                                            @RequestParam("endDate") String endDate,
                                            @RequestParam("stageId") String stageId){
        ProjectDTO returnDTO = projectService.getProjectAccounts(page, per_page, sortBy, descending, search, p_stage, startDate, endDate, stageId);
        return ResponseEntity.ok(returnDTO.getDataPagination());

    }

    /**
     * 添加项目产值
     *
     * @param projectDTO
     * @param bindingResult
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/project/output/")
    public ResponseEntity<?> addOutPuts(@Valid @RequestBody ProjectDTO projectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("添加产值核算错误", bindingResult);
        }
        ProjectDTO returnDTO = projectService.addOutPut(projectDTO);
        if (!TextUtils.isEmpty(returnDTO.getError())) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 更新产值数据
     * @param projectDTO
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/project/output/update/")
    public ResponseEntity<?> updateOutPuts(@Valid @RequestBody ProjectDTO projectDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新产值核算出错",bindingResult);
        }
        ProjectDTO returnDTO = projectService.updateOutPut(projectDTO);
        if(returnDTO.getError() != null){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 添加项目审定
     *
     * @param projectDTO
     * @param bindingResult
     * @return
     */
    @PutMapping(value = "/projectSetUp/")
    public ResponseEntity<?> addExamineNote(@Valid @RequestBody ProjectDTO projectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("添加审定内容失败", bindingResult);
        }
        ProjectDTO returnDTO = projectService.addExamineNote(projectDTO);
        if (!TextUtils.isEmpty(returnDTO.getError())) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 更新项目数据
     *
     * @param projectDTO
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/updateProject/")
    public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectDTO projectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("更新数据出错", bindingResult);
        }
        ProjectDTO returnDTO = projectService.updateProject(projectDTO);
        if (!TextUtils.isEmpty(returnDTO.getError())) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 删除项目
     *
     * @param projectNO
     * @return
     */
    @DeleteMapping(value = "/project/", params = {"projectNo"})
    public ResponseEntity<?> deleteProject(@RequestParam("projectNo") String projectNO) {
        ProjectDTO returnDTO = projectService.deleteProject(projectNO);
        if (!TextUtils.isEmpty(returnDTO.getError())) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 回收站操作(stageId == 1为正常状态，2放入回收站)
     * @param projectNo
     * @return
     */
    @PostMapping(value = "/project/recycle/", params = {"projectNo","stageId"})
    public ResponseEntity<?> putProjectToRecycle(@RequestParam("projectNo") String projectNo,
                                                 @RequestParam("stageId") int stageId) {
        ProjectDTO returnDTO = projectService.putProjectToRecycle(projectNo,stageId);
        if (!TextUtils.isEmpty(returnDTO.getError())) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 添加财务数据
     * @param projectDTO
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/project/account/")
    public ResponseEntity<?> addProjectAccount(@Valid @RequestBody ProjectDTO projectDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加财务数据失败",bindingResult);
        }
        ProjectDTO returnDTO = projectService.addProjectAccount(projectDTO);
        if(returnDTO.getError() !=null){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     *  添加财务收支明细
     * @param projectAccount
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/project/addAccount/")
    public ResponseEntity<?> addProjectAccountInfo(@Valid @RequestBody ProjectAccount projectAccount,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加财务数据失败",bindingResult);
        }
        ProjectDTO returnDTO = projectService.addProjectAccountInfo(projectAccount);
        if(returnDTO.getError() !=null){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 获取项目立项数据
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/project/setup/",params = "projectNo")
    public ResponseEntity<?> getProjectBySetUp(@RequestParam("projectNo") String projectNo){
        ProjectDTO returnDTO = projectService.getProjectBySetUp(projectNo);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.ok(returnDTO);
        }
    }

    /**
     * 获取财务操作数据
     * @param project_no
     * @return
     */
    @GetMapping(value = "/project/account/",params = "projectNo")
    public ResponseEntity<?> getProjectAccount(@RequestParam("projectNo") String project_no){
        ProjectDTO returnDTO = projectService.getProjectAccount(project_no);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getAccountWrap());
        }
    }

    /**
     * 更新项目所属阶段
     * @return
     */
    @PostMapping(value = "/project/stage/")
    public ResponseEntity<?> updateProjectStage(@Valid @RequestBody ProjectWork projectWork, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新项目阶段失败",bindingResult);
        }
        ProjectDTO returnDTO = projectService.updateProjectStage(projectWork);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    @GetMapping(value = "/project/role/",params = {"roleId"})
    public ResponseEntity<?> getRoleUser(@RequestParam("roleId") String roleId){
        ProjectDTO returnDTO = projectService.getProjectRole(roleId);
        return ResponseEntity.ok(returnDTO.getRoleUser());
    }

    /**
     * 获取项目信息
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/project/output/",params = {"projectNo"})
    public ResponseEntity<?> getOutPutData(@RequestParam("projectNo") String projectNo){
        ProjectDTO returnDTO = projectService.getProjectOutPutData(projectNo);
        return ResponseEntity.ok(returnDTO.getProjectOutPutWrap());
    }

    /**
     * 获取项目信息作业打印
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/PrintWorkData/",params = {"projectNo"})
    public ResponseEntity<?> PrintWorkData(@RequestParam("projectNo") String projectNo){
        ProjectDTO returnDTO = projectService.PrintWorkData(projectNo);
        return ResponseEntity.ok(returnDTO.getProjectOutPutWrap());
    }

    @GetMapping(value = "/project/accounts/",params = {"projectNo"})
    public ResponseEntity<?> getAccountInfo(@RequestParam("projectNo") String projectNo){
        ProjectDTO returnDTO = projectService.getAccountInfo(projectNo);
        return ResponseEntity.ok(returnDTO.getProjectWrap());
    }

    @DeleteMapping(value = "/account/{accountId}/")
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") Long accountId){
        ProjectDTO returnDTO = projectService.deleteAccount(accountId);
        if(returnDTO.getError() == null){
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 获取项目进度数据
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/schedule/",params = {"projectNo"})
    public ResponseEntity<?> getScheduleList(@RequestParam("projectNo") String projectNo){
        ProjectDTO returnDTO = projectService.getScheduleByNo(projectNo);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectSchedule());
    }

    /**
     * 作业阶段获取项目进度数据
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/getSchedule/",params = {"projectNo"})
    public ResponseEntity<?> getSchedule(@RequestParam("projectNo") String projectNo){
        ProjectDTO returnDTO = projectService.getWorkSchedule(projectNo);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectSchedule());
    }

    /**
     * 添加项目进度
     * @param projectSchedule
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/schedule/")
    public ResponseEntity<?> addSchedule(@Valid @RequestBody ProjectSchedule projectSchedule,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("新增进度失败",bindingResult);
        }
        ProjectDTO projectDTO = projectService.addSchedule(projectSchedule);
        message.setMessage(projectDTO.getSuccess());
        return ResponseEntity.ok(message);
    }

    /**
     * 删除项目进度数据
     * @param scheduleId
     * @return
     */
    @DeleteMapping(value = "/schedule/{scheduleId}/")
    public ResponseEntity<?> deleteScheduleById(@PathVariable("scheduleId") long scheduleId){
        ProjectDTO returnDTO = projectService.deleteSchedule(scheduleId);
        message.setMessage(returnDTO.getSuccess());
        return ResponseEntity.ok(message);
    }

    /**
     * 添加返修数据
     * @param backWork
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/project/back/")
    public ResponseEntity<?> addBackWork(@Valid @RequestBody BackWork backWork,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("新增出错",bindingResult);
        }
        ProjectDTO projectDTO = projectService.addBackWork(backWork);
        if(projectDTO.getError() != null){
            message.setMessage(projectDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(projectDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 修改返修数据
     * @param backWork
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/project/back/update/")
    public ResponseEntity<?> updateBackWork(@Valid @RequestBody BackWork backWork, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新出错",bindingResult);
        }
        ProjectDTO projectDTO = projectService.updateBackData(backWork);
        if(projectDTO.getError() != null){
            message.setMessage(projectDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(projectDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 添加项目实际完成天数
     * @param project
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/actuallyFinishNum/")
    public ResponseEntity<?> actuallyFinishNum(@Valid @RequestBody Project project, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新出错",bindingResult);
        }
        ProjectDTO projectDTO = projectService.updatectuallyFinishNum(project);
        if(projectDTO.getError() != null){
            message.setMessage(projectDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(projectDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 获取返修结果
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/project/back/" ,params = {"projectNo"})
    public ResponseEntity<?> getBackWork(@RequestParam("projectNo") String projectNo){
        ProjectDTO projectDTO = projectService.getBackData(projectNo);
        if(projectDTO.getError() != null){
            message.setMessage(projectDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(projectDTO.getSuccess());
            return ResponseEntity.status(HttpStatus.CREATED).body(projectDTO.getBackList());
        }
    }

    /**
     * 获取返修项目作业数据(分页)
     *
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @return
     */
    @GetMapping(value = "/projectWorkBack/", params = {"page", "rowsPerPage", "sortBy", "descending","p_stage","search","startDate","endDate","stageId"})
    public ResponseEntity<?> getProjectWorks(@RequestParam("page") int page,
                                             @RequestParam("rowsPerPage") int per_page,
                                             @RequestParam("sortBy") String sortBy,
                                             @RequestParam("descending") boolean descending,
                                             @RequestParam("p_stage") int p_stage,
                                             @RequestParam("search") String search,
                                             @RequestParam("startDate") String startDate,
                                             @RequestParam("endDate") String endDate,
                                             @RequestParam("stageId") int stageId) {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        ProjectDTO returnDTO = projectService.getProjectWorks(page, per_page, sortBy, descending, search,p_stage,start,end,stageId);
        return ResponseEntity.ok(returnDTO.getProjectPagination());
    }

    /**
     * 获取进度数据表
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/project/schedule/",params = {"page", "rowsPerPage", "sortBy", "descending","search","startDate","endDate","userAccount"})
    public ResponseEntity<?> getProjectSchedule(@RequestParam("page") int page,
                                                @RequestParam("rowsPerPage") int per_page,
                                                @RequestParam("sortBy") String sortBy,
                                                @RequestParam("descending") boolean descending,
                                                @RequestParam("search") String search,
                                                @RequestParam("startDate") String startDate,
                                                @RequestParam("endDate") String endDate,
                                                @RequestParam("userAccount") String userAccount) throws Exception {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        ProjectDTO returnDTO = projectService.getScheduleList(page, per_page, sortBy, descending, search,start,end,userAccount);
        return ResponseEntity.ok(returnDTO.getSchedulePagination());
    }

    /**
     * 获取计算项目系数数据
     * @return
     */
    @GetMapping(value = "/project/coe/")
    public ResponseEntity<?> getProjectCoe(){
        ProjectDTO returnDTO = projectService.getProjectCoe();
        return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getGroupList());
    }

    /**
     * 获取计算项目系数数据(二版)
     * @return
     */
    @GetMapping(value = "/project/datacoe/")
    public ResponseEntity<?> getProjectDatacoe(){
        ProjectDTO returnDTO = projectService.getProjectDataCoe();
        return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getGroupList());
    }

    /**
     * 获取项目产值数据
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/projectOutPut/",params = {"page", "rowsPerPage", "sortBy", "descending","search","startDate","endDate","userGroup","userAccount"})
    public ResponseEntity<?> getProjectOutPut(@RequestParam("page") int page,
                                              @RequestParam("rowsPerPage") int per_page,
                                              @RequestParam("sortBy") String sortBy,
                                              @RequestParam("descending") boolean descending,
                                              @RequestParam("search") String search,
                                              @RequestParam("startDate") String startDate,
                                              @RequestParam("endDate") String endDate,
                                              @RequestParam("userGroup") String userGroup,
                                              @RequestParam("userAccount") String userAccount){
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        OutPutDTO returnDTO = projectService.getProjectOutPut(page, per_page, sortBy, descending, search,start,end,userGroup,userAccount);
        return ResponseEntity.ok(returnDTO.getProjectwoirkPagination());
    }

    @GetMapping(value = "/project/schedule/{projectNo}/")
    public ResponseEntity<?> getScheduleByNo(@PathVariable("projectNo") String projectNo){
        ProjectDTO returnDTO = projectService.getProjectSchedule(projectNo);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectSchedules());
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping(value = "/file/",params = "contractNo")
    public ResponseEntity<?> uploadFile(@PathVariable MultipartFile file,@RequestParam("contractNo") String contractNo){
//        String contractNo = "ss";
        ProjectDTO returnDTO = projectService.uploadFile(contractNo,file);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 下载文件
     * @param contractNo
     * @param response
     * @return
     */
    @GetMapping(value = "/download/",params = "contractNo")
    public String downloadFile(@RequestParam("contractNo") String contractNo,HttpServletResponse response) {
        String fileName = projectDao.getContractFileName(contractNo);// 设置文件名，根据业务需要替换成要下载的文件名
        if(TextUtils.isEmpty(fileName)){
            return "该文件不存在";
        } else {
            //设置文件路径
            String realPath = upContractFolder;
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取项目所负责的小组信息
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/project/group/",params = {"projectNo"})
    public ResponseEntity<?> getProjectGroup(@RequestParam("projectNo") String projectNo){
        WorkGroupDTO returnDTO = projectService.getProjectGroup(projectNo);
        return ResponseEntity.ok(returnDTO.getWorkGroups());
    }

    @GetMapping(value = "/project/allGroupOutput/", params = {"projectNo"})
    public ResponseEntity<?> getAllGroupOutPut(@RequestParam("projectNo") String projectNo) {
        ProjectDTO returnDTO = projectService.getAllGroupOutPut(projectNo);
        return ResponseEntity.ok(returnDTO.getGroupList());
    }

    /**
     * 修改项目安排产值
     * @param projectPlan
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/project/updateoutput/")
        public ResponseEntity<?> updateOutPut(@Valid @RequestBody ProjectPlan projectPlan, BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                throw new InvalidRequestException("更新产值核算出错",bindingResult);
            }
            ProjectDTO returnDTO = projectService.updateoutput(projectPlan);
            if(returnDTO.getError() != null){
                message.setMessage(returnDTO.getError());
                return ResponseEntity.badRequest().body(message);
            }else{
                message.setMessage(returnDTO.getSuccess());
                return ResponseEntity.ok(message);
            }
        }
    @PostMapping(value = "/addNote/")
    public ResponseEntity<?> addNote(@Valid @RequestBody EditeNote editeNote, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加出错",bindingResult);
        }
        ProjectDTO returnDTO = projectService.addNote(editeNote);
        if (!TextUtils.isEmpty(returnDTO.getError())) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

//    @GetMapping(value = "/addsql/")
//    public ResponseEntity<?> addSql(){
//        ProjectDTO returnDTO = projectService.addSql();
//        return ResponseEntity.ok("添加成功");
//    }
}
