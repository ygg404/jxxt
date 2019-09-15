package cn.ux.jxxt.service;


import cn.ux.jxxt.dto.LogDTO;

public interface LogService {
    LogDTO getLogByPagination(int page, int per_page, String sortBy, boolean descending,String search,String startDate,String endDate);
}
