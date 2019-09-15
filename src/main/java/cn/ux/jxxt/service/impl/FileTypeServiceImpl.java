package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.FileTypeDao;
import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.domain.FileType;
import cn.ux.jxxt.dto.FiletypeDTO;
import cn.ux.jxxt.service.FileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileTypeServiceImpl implements FileTypeService {
    @Autowired
    private FileTypeDao fileTypeDao;

    @Override
    public FiletypeDTO getFileTypeList(){
        FiletypeDTO fileTypeDTO = new FiletypeDTO();
        List<FileType> fileTypeList = fileTypeDao.getFileTypeList();
        fileTypeDTO.setFileTypeList(fileTypeList);
        return fileTypeDTO;
    }

    @Override
    public FiletypeDTO addFiletype(FileType filetype){
        FiletypeDTO returnDTO = new FiletypeDTO();
        fileTypeDao.addFiletype(filetype);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    @Override
    public FiletypeDTO deleteFiletypeById(int id){
        FiletypeDTO returnDTO = new FiletypeDTO();
        fileTypeDao.deleteFiletypeById(id);
        returnDTO.setSuccess("删除成功");
        return returnDTO;
    }

    @Override
    public FiletypeDTO getFileTypeListByPid(int pid){
        FiletypeDTO returnDTO = new FiletypeDTO();
        List<FileType> fileTypeList = fileTypeDao.getFileTypeListByPid(pid);
        returnDTO.setFileTypeList(fileTypeList);
        return returnDTO;
    }

    @Override
    public FiletypeDTO updateFiletype(FileType fileType){
        FiletypeDTO returnDTO = new FiletypeDTO();
        fileTypeDao.updateFiletype(fileType);
        returnDTO.setSuccess("更新成功");
        return returnDTO;
    }

    @Override
    public int getFiletypeByMax(int pid){
        return fileTypeDao.getFiletypeByMax(pid);
    }

    @Override
    public int getFiletypeByMin(int pid){
        return fileTypeDao.getFiletypeByMin(pid);
    }

    @Override
    public FiletypeDTO getTopFiletypeBySort(FileType fileType){
        FiletypeDTO returnDTO = new FiletypeDTO();
        FileType fType = fileTypeDao.getTopFiletypeBySort(fileType);
        returnDTO.setFileType(fType);
        return returnDTO;
    }

    @Override
    public FiletypeDTO updateFiletypeSort(FileType fileType){
        FiletypeDTO returnDTO = new FiletypeDTO();
        fileTypeDao.updateFiletypeSort(fileType);
        return  returnDTO;
    }
}
