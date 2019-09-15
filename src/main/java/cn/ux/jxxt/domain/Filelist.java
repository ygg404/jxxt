package cn.ux.jxxt.domain;

import java.sql.Timestamp;
import java.util.Date;

public class Filelist {
    private int fileNo;
    private String fileName;
    private String fileUrl;
    private int filetypeId;
    private int createUserId;
    private Timestamp createTime;

    public void setFileNo(int fileNo){
        this.fileNo = fileNo;
    }

    public int getFileNo(){
        return this.fileNo;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public void setFileUrl(String fileUrl){
        this.fileUrl = fileUrl;
    }

    public String getFileUrl(){
        return this.fileUrl;
    }

    public void setFiletypeId(int filetypeId){ this.filetypeId = filetypeId;}

    public int getFiletypeId(){return this.filetypeId;}

    public void setCreateUserId(int createUserId){
        this.createUserId = createUserId;
    }

    public int getCreateUserId(){
        return  this.createUserId;
    }

    public void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }

    public Timestamp getCreateTime(){return  this.createTime;}
}
