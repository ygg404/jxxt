package cn.ux.jxxt.web;

import cn.ux.jxxt.domain.ProjectShortCut;
import cn.ux.jxxt.dto.ShortCutDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.ShortCutService;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ShortCutController {

    @Autowired
    private ShortCutService shortCutService;

    @Autowired
    private GeneralMessage message;

    /**
     * 获取所有快捷输入语
     * @return
     */
    @GetMapping(value = "/shortcut/")
    public ResponseEntity<?> getAllShort() {
        ShortCutDTO returnDTO = shortCutService.getAllShort();
        if(returnDTO.getProjectList().size() == 0){
            message.setMessage("未录入数据");
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.ok(returnDTO.getProjectList());
        }
    }

    /**
     * 新增快捷输入语
     * @param shortCut
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/shortcut/")
    public ResponseEntity<?> addShortCut(@Valid @RequestBody ProjectShortCut shortCut, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("新增出错",bindingResult);
        }
        ShortCutDTO returnDTO = shortCutService.insertShort(shortCut);
        if(returnDTO.getError() != null){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 获取短语类型
     * @return
     */
    @GetMapping(value = "/shortcut/type/")
    public ResponseEntity<?> getShortCut(){
        ShortCutDTO returnDTO = shortCutService.getAllShortType();
        return ResponseEntity.ok(returnDTO.getTypeList());
    }

    @GetMapping(value = "/shortcut/{typeId}/")
    public ResponseEntity<?> getShortByTypeId(@PathVariable("typeId") Long typeId){
        ShortCutDTO returnDTO = shortCutService.getShortList(typeId);
        return ResponseEntity.ok(returnDTO.getProjectList());
    }

    /**
     * 更新快捷输入语
     * @param cutId
     * @param projectShortCut
     * @param bindingResult
     * @return
     */
    @PutMapping(value = "/shortcut/{cutId}/")
    public ResponseEntity<?> updateShort(@PathVariable("cutId") Long cutId, @RequestBody ProjectShortCut projectShortCut, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新出错",bindingResult);
        }
        ShortCutDTO returnDTO = shortCutService.updateShort(cutId,projectShortCut);
        if(returnDTO.getError() !=null){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectShortCut());
        }
    }

    /**
     * 删除快捷输入语
     * @param cutId
     * @return
     */
    @DeleteMapping(value = "/shortcut/{cutId}/")
    public ResponseEntity<?> deleteShort(@PathVariable("cutId") Long cutId){
        ShortCutDTO returnDTO = shortCutService.deleteShort(cutId);
        if(returnDTO.getError() == null){
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 获取分页快捷输入语
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @return
     */
    @GetMapping(value = "/shortcut/",params = {"page","rowsPerPage","sortBy","descending"})
    public ResponseEntity<?> getShortByPagination(@RequestParam("page") int page,
                                                  @RequestParam("rowsPerPage") int per_page,
                                                  @RequestParam("sortBy") String sortBy,
                                                  @RequestParam("descending") boolean descending){
        ShortCutDTO returnDTO = shortCutService.getAllShortByPaginated(page, per_page, sortBy, descending);
        return ResponseEntity.ok(returnDTO.getShortCutPagination());
    }
}
