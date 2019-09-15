package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.ProjectShortCut;
import cn.ux.jxxt.domain.ShortType;
import cn.ux.jxxt.vo.Pagination;

import java.util.List;

public class ShortCutDTO extends BasicDTO {
    private Long id;
    private String ShortName;
    private String ShortNote;
    private ProjectShortCut projectShortCut;
    private List<ProjectShortCut> projectList;
    private List<ShortType> typeList;
    private Pagination<ProjectShortCut> shortCutPagination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getShortNote() {
        return ShortNote;
    }

    public void setShortNote(String shortNote) {
        ShortNote = shortNote;
    }

    public ProjectShortCut getProjectShortCut() {
        return projectShortCut;
    }

    public void setProjectShortCut(ProjectShortCut projectShortCut) {
        this.projectShortCut = projectShortCut;
    }

    public List<ProjectShortCut> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectShortCut> projectList) {
        this.projectList = projectList;
    }

    public Pagination<ProjectShortCut> getShortCutPagination() {
        return shortCutPagination;
    }

    public void setShortCutPagination(Pagination<ProjectShortCut> shortCutPagination) {
        this.shortCutPagination = shortCutPagination;
    }

    public List<ShortType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<ShortType> typeList) {
        this.typeList = typeList;
    }
}
