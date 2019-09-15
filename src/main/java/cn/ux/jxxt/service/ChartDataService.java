package cn.ux.jxxt.service;

import cn.ux.jxxt.dto.ChartDataDTO;
import cn.ux.jxxt.dto.OutPutDTO;
import cn.ux.jxxt.dto.ProjectDTO;

public interface ChartDataService {

    ChartDataDTO getChartData(String startDate, String endDate, String project_charge);

    OutPutDTO getOutPut(String startDate, String endDate);

    OutPutDTO getOutPut2(String startDate, String endDate, int workId);

    OutPutDTO getQualityChart(String startDate, String endDate, int workId);

    ProjectDTO getprojectCharge();

}
