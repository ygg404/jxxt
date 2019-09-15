package cn.ux.jxxt.web;

import cn.ux.jxxt.domain.CheckQuality;
import cn.ux.jxxt.domain.ProjectWork;
import cn.ux.jxxt.domain.QualityScore;
import cn.ux.jxxt.dto.CheckQualityDTO;
import cn.ux.jxxt.dto.ProjectWorkDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.ProjectQualityService;
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
public class ProjectQualityController {

    @Autowired
    private ProjectQualityService projectQualityService;

    @Autowired
    private GeneralMessage message;

    /**
     * 添加质量检查数据
     * @param checkQuality
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/projectQuality/")
    public ResponseEntity<?> addProjectQuality(@Valid @RequestBody CheckQuality checkQuality, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加质量检查数据失败",bindingResult);
        }
        CheckQualityDTO returnDTO = projectQualityService.addQuality(checkQuality);
        if(returnDTO.getError() == null){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 更新质量检查数据
     * @param checkQuality
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/projectQuality/update/")
    public ResponseEntity<?> updateQuality(@Valid @RequestBody CheckQuality checkQuality, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新质量检查数据失败",bindingResult);
        }
        CheckQualityDTO returnDTO = projectQualityService.updateQuality(checkQuality);
        if(returnDTO.getError() != null){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 获取质量检查数据
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/projectData/",params = {"projectNo"})
    public ResponseEntity<?> getQuality(@RequestParam("projectNo") String projectNo){
        CheckQualityDTO returnDTO = projectQualityService.getQualityData(projectNo);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getCheckQuality());
        }
    }

    /**
     * 新增/修改质量评分数据
     * @param qualityScore
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/project/quality/")
    public ResponseEntity<?> setQualityScore(@Valid @RequestBody CheckQualityDTO qualityScore,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("修改失败",bindingResult);
        }
        CheckQualityDTO returnDTO = projectQualityService.updateQualityScore(qualityScore);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 添加质量审核人
     * @param qualityDTO
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/quality/user/")
    public ResponseEntity<?> setQualityUser(@Valid @RequestBody CheckQuality qualityDTO,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("修改失败",bindingResult);
        }
        CheckQualityDTO returnDTO = projectQualityService.putToOutPut(qualityDTO);
        return ResponseEntity.ok("添加成功");
    }

    /**
     * 获取质量评分数据
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/project/quality/",params = "projectNo")
    public ResponseEntity<?> getQualityScore(@RequestParam("projectNo") String projectNo){
        CheckQualityDTO returnDTO = projectQualityService.getQualityScore(projectNo);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getScoreList()  );
        }
    }

    /**
     * 添加完成时间
     * @param checkQuality
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/projectQuality/time/")
    public ResponseEntity<?> addFinishDateTime(@Valid @RequestBody CheckQuality checkQuality, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加出错");
        }
        CheckQualityDTO returnDTO = projectQualityService.addFinishDateTime(checkQuality);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 修改结算时间
     * @return
     */
    @PostMapping(value = "/projectQuality/changetime/")
    public ResponseEntity<?> changeFinishDateTime(@Valid @RequestBody CheckQuality checkQuality,  BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("修改出错");
        }
        CheckQualityDTO returnDTO = projectQualityService.changeFinishDateTime(checkQuality);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }
}
