package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.WorkProjectType;
import cn.ux.jxxt.domain.WorkType;
import cn.ux.jxxt.vo.Pagination;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

public class WorkTypeDTO extends BasicDTO {
    private Long id;
    @NotBlank(message = "类型名称不能为空")
    private String typeName;
    @NotBlank(message = "必须要有产值单位")
    private String typeUnit;
    @NotBlank(message = "产值不能为空")
    private Float typeOutput;
    private Timestamp startTime;
    private Timestamp updateTime;
    private List<WorkType> typeList;
    private WorkType workType;
    private Pagination<WorkType> typePagination;
    private List<Long> wpTypeNotInList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeUnit() {
        return typeUnit;
    }

    public void setTypeUnit(String typeUnit) {
        this.typeUnit = typeUnit;
    }

    public Float getTypeOutput() {
        return typeOutput;
    }

    public void setTypeOutput(Float typeOutput) {
        this.typeOutput = typeOutput;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<WorkType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<WorkType> typeList) {
        this.typeList = typeList;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public Pagination<WorkType> getTypePagination() {
        return typePagination;
    }

    public void setTypePagination(Pagination<WorkType> typePagination) {
        this.typePagination = typePagination;
    }

    public List<Long> getWpTypeNotInList() { return  this.wpTypeNotInList;}

    public void setWpTypeNotInList(List<Long> wpTypeNotInList){this.wpTypeNotInList = wpTypeNotInList;}

}
