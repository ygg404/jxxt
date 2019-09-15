package cn.ux.jxxt.dao;

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
}
