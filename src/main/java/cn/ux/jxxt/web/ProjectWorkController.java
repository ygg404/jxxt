package cn.ux.jxxt.web;

import cn.ux.jxxt.domain.ProjectWork;
import cn.ux.jxxt.dto.ProjectWorkDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.ProjectWorkService;
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
public class ProjectWorkController {

    @Autowired
    private ProjectWorkService projectWorkService;

    @Autowired
    private GeneralMessage message;

    /**
     * 添加项目作业内容
     * @param projectWork
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/projectWork/")
    public ResponseEntity<?> addProjectWork(@Valid @RequestBody ProjectWork projectWork, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加项目作业内容失败",bindingResult);
        }
        ProjectWorkDTO returnDTO = projectWorkService.addProjectWork(projectWork);
        if(TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 更新项目作业内容
     * @param projectWork
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/projectWork/update/")
    public ResponseEntity<?> updateProjectWork(@Valid @RequestBody ProjectWork projectWork, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新项目作业内容失败",bindingResult);
        }
        ProjectWorkDTO returnDTO = projectWorkService.updateProjectWork(projectWork);
        if(TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 获取项目作业数据
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/projectWork/",params = {"projectNo"})
    public ResponseEntity<?> getWorkData(@RequestParam("projectNo") String projectNo){
        ProjectWorkDTO returnDTO = projectWorkService.getWorkData(projectNo);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectWork());
        }
    }

    /**
     * 添加完成时间
     * @param projectWork
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/projectWork/time/")
    public ResponseEntity<?> addFinishDateTime(@Valid @RequestBody ProjectWork projectWork,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加出错");
        }
        ProjectWorkDTO returnDTO = projectWorkService.addFinishDateTime(projectWork);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }
}
