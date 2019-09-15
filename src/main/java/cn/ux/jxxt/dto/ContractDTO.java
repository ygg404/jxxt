package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.ContractData;
import cn.ux.jxxt.vo.Pagination;

import java.util.List;

public class ContractDTO extends BasicDTO {
    private ContractData contractData;
    private List<ContractData> dataList;
    private Pagination<ContractData> contractDataPagination;      //分页数据

    public ContractData getContractData() {
        return contractData;
    }

    public void setContractData(ContractData contractData) {
        this.contractData = contractData;
    }

    public Pagination<ContractData> getContractDataPagination() {
        return contractDataPagination;
    }

    public void setContractDataPagination(Pagination<ContractData> contractDataPagination) {
        this.contractDataPagination = contractDataPagination;
    }

    public List<ContractData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ContractData> dataList) {
        this.dataList = dataList;
    }
}
