package cn.ux.jxxt.web;

import cn.ux.jxxt.domain.FileType;
import cn.ux.jxxt.domain.Filelist;
import cn.ux.jxxt.dto.FilelistDTO;
import cn.ux.jxxt.dto.ProjectDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.FileListService;
import cn.ux.jxxt.service.ProjectContractService;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileuploadController {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private GeneralMessage message;

    @Autowired
    private FileListService fileListService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping(value = "/uploadfile")
    public ResponseEntity<?> uploadFile(@PathVariable MultipartFile file,HttpServletRequest req){
//        String contractNo = "ss";
        FilelistDTO returnDTO = fileListService.uploadfile(file);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getFileUrl());
            return ResponseEntity.ok(message);
        }
    }
    /**
     * 下载文件
     * @param fileNo
     * @param response
     * @return
     */
    @GetMapping(value = "/fileList/download/",params = "fileNo")
    public String downloadFile(@RequestParam("fileNo") int fileNo, HttpServletResponse response) {
        FilelistDTO filelistDTO = fileListService.getFilelistByFileNo(fileNo);// 设置文件名，根据业务需要替换成要下载的文件名
        String fileUrl = filelistDTO.getFileUrl();
        String fileName = filelistDTO.getFileName();
        if(TextUtils.isEmpty(fileUrl)){
            return "该文件不存在";
        } else {
            //设置文件路径
            String realPath = uploadFolder;
            File file = new File(realPath , fileUrl);
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
     * 获取上传文件列表
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @param startDate
     * @param endDate
     * @param userId
     * @return
     */
    @GetMapping(value = "/filelist/getFilelist/" ,  params = {"page", "rowsPerPage", "sortBy", "descending","search","startDate","endDate","userId","typeId"})
    public ResponseEntity<?> getFilelist(@RequestParam("page") int page,
                                         @RequestParam("rowsPerPage") int per_page,
                                         @RequestParam("sortBy") String sortBy,
                                         @RequestParam("descending") boolean descending,
                                         @RequestParam("search") String search,
                                         @RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate,
                                         @RequestParam("userId") String userId,
                                         @RequestParam("typeId") String typeId)
    {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        FilelistDTO returnDTO = fileListService.getFileList(page, per_page, sortBy, descending, search,start,end,userId,typeId);
        return ResponseEntity.ok(returnDTO.getFileDataPagination());
    }

    @DeleteMapping(value = "/filelist/", params = {"fileNo"})
    public ResponseEntity<?> deleteFilelist(@RequestParam("fileNo") int fileNo)
    {
        FilelistDTO returnDTO = new FilelistDTO();
        returnDTO = fileListService.deleteFilelistByFileno(fileNo);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 更新上传文件信息
     */
    @PostMapping(value = "/filelist/update/")
    public ResponseEntity<?> updateFilelist(@Valid @RequestBody Filelist filelist, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新文件失败，请重新添加", bindingResult);
        }
        FilelistDTO returnDTO = fileListService.updateFileList(filelist);
        if (returnDTO.getError() != null) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 上传新文件信息
     * @param filelist
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/filelist/addFilelist/")
    public ResponseEntity<?> addFileList(@Valid @RequestBody Filelist filelist, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新文件类目失败，请重新添加", bindingResult);
        }
        Timestamp date = new Timestamp(new Date().getTime());
        filelist.setCreateTime(date);
        FilelistDTO returnDTO = fileListService.addfileList(filelist);
        if (returnDTO.getError() != null) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }
}
