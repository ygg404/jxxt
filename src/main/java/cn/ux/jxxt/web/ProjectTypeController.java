package cn.ux.jxxt.web;

import cn.ux.jxxt.dto.ProjectTypeDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.ProjectTypeService;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProjectTypeController {

    @Autowired
    private ProjectTypeService projectTypeService;

    @Autowired
    private GeneralMessage message;

    /**
     * 获取所有项目类型
     *
     * @return
     */
    @GetMapping(value = "/projectTypes/")
    public ResponseEntity<?> getType() {
        ProjectTypeDTO returnDTO = projectTypeService.getType();
        if (returnDTO.getProjectLists().size() == 0) {
            return ResponseEntity.badRequest().body("未录入数据");
        } else {
            return ResponseEntity.ok(returnDTO.getProjectLists());
        }

    }

    /**
     * 新增项目类型
     *
     * @param projectTypeDTO
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/projectTypes/")
    public ResponseEntity<?> addType(@Valid @RequestBody ProjectTypeDTO projectTypeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("新增类型错误", bindingResult);
        }

        ProjectTypeDTO returnDTO = projectTypeService.addType(projectTypeDTO);
        if (returnDTO.getError() == null) {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        } else {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 根据id更新项目类型
     *
     * @param typeId
     * @param projectTypeDTO
     * @return
     */
    @PutMapping(value = "/projectTypes/{typeId}/")
    public ResponseEntity<?> updateType(@PathVariable("typeId") Long typeId, @RequestBody ProjectTypeDTO projectTypeDTO) {
        ProjectTypeDTO returnDTO = projectTypeService.updateType(typeId, projectTypeDTO);
        if (returnDTO.getError() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectType());
        } else {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 根据Id删除对应类型
     *
     * @param typeId
     * @return
     */
    @DeleteMapping(value = "/projectTypes/{typeId}/")
    public ResponseEntity<?> deleteType(@PathVariable("typeId") Long typeId) {
        ProjectTypeDTO returnDTO = projectTypeService.deleteType(typeId);
        if (returnDTO.getError() == null) {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        } else {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 获取项目类型分页数据
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @return
     */
    @GetMapping(value = "/project/types/", params = {"page", "rowsPerPage", "sortBy", "descending"})
    public ResponseEntity<?> getProjectTypes(@RequestParam("page") int page,
                                             @RequestParam("rowsPerPage") int per_page,
                                             @RequestParam("sortBy") String sortBy,
                                             @RequestParam("descending") boolean descending) {
        ProjectTypeDTO returnDTO = projectTypeService.getTypeByPagination(page, per_page, sortBy, descending);
        return ResponseEntity.ok(returnDTO.getPagination());
    }
}
