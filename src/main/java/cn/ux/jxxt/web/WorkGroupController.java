package cn.ux.jxxt.web;

import cn.ux.jxxt.dto.WorkGroupDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.WorkGroupService;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WorkGroupController {

    @Autowired
    private WorkGroupService workGroupService;

    @Autowired
    private GeneralMessage message;

    /**
     * 增加作业组数据
     * @param workGroup
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/workGroups/")
    public ResponseEntity<?> addWorkGroup(@Valid @RequestBody WorkGroupDTO workGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("新增作业组失败", bindingResult);
        }
        WorkGroupDTO returnDTO = workGroupService.addGroup(workGroup);
        if (returnDTO.getWorkGroup() != null) {
            return ResponseEntity.status(HttpStatus.CREATED ).body(returnDTO.getWorkGroup());
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 获取所有作业组数据（分页）
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @return
     */
    @GetMapping(value = "/workGroups/",params = {"page","rowsPerPage","sortBy","descending","search"})
    public ResponseEntity<?> getGroupsByPaginated(@RequestParam("page") int page,
                                                  @RequestParam("rowsPerPage") int per_page,
                                                  @RequestParam("sortBy") String sortBy,
                                                  @RequestParam("descending") boolean descending,
                                                  @RequestParam("search") String search){
        WorkGroupDTO returnDTO = workGroupService.getGroupByPaginated(page,per_page,sortBy,descending,search);
        return ResponseEntity.ok(returnDTO.getGroupPagination());
    }

    /**
     * 获取所有作业组数据不分页
     * @return
     */
    @GetMapping(value = "/workGroups/")
    public ResponseEntity<?> getGroupsNotPaginated(){
        WorkGroupDTO returnDTO = workGroupService.getGroup();
        return ResponseEntity.ok(returnDTO.getWorkGroups());
    }

    /**
     * 根据组id删除组
     * @param groupId
     * @return
     */
    @DeleteMapping(value = "/workGroups/{groupId}/")
    public ResponseEntity<?> deleteGroup (@PathVariable("groupId") Long groupId){
        WorkGroupDTO returnDTO = workGroupService.deleteGroup(groupId);
        if(returnDTO.getError() == null){
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 根据作业组id更新作业组
     * @param groupId
     * @param workGroupDTO
     * @return
     */
    @PutMapping(value = "/workGroups/{groupId}/")
    public ResponseEntity<?> updateGroup(@PathVariable("groupId") Long groupId,@RequestBody WorkGroupDTO workGroupDTO){
        WorkGroupDTO returnDTO = workGroupService.updateGroup(groupId,workGroupDTO);
        if(returnDTO.getError() == null){
            return ResponseEntity.status(HttpStatus.CREATED ).body(returnDTO.getWorkGroup());
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 根据项目编号获取作业组名
     * @param projectNo
     * @return
     */
    @GetMapping(value = "/projectWorkName/",params = "projectNo")
    public ResponseEntity<?> getWorkName(@RequestParam("projectNo")String projectNo){
        List<String> list = workGroupService.getWorkName(projectNo);
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    /**
     * 调转位置
     * @param workGroup
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/group/position/")
    public ResponseEntity<?> updateFid(@Valid @RequestBody WorkGroupDTO workGroup, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("替换失败",bindingResult);
        }
        WorkGroupDTO returnDTO = workGroupService.upItem(workGroup);
        message.setMessage(returnDTO.getSuccess());
        return ResponseEntity.ok(message);
    }

    //查询用户所属得那个工作组的id
    @GetMapping(value = "/selectGroup/",params = "userAccount")
    public ResponseEntity<?> selectGroup(@RequestParam("userAccount")String userAccount){
        WorkGroupDTO returnDTO = workGroupService.selectGroup(userAccount);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getWorkGroup());
        }
    }
}
