package cn.ux.jxxt.domain;

import java.sql.Timestamp;

public class BackWork {
    private long id;
    private String backNote;                            //返修意见
    private String projectNo;                           //项目编号
    private String createTime;                          //创建时间
    private Timestamp backCreatedTime;                  //创建时间
    private Timestamp submitCreatedTime;                //提交时间
    private int backDateNum;                            //返修天数
    private int backStage;
    private int groupId;
    private String backcreatedtime;                 //创建时间
    private String note;                    //提交内容

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBackNote() {
        return backNote;
    }

    public void setBackNote(String backNote) {
        this.backNote = backNote;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public Timestamp getBackCreatedTime() {
        return backCreatedTime;
    }

    public void setBackCreatedTime(Timestamp backCreatedTime) {
        this.backCreatedTime = backCreatedTime;
    }

    public Timestamp getSubmitCreatedTime() {
        return submitCreatedTime;
    }

    public void setSubmitCreatedTime(Timestamp submitCreatedTime) {
        this.submitCreatedTime = submitCreatedTime;
    }

    public int getBackStage() {
        return backStage;
    }

    public void setBackStage(int backStage) {
        this.backStage = backStage;
    }

    public int getBackDateNum() {
        return backDateNum;
    }

    public void setBackDateNum(int backDateNum) {
        this.backDateNum = backDateNum;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBackcreatedtime() {
        return backcreatedtime;
    }

    public void setBackcreatedtime(String backcreatedtime) {
        this.backcreatedtime = backcreatedtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
