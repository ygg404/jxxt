package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.ContractData;

import java.util.List;
import java.util.Map;

public interface ContractDao {
    /**
     * 添加合同
     * @param contractData
     * @return
     */
    public int addContract(ContractData contractData);

    /**
     * 添加合同没有添加时间
     * @param contractData
     * @return
     */
    public int addContractNoAddTime(ContractData contractData);

    /**
     * 获取合同数据
     * @param params
     * @return
     */
    public List<ContractData> getContractData(Map<String, Object> params);

    /**
     * 根据合同编码查找合同
     * @param contractNo
     * @return
     */
    public ContractData getContractByNo(String contractNo);

    /**
     * 根据合同编码删除合同
     * @param contractNo
     * @return
     */
    public int deleteContractByNo(String contractNo);

    /**
     * 获取合同总数
     * @param params
     * @return
     */
    public Long getContractCount(Map<String, Object> params);

    /**
     * 更新合同数据
     * @param params
     * @return
     */
    public int updateContract(Map<String,Object> params);

    /**
     * 添加联系人信息
     * @param contractData
     * @return
     */
    public int addContractUser(ContractData contractData);

    /**
     * 根据合同编号删除联系人
     * @param contractNo
     */
    public void deleteContractUserByNo(String contractNo);

    /**
     * 更新联系人信息
     * @param params
     * @return
     */
    public int updateUser(Map<String,Object> params);

    /**
     * 添加项目与合同关系
     * @param contractData
     * @return
     */
    public int addProjectToContract(ContractData contractData);

    /**
     * 更新项目与合同关系
     * @param contractData
     * @return
     */
    public int updateProjectContract(ContractData contractData);

    /**
     * 根据项目编号解除合同关系
     * @param projectNo
     * @return
     */
    public int deleteProjectContract(String projectNo);

    /**
     * 查找当月未被选择的合同
     * @return
     */
    public List<ContractData> getContractUnselected();

    /**
     * 删除合同文件
     * @param contractNo
     * @return
     */
    public int deleteContractFile (String contractNo);

    public int updateContractNo(Map<String,Object> params);
    public long getLastId();

    /**
     * 获取项目合同列表
     * @return
     */
    public List<String> getContractNumList(String ymstr);
}
