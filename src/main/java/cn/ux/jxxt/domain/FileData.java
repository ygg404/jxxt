package cn.ux.jxxt.domain;

import java.sql.Timestamp;

public class FileData {
    private int fileNo;
    private String fileName;
    private String fileUrl;
    private int filetypeId;
    private String filetype;
    private int createUserId;
    private String createUser;
    private String createTime;

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

    public void setFiletype(String filetype){ this.filetype = filetype;};

    public String getFiletype(){return this.filetype;}

    public void setCreateUserId(int createUserId){
        this.createUserId = createUserId;
    }

    public int getCreateUserId(){
        return  this.createUserId;
    }

    public void setCreateUser(String createUser){this.createUser = createUser;}

    public String getCreateUser(){return  this.createUser;}

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    public String getCreateTime(){return  this.createTime;}
}
