package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.ContractData;
import cn.ux.jxxt.dto.ContractDTO;

import java.util.List;


public interface ProjectContractService {

    ContractDTO addContract(ContractData contractData);

    ContractDTO getContractList(int page, int per_page, String sortBy, boolean descending, String search, String startDate,String endDate);

    ContractDTO deleteContract(String contractNo);

    ContractDTO getContractInfo(String contractNo);

    ContractDTO updateContract(ContractData contractData);

    ContractDTO addProjectToContract(ContractData contractData);

    ContractDTO updateProjectToContract(ContractData contractData);

    ContractDTO getContractUnselected();

    List<String> getContractNumList(String ymstr);

    void deleteContractUserByNo(String contractNo);
}
