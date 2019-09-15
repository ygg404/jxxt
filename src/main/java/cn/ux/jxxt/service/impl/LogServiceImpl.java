package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.domain.Log;
import cn.ux.jxxt.dto.LogDTO;
import cn.ux.jxxt.service.LogService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.PaginationUtil;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public LogDTO getLogByPagination(int page, int per_page, String sortBy, boolean descending,String search,String startDate,String endDate) {
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看日志"));
        LogDTO logDTO = new LogDTO();
        Map<String,Object> params = new HashMap<>();
        params.put("per_page",per_page);
        params.put("offset",(page-1) * per_page);
        params.put("sortBy",sortBy);
        params.put("desc",descending);
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim()) && !TextUtils.isEmpty(endDate.trim())) {
            if (startDate.trim().equals(endDate.trim())) {
                params.put("oneDate", startDate);
            } else {
                params.put("startDate", startDate);
                params.put("endDate", endDate);
            }
        }
        Long total = logDao.getLogNum(params);
        List<Log> logs = logDao.getLogByPagination(params);
        for(Log log : logs){
            log.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getOperationTime()));
        }
        logDTO.setLogPagination(PaginationUtil.paginate(page,per_page,total,logs));
        return logDTO;
    }
}
