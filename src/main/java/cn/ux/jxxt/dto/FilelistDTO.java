package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.FileData;
import cn.ux.jxxt.domain.Filelist;
import cn.ux.jxxt.vo.Pagination;

import java.util.List;

public class FilelistDTO extends BasicDTO{

    public Filelist filelist;

    public List<FileData> fileDataList;

    public String fileUrl;

    public String fileName;

    private Pagination<FileData> fileDataPagination;      //分页数据


    public Pagination<FileData> getFileDataPagination() {
        return fileDataPagination;
    }

    public void setFileDataPagination(Pagination<FileData> fileDataPagination) {
        this.fileDataPagination = fileDataPagination;
    }

    //设置文件保存位置
    public void setFileUrl(String url){this.fileUrl = url;}

    public String getFileUrl(){return  this.fileUrl;}

    //设置文件名称
    public void setFileName(String name){this.fileName = name;}

    public String getFileName(){return  this.fileName;}
}
