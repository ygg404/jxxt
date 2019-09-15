package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.ProjectStage;

import java.util.List;

public class StageDTO extends BasicDTO {

    private Long id;
    private String name;
    private ProjectStage projectStage;
    private List<ProjectStage> list;

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

    public ProjectStage getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(ProjectStage projectStage) {
        this.projectStage = projectStage;
    }

    public List<ProjectStage> getList() {
        return list;
    }

    public void setList(List<ProjectStage> list) {
        this.list = list;
    }
}
