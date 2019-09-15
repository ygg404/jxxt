package cn.ux.jxxt.web;

import cn.ux.jxxt.domain.FileType;
import cn.ux.jxxt.dto.FiletypeDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.FileTypeService;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.channels.FileLockInterruptionException;
import java.util.List;

@RestController
public class FiletypeController {
    @Autowired
    private FileTypeService fileTypeService;

    @Autowired
    private GeneralMessage message;

    /**
     * 获取文件类型列表
     * @return
     */
    @GetMapping(value = "/filetype/getFileTypeList")
    public ResponseEntity<?> getFileTypeList() {
        FiletypeDTO filetypeDTO = fileTypeService.getFileTypeList();
        return ResponseEntity.status(HttpStatus.CREATED).body(filetypeDTO.getFileTypeList());
    }

    @PostMapping(value = "/filetype/addFiletype/")
    public ResponseEntity<?> addFiletype(@Valid @RequestBody FileType fileType, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加文件类别失败，请重新添加", bindingResult);
        }
        int max = fileTypeService.getFiletypeByMax(fileType.getPid());
        fileType.setSort(max + 1);
        FiletypeDTO filetypeDTO = fileTypeService.addFiletype(fileType);
        if (filetypeDTO.getError() != null) {
            message.setMessage(filetypeDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(filetypeDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 删除文件类目
     * @param id
     * @return
     */
    @DeleteMapping(value = "/filetype/", params = {"id"})
    public ResponseEntity<?> deleteFiletype(@RequestParam("id") int id) {
        FiletypeDTO returnDTO = fileTypeService.deleteFiletypeById(id);
        //contractService.deleteContractUserByNo(contractNo);
        if (!TextUtils.isEmpty(returnDTO.getError())) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 更新文件类目
     * @param fileType
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/filetype/update/")
    public ResponseEntity<?> updateFiletype(@Valid @RequestBody FileType fileType, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新文件类目失败，请重新添加", bindingResult);
        }
        FiletypeDTO returnDTO = fileTypeService.updateFiletype(fileType);
        if (returnDTO.getError() != null) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 更新类目顺序
     * @return
     */
    @PostMapping(value = "/filetype/updatesort/")
    public ResponseEntity<?> updateFiletypeSort(@Valid @RequestBody FileType fileType, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新文件类目失败，请重新操作", bindingResult);
        }
        FiletypeDTO returnDTO = new FiletypeDTO();
        //int max = fileTypeService.getFiletypeByMax(fileType.getPid());
        int min = fileTypeService.getFiletypeByMin(fileType.getPid());

        //该类目是最小排序号则不作修改
        if(fileType.getSort() <= min){
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
        else{
            try {
                returnDTO = fileTypeService.getTopFiletypeBySort(fileType);
                FileType topFiletype = returnDTO.getFileType();
                int tempsort = fileType.getSort();
                fileType.setSort(topFiletype.getSort());
                topFiletype.setSort(tempsort);
                fileTypeService.updateFiletypeSort(fileType);
                fileTypeService.updateFiletypeSort(topFiletype);
                message.setMessage(returnDTO.getSuccess());
                return ResponseEntity.ok(message);
            }catch (Exception ex){
                message.setMessage(returnDTO.getError());
                return ResponseEntity.badRequest().body(message);
            }

        }
    }
}
