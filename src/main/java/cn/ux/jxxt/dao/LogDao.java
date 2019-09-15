package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.Log;

import java.util.List;
import java.util.Map;

public interface LogDao {
    /**
     * 获取查询总数
     * @param params
     * @return
     */
    public Long getLogNum(Map<String, Object> params);

    /**
     * 获取分页日志信息
     * @param params
     * @return
     */
    public List<Log> getLogByPagination(Map<String, Object> params);

    /**
     * 添加Log
     * @param log
     * @return
     */
    public int addLog(Log log);

    /**
     * 删除Log
     * @param log
     * @return
     */
    public int deleteLog(Log log);

}
