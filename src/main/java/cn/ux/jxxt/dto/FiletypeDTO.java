package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.FileType;

import java.lang.reflect.Type;
import java.util.List;

public class FiletypeDTO extends BasicDTO {

    private FileType fileType;

    private List<FileType> fileTypeList;

    public void setFileTypeList(List<FileType> fileTypeData) {
        this.fileTypeList = fileTypeData;
    }

    public List<FileType> getFileTypeList(){
        return fileTypeList;
    }

    //通过父id获取所有列表信息
    public List<FileType> getFileTypeListByPid(int pid){
        return fileTypeList;
    }

    public void setFileType(FileType fileType){this.fileType = fileType;}

    public FileType getFileType(){return this.fileType;}

}
