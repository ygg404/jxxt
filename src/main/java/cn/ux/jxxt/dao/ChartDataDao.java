package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.ChartData;
import cn.ux.jxxt.domain.PlanRate;
import cn.ux.jxxt.domain.WorkGroup;
import cn.ux.jxxt.domain.custom.OutPutWrap;

import java.util.List;
import java.util.Map;

public interface ChartDataDao {

    /**
     * 获取收费统计内容
     * @return
     */
    public List<ChartData> getCharDatas(Map<String, Object> params);

    /**
     * 根据id获取小队项目
     * @return
     */
    public List<PlanRate> getProjectSum(Map<String, Object> params);

    /**
     * 根据id 获取小队已完成项目
     */
    public List<PlanRate> getFinishProjectSum(Map<String, Object> params);

    /**
     * 根据getProject_no计算产值
     * @return
     */
    public List<OutPutWrap> getOutputwrap(String getProject_no);

    /**
     * 查询所有作业组
     * @return
     */
    public List<WorkGroup> getGroup(int workId);

    public List<PlanRate> getProjects(long id);

    public List<PlanRate> getProjectQuality(Map<String, Object> params);

    public List<PlanRate> getQuality(long id);

    public List<PlanRate> getProjects(Map<String, Object> params);

    public List<PlanRate> getQuality(Map<String, Object> params);

    public String getUserName(String userAccount);

}
