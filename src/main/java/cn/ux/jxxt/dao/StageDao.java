package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.ProjectStage;

import java.util.List;

public interface StageDao {
    /**
     * 查找所有项目阶段
     * @return
     */
    public List<ProjectStage> queryStage();

    /**
     * 新增项目阶段
     * @param projectStage
     * @return
     */
    public int insertStage(ProjectStage projectStage);

    /**
     * 更新项目阶段
     * @param projectStage
     * @return
     */
    public int updateStage(ProjectStage projectStage);

    /**
     * 删除项目阶段
     * @param stageId
     * @return
     */
    public int deleteById(Long stageId);

    /**
     * 根据阶段名称查询对应项目阶段
     * @param name
     * @return
     */
    public ProjectStage queryByName(String name);

    /**
     * 根据阶段id查询对应项目阶段
     * @param stageId
     * @return
     */
    public ProjectStage queryById(Long stageId);
}
