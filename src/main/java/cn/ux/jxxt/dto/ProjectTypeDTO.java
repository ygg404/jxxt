package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.ProjectType;
import cn.ux.jxxt.vo.Pagination;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class ProjectTypeDTO extends BasicDTO {
    private Long id;
    @NotBlank(message = "名称不能为空")
    private String name;
    private List<ProjectType> projectLists;
    private ProjectType projectType;
    private Pagination<ProjectType> pagination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectType> getProjectLists() {
        return projectLists;
    }

    public void setProjectLists(List<ProjectType> projectLists) {
        this.projectLists = projectLists;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Pagination<ProjectType> getPagination() {
        return pagination;
    }

    public void setPagination(Pagination<ProjectType> pagination) {
        this.pagination = pagination;
    }
}
