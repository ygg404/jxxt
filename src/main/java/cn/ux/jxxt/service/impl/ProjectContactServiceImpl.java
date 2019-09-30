package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.ContractDao;
import cn.ux.jxxt.dao.ProjectDao;
import cn.ux.jxxt.domain.ContractData;
import cn.ux.jxxt.domain.Project;
import cn.ux.jxxt.dto.ContractDTO;
import cn.ux.jxxt.service.ProjectContractService;
import cn.ux.jxxt.util.NumUtil;
import cn.ux.jxxt.util.PaginationUtil;
import cn.ux.jxxt.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectContactServiceImpl implements ProjectContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ProjectDao projectDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public ContractDTO addContract(ContractData contractData) {
        ContractDTO returnDTO = new ContractDTO();
        contractData.setContractCreateTime(Timestamp.valueOf(sdf.format(new Date())));
        if(contractDao.getContractByNo(contractData.getContractNo()) != null){
            contractData.setContractNo(Integer.parseInt(contractData.getContractNo())+(int) (Math.random() * 3) + "");
        }
        if(TextUtils.isEmpty(contractData.getContractAddTime())){
            contractDao.addContractNoAddTime(contractData);
        }else{
            contractDao.addContract(contractData);
        }
        contractDao.addContractUser(contractData);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    @Override
    public ContractDTO getContractList(int page, int per_page, String sortBy, boolean descending, String search, String startDate, String endDate) {
        ContractDTO returnDTO = new ContractDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        if (!TextUtils.isEmpty(search.trim())) {
            params.put("search", search);
        }
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        Long total = contractDao.getContractCount(params);
        List<ContractData> contracts = contractDao.getContractData(params);
        for(ContractData contract :contracts){
            if(!TextUtils.isEmpty(contract.getContractAddTime())) {
                contract.setContractAddTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(contract.getContractAddTime())));
            }
        }
        returnDTO.setContractDataPagination(PaginationUtil.paginate(page, per_page, total, contracts));
        return returnDTO;
    }

    @Override
    public ContractDTO deleteContract(String contractNo) {
        ContractDTO returnDTO = new ContractDTO();
        if(contractDao.getContractByNo(contractNo) == null){
            returnDTO.setError("查无此合同");
            return returnDTO;
        }
        contractDao.deleteContractByNo(contractNo);
        contractDao.deleteContractFile(contractNo);
        returnDTO.setSuccess("删除成功");
        return returnDTO;
    }

    @Override
    public ContractDTO getContractInfo(String contractNo) {
        ContractDTO returnDTO = new ContractDTO();
        if(contractDao.getContractByNo(contractNo) == null){
            returnDTO.setError("查无此合同");
            return returnDTO;
        }
        returnDTO.setContractData(contractDao.getContractByNo(contractNo));
        returnDTO.getContractData().setContractAddTime(new SimpleDateFormat("yyyy-MM-dd").format(TextUtils.stringToTimeStamp(returnDTO.getContractData().getContractAddTime())));
        return returnDTO;
    }

    @Override
    public ContractDTO updateContract(ContractData contractData) {
        ContractDTO returnDTO = new ContractDTO();
        if(contractDao.getContractByNo(contractData.getContractNo()) == null){
            returnDTO.setError("查无此合同");
            return returnDTO;
        }
        Map<String,Object> params = new HashMap<>();
        params.put("contractNo",contractData.getContractNo());
        params.put("contractName",contractData.getContractName());
        params.put("contractAddTime",contractData.getContractAddTime());
        params.put("contractAuthorize",contractData.getContractAuthorize());
        params.put("contractNote",contractData.getContractNote());
        params.put("typeId",contractData.getTypeId());
        params.put("contractBusiness",contractData.getContractBusiness());
        params.put("contractUserName",contractData.getContractUserName());
        params.put("contractUserPhone",contractData.getContractUserPhone());
        params.put("contractMoney",contractData.getContractMoney());
        params.put("projectType",contractData.getProjectType());
        contractDao.updateContract(params);
        String projectNo = projectDao.getProjectNoByContractNo(contractData.getContractNo());
        if(!TextUtils.isEmpty(projectNo)){
            Project project = new Project();
            project.setProjectNo(projectNo);
            project.setProjectAuthorize(contractData.getContractAuthorize());
            project.setProjectName(contractData.getContractName());
            project.setProjectMoney(contractData.getContractMoney());
            project.setProjectNote(contractData.getContractNote());
            project.setProjectType(contractData.getProjectType());
            projectDao.updateProject(project);
        }
        contractDao.updateUser(params);
        returnDTO.setSuccess("更新成功");
        return returnDTO;
    }

    @Override
    public ContractDTO addProjectToContract(ContractData contractData) {
        ContractDTO returnDTO = new ContractDTO();
        if(contractDao.getContractByNo(contractData.getContractNo()) == null){
            returnDTO.setError("查无此合同");
            return returnDTO;
        }
        contractDao.deleteProjectContract(contractData.getProjectNo());
        contractDao.addProjectToContract(contractData);
        returnDTO.setContractData(contractDao.getContractByNo(contractData.getContractNo()));
        return returnDTO;
    }

    @Override
    public ContractDTO updateProjectToContract(ContractData contractData) {
        ContractDTO returnDTO = new ContractDTO();
        if(contractDao.getContractByNo(contractData.getContractNo()) == null){
            returnDTO.setError("查无此合同");
            return returnDTO;
        }
        contractDao.updateProjectContract(contractData);
        returnDTO.setContractData(contractDao.getContractByNo(contractData.getContractNo()));
        return returnDTO;
    }

    @Override
    public ContractDTO getContractUnselected() {
        ContractDTO returnDTO = new ContractDTO();
        returnDTO.setDataList(contractDao.getContractUnselected());
        return returnDTO;
    }

    @Override
    public List<String> getContractNumList(String ymstr) {
        ContractDTO returnDTO = new ContractDTO();
        List<String> contractNumList = contractDao.getContractNumList(ymstr);
        return contractNumList;
    }

    @Override
    public void deleteContractUserByNo(String contractNo)
    {
        contractDao.deleteContractUserByNo(contractNo);
    }
}
