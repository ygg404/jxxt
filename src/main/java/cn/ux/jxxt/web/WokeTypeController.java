package cn.ux.jxxt.web;

import cn.ux.jxxt.domain.WorkType;
import cn.ux.jxxt.dto.WorkTypeDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.WorkTypeService;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class WokeTypeController {

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private GeneralMessage message;

    @GetMapping(value = "/workType/",params = {"page","rowsPerPage","sortBy","descending","search"})
    public ResponseEntity<?> getAllByPagination(@RequestParam("page") int page,
                                                @RequestParam("rowsPerPage") int per_page,
                                                @RequestParam("sortBy") String sortBy,
                                                @RequestParam("descending") boolean descending,
                                                @RequestParam("search") String search){
        WorkTypeDTO returnDTO = workTypeService.getAllTypeByPaginated(page, per_page, sortBy, descending, search);
        return ResponseEntity.ok(returnDTO.getTypePagination());
    }

    /**
     * 获取所有工作类型
     * @return
     */
    @GetMapping(value = "/workType/")
    public ResponseEntity<?> getAllWork(){
        WorkTypeDTO workTypeDTO = workTypeService.getAllType();
        if(workTypeDTO.getTypeList().size() == 0){
            message.setMessage("暂未录入数据");
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(workTypeDTO.getTypeList());
        }
    }

    /**
     * 新增工作类型
     * @param workType
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/workType/")
    public ResponseEntity<?> insertType(@Valid @RequestBody WorkType workType, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("新增工作类型错误", bindingResult);
        }
        WorkTypeDTO returnDTO = workTypeService.addType(workType);
        if(returnDTO.getError() == null){
            return ResponseEntity.ok(returnDTO.getWorkType());
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 更新对应工作类型
     * @param typeId
     * @param workType
     * @param bindingResult
     * @return
     */
    @PutMapping(value = "/workType/{typeId}/")
    public ResponseEntity<?> updateType(@PathVariable("typeId") Long typeId, @RequestBody WorkType workType, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新项目类型失败",bindingResult);
        }
        WorkTypeDTO returnDTO = workTypeService.updateType(typeId,workType);
        if(returnDTO.getError() == null){
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getWorkType());
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 删除对应工作类型
     * @param typeId
     * @return
     */
    @DeleteMapping(value = "/workType/{typeId}/")
    public ResponseEntity<?> deleteType(@PathVariable("typeId") Long typeId){
        WorkTypeDTO returnDTO = workTypeService.deleteType(typeId);
        if(returnDTO.getError() != null){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }
}
