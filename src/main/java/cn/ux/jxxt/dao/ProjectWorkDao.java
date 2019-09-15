package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.ProjectWork;

public interface ProjectWorkDao {
    /**
     * 添加项目作业数据
     * @param projectWork
     * @return
     */
    public int addProjectWork(ProjectWork projectWork);

    /**
     * 添加项目完成时间
     * @param projectWork
     * @return
     */
    public int addFinishDateTime(ProjectWork projectWork);

    /**
     * 获取项目完成时间
     * @param projectNo
     * @return
     */
    public String getFinishDateTime(String projectNo);

    /**
     * 更新作业内容
     * @param projectWork
     * @return
     */
    public int updateWork(ProjectWork projectWork);

    /**
     * 获取对应项目作业数据
     * @param project_no
     * @return
     */
    public ProjectWork getWorkData(String project_no);
}
