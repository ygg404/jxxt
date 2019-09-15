package cn.ux.jxxt.web;

import cn.ux.jxxt.dto.ProjectPlanDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.ProjectPlanService;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ProjectPlanController {

    @Autowired
    private ProjectPlanService projectPlanService;

    @Autowired
    private GeneralMessage message;

    /**
     * 添加项目安排数据
     * @param projectPlanDTO
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/projectPlan/")
    public ResponseEntity<?> addProjectPlan(@Valid @RequestBody ProjectPlanDTO projectPlanDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加项目安排失败",bindingResult);
        }
        ProjectPlanDTO returnDTO = projectPlanService.addProjectPlan(projectPlanDTO);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 更新项目安排数据
     * @param projectPlanDTO
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/projectPlan/update/")
    public ResponseEntity<?> updateProjectPlan(@Valid @RequestBody ProjectPlanDTO projectPlanDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新失败",bindingResult);
        }
        ProjectPlanDTO returnDTO = projectPlanService.updateProjectPlan(projectPlanDTO);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 获取项目安排数据
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/projectPlan/",params = "projectNo")
    public ResponseEntity<?> getPlanData(@RequestParam("projectNo") String projectNo){
        ProjectPlanDTO returnDTO = projectPlanService.getPlanData(projectNo);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectPlan());
        }
    }
}
