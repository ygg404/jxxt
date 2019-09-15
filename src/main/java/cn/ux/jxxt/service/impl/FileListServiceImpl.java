package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.FileListDao;
import cn.ux.jxxt.dao.FileTypeDao;
import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.domain.FileData;
import cn.ux.jxxt.domain.Filelist;
import cn.ux.jxxt.dto.FilelistDTO;
import cn.ux.jxxt.service.FileListService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.PaginationUtil;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileListServiceImpl implements FileListService {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private LogDao logDao;

    @Autowired
    private FileListDao fileListDao;

    @Override
    public FilelistDTO getFileList(int page, int per_page, String sortBy, boolean descending, String search, String startDate, String endDate,String userId,String typeId){
        FilelistDTO  filelistDTO = new FilelistDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        if(!TextUtils.isEmpty(userId.trim())){
            params.put("userId",userId.trim());
        }
        if(!TextUtils.isEmpty(typeId.trim())){
            params.put("typeId",typeId.trim());
        }
        Long total = fileListDao.getFilelistCount(params);
        List<FileData> filedataList = fileListDao.getFiledataList(params);
        for(FileData filedata :filedataList){
            if(!TextUtils.isEmpty( filedata.getCreateTime()) ) {
                filedata.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(TextUtils.stringToTimeStamp(filedata.getCreateTime())));
            }
        }
        filelistDTO.setFileDataPagination(PaginationUtil.paginate(page, per_page, total, filedataList));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看文件管理"));
        return filelistDTO;
    }

    @Override
    public FilelistDTO deleteFilelistByFileno(int fileNo){
        FilelistDTO filelistDTO = new FilelistDTO();
        try {
            fileListDao.deleteFilelistByFileno(fileNo);
            filelistDTO.setSuccess("删除成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return filelistDTO;
    }

    @Override
    public FilelistDTO getFilelistByFileNo(int fileNo){
            FilelistDTO filelistDTO = new FilelistDTO();
            FileData filelist = fileListDao.getFiledataListByFileNo(fileNo);
            filelistDTO.setFileUrl(filelist.getFileUrl());
            filelistDTO.setFileName(filelist.getFileName());
            return filelistDTO;
    }

    @Override
    public FilelistDTO addfileList(Filelist filelist){
        FilelistDTO filelistDTO = new FilelistDTO();
        fileListDao.addFilelist(filelist);
        filelistDTO.setSuccess("添加成功");
        return filelistDTO;
    }

    @Override
    public FilelistDTO updateFileList(Filelist filelist)
    {
        FilelistDTO filelistDTO = new FilelistDTO();
        fileListDao.updateFilelist(filelist);
        filelistDTO.setSuccess("更新成功");
        return filelistDTO;
    }

    @Override
    public FilelistDTO uploadfile(MultipartFile file){
        FilelistDTO returnDTO = new FilelistDTO();
        try {
            if (file.isEmpty()) {
                returnDTO.setError("文件为空");
                return returnDTO;
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();

            // 设置文件存储路径
            String filePath = uploadFolder;
            String fileNewName =fileName.substring(0,fileName.lastIndexOf(".")) + (new Date()).getTime()  + fileName.substring(fileName.lastIndexOf("."));
            String path = filePath + fileNewName;

            File dest = new File(path);
            // 检测是否存在目录

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            returnDTO.setFileUrl(fileNewName);
            returnDTO.setSuccess("上传成功");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnDTO;
    }
}
