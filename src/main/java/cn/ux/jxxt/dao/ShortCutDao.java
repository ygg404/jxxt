package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.ProjectShortCut;
import cn.ux.jxxt.domain.ShortType;

import java.util.List;
import java.util.Map;

public interface ShortCutDao {
    /**
     * 获取所有快捷语
     * @return
     */
    public List<ProjectShortCut> queryAllShort();

    /**
     * 新增一条快捷语
     * @param shortCut
     * @return
     */
    public int insertShort(ProjectShortCut shortCut);

    /**
     * 更新指定快捷语
     * @param shortCut
     * @return
     */
    public int updateShort(ProjectShortCut shortCut);

    /**
     * 删除指定快捷语
     * @param cutId
     * @return
     */
    public int deleteShort(Long cutId);

    /**
     * 根据快捷语标题查找指定快捷语
     * @param sName
     * @return
     */
    public ProjectShortCut queryByName(String sName);

    /**
     * 根据id查找指定快捷语
     * @param cutId
     * @return
     */
    public ProjectShortCut queryById(Long cutId);

    /**
     * 获取所有快捷输入语总数
     * @param params
     * @return
     */
    public Long getShortNum(Map<String, Object> params);

    /**
     * 获取分页快捷输入语数据
     * @param params
     * @return
     */
    public List<ProjectShortCut> getShortByPagination(Map<String, Object> params);

    /**
     * 获取短语类型
     * @return
     */
    public List<ShortType> getShortType();

    /**
     * 根据短语名称获取id
     * @param typeName
     * @return
     */
    public Long getTypeId(String typeName);

    public List<ProjectShortCut> getShortList(Long typeId);
}
