package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.WorkProjectType;
import cn.ux.jxxt.domain.WorkType;

import java.util.List;
import java.util.Map;

public interface WorkTypeDao {

    /**
     * 获取所有工作类型
     * @return
     */
    public List<WorkType> getAllType();

    /**
     * 更新指定的类型
     * @param type
     * @return
     */
    public int updateById(WorkType type);

    /**
     * 新增工作类型
     * @param type
     * @return
     */
    public int insertWorkType(WorkType type);

    /**
     * 删除指定的类型
     * @param typeId
     * @return
     */
    public int deleteType(Long typeId);

    /**
     * 根据类型名称查找
     * @param typeName
     * @return
     */
    public WorkType getTypeByName(String typeName);

    /**
     * 根据类型Id查找
     * @param typeId
     * @return
     */
    public WorkType getTypeById(Long typeId);

    /**
     * 获取总数
     * @param params
     * @return
     */
    public Long getTypeNum(Map<String, Object> params);

    /**
     * 获取分类分页数据
     * @param params
     * @return
     */
    public List<WorkType> queryTypesPaginated(Map<String, Object> params);

    /**
     * 获取工作类型和所属项目类型
     * @param typeId
     * @return
     */
    public List<WorkProjectType>  getWorkTypeById(int typeId);

    /**
     * 插入项目与作业间的关系表
     * @param type
     */
    public void insertWorkProjectType(WorkProjectType type);

    /**
     * 根据工作类型id 删除
     * @param wtypeId
     */
    public void deleteWorkProjectType(int wtypeId);

    /**
     * 获取项目和工作类型列表
     * @return
     */
    public List<WorkProjectType> getWorkProjectList();

    /**
     * 获取不在作业项目关系表中的工作类型id
     * @return
     */
    public List<Long> getWorkIdNotIn();


    /**
     * 通过项
     * @param ptypeId
     * @return
     */
    public List<Long> getWorkTypeListByPid(long ptypeId);
}
