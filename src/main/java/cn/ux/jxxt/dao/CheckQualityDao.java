package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.CheckQuality;
import cn.ux.jxxt.domain.ProjectPlan;
import cn.ux.jxxt.domain.ProjectWork;
import cn.ux.jxxt.domain.QualityScore;

import java.util.List;
import java.util.Map;

public interface CheckQualityDao {
    /**
     * 添加质量检查内容
     * @param checkQuality
     * @return
     */
    public int addQualityData(CheckQuality checkQuality);

    /**
     * 更新质量检查内容
     * @param checkQuality
     * @return
     */
    public int updateQuality(CheckQuality checkQuality);

    /**
     * 更新质量审核人
     * @param params
     * @return
     */
    public int updateQualityFromUser(Map<String,Object> params);

    /**
     * 获取质量检查内容
     * @param project_no
     * @return
     */
    public CheckQuality getQualityData(String project_no);

    /**
     * 添加质量分数
     * @param qualityScore
     * @return
     */
    public int addQualityScore(QualityScore qualityScore);

    /**
     * 更新质量分数
     * @param qualityScore
     * @return
     */
    public int updateQualityScore(QualityScore qualityScore);

    /**
     * 获取质量分数
     * @param projectNo
     * @return
     */
    public List<QualityScore> getQualityScore(String projectNo);

    /**
     * 获取单项数据
     * @param params
     * @return
     */
    public QualityScore getQualityScoreByTypeId(Map<String,Object> params);

    /**
     * 添加质检完成时间
     * @param checkQuality
     * @return
     */
    public int addFinishDateTime(CheckQuality checkQuality);

    /**
     * 获取质检完成时间
     * @param projectNo
     * @return
     */
    public String getFinishDateTime(String projectNo);

    /**
     * 获取安排数据
     * @param projectNo
     * @return
     */
    public ProjectPlan getPlanData(String projectNo);

    /**
     * 获取项目作业数据
     * @param projectNo
     * @return
     */
    public ProjectWork getWorkData(String projectNo);

    /**
     * 获取质检员账号
     * @param projectNO
     * @return
     */
    public String getQualityAccount(String projectNO);

    /**
     * 添加质检员
     * @param params
     * @return
     */
    public int updateQualityFromQuser(Map<String,Object> params);

}
