package cn.ux.jxxt.web;

import cn.ux.jxxt.domain.ProjectStage;
import cn.ux.jxxt.dto.StageDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.ProjectStageService;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProjectStageController {

    @Autowired
    private ProjectStageService service;

    @Autowired
    private GeneralMessage message;

    /**
     * 获取所有项目阶段
     * @return
     */
    @GetMapping(value = "/stage/")
    public ResponseEntity<?> getStage(){
        StageDTO returnDTO = service.getStage();
        if(returnDTO.getList().size() == 0){
            message.setMessage("未录入数据");
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.ok(returnDTO.getList());
        }
    }

    /**
     * 新增项目阶段
     * @param projectStage
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/stage/")
    public ResponseEntity<?> insertStage(@Valid @RequestBody ProjectStage projectStage, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("新增项目阶段失败",bindingResult);
        }
        StageDTO returnDTO = service.insertStage(projectStage);
        if(returnDTO.getError() == null){
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 更新指定id的项目阶段
     * @param stageId
     * @param projectStage
     * @param bindingResult
     * @return
     */
    @PutMapping(value = "/stage/{stageId}/")
    public ResponseEntity<?> updateStage(@PathVariable("stageId") Long stageId, @RequestBody ProjectStage projectStage, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新项目失败",bindingResult);
        }

        StageDTO returnDTO = service.updateStage(stageId,projectStage);
        if(returnDTO.getError() != null){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectStage());
        }
    }

    /**
     * 删除指定的项目阶段
     * @param stageId
     * @return
     */
    @DeleteMapping(value = "/stage/{stageId}/")
    public ResponseEntity<?> deleteStage(@PathVariable("stageId") Long stageId){
        StageDTO returnDTO = service.deleteStage(stageId);
        if(returnDTO.getError() == null){
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }
}
