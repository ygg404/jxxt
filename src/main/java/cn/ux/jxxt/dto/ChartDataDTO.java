package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.ChartData;

import java.util.List;

public class ChartDataDTO extends BasicDTO {
    private ChartData chartData;
    private List<ChartData> chartDataList;

    public ChartData getChartData() {
        return chartData;
    }

    public void setChartData(ChartData chartData) {
        this.chartData = chartData;
    }

    public List<ChartData> getChartDataList() {
        return chartDataList;
    }

    public void setChartDataList(List<ChartData> chartDataList) {
        this.chartDataList = chartDataList;
    }
}
