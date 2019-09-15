package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.WorkGroup;
import cn.ux.jxxt.vo.Pagination;

import java.util.List;

public class OutPutDTO extends BasicDTO {
    private WorkGroup workGroups;
    List<WorkGroup> workGroupList;
    private Pagination<WorkGroup> projectwoirkPagination;

    public WorkGroup getWorkGroups() {
        return workGroups;
    }

    public void setWorkGroups(WorkGroup workGroups) {
        this.workGroups = workGroups;
    }

    public List<WorkGroup> getWorkGroupList() {
        return workGroupList;
    }

    public void setWorkGroupList(List<WorkGroup> workGroupList) {
        this.workGroupList = workGroupList;
    }

    public Pagination<WorkGroup> getProjectwoirkPagination() {
        return projectwoirkPagination;
    }

    public void setProjectwoirkPagination(Pagination<WorkGroup> projectwoirkPagination) {
        this.projectwoirkPagination = projectwoirkPagination;
    }
}
