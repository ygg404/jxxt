package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.UserGroup;
import cn.ux.jxxt.domain.WorkGroup;
import cn.ux.jxxt.vo.Pagination;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class WorkGroupDTO extends BasicDTO {
    private Long id;
    private String gName;                   //作业组名称
    private Timestamp createTime;              //创建时间
    private String headMan;                 //组长
    private String deputyLeader;            //副组长
    private Long fId;
    private long headId;                    //组长id
    private long deputyId;                  //副组长id
    private WorkGroup workGroup;
    private List<WorkGroup> workGroups;
    private UserGroup userGroup;                //新作业组数据
    private Pagination<WorkGroup> groupPagination;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getHeadMan() {
        return headMan;
    }

    public void setHeadMan(String headMan) {
        this.headMan = headMan;
    }

    public String getDeputyLeader() {
        return deputyLeader;
    }

    public void setDeputyLeader(String deputyLeader) {
        this.deputyLeader = deputyLeader;
    }

    public WorkGroup getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(WorkGroup workGroup) {
        this.workGroup = workGroup;
    }

    public List<WorkGroup> getWorkGroups() {
        return workGroups;
    }

    public void setWorkGroups(List<WorkGroup> workGroups) {
        this.workGroups = workGroups;
    }

    public Pagination<WorkGroup> getGroupPagination() {
        return groupPagination;
    }

    public void setGroupPagination(Pagination<WorkGroup> groupPagination) {
        this.groupPagination = groupPagination;
    }

    public long getHeadId() {
        return headId;
    }

    public void setHeadId(long headId) {
        this.headId = headId;
    }

    public long getDeputyId() {
        return deputyId;
    }

    public void setDeputyId(long deputyId) {
        this.deputyId = deputyId;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }
}
