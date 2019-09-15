package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.ProjectType;
import cn.ux.jxxt.dto.ProjectTypeDTO;

import java.util.List;
import java.util.Map;

public interface ProjectTypeDao {
    /**
     * 查询所有类型
     * @return
     */
    public List<ProjectType> queryType();

    /**
     * 新增一条内容
     * @param projectType
     * @return
     */
    public int insertType(ProjectTypeDTO projectType);

    /**
     * 根据id更新指定内容
     * @param projectType
     * @return
     */
    public int updateType(ProjectTypeDTO projectType);

    /**
     * 根据Id删除指定内容
     * @param typeId
     * @return
     */
    public int deleteById(Long typeId);

    /**
     * 根据名称删除指定内容
     * @param typeName
     * @return
     */
    public int deleteByName(String typeName);

    /**
     * 根据Id查询类型数据
     * @param typeId
     * @return
     */
    public ProjectType queryTypeById(Long typeId);

    /**
     * 根据名称查询类型数据
     * @param typeName
     * @return
     */
    public ProjectType queryTypeByName(String typeName);

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
    public List<ProjectType> queryTypesPaginated(Map<String, Object> params);

}
