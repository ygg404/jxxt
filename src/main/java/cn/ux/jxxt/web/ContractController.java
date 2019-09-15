package cn.ux.jxxt.web;

import cn.ux.jxxt.domain.ContractData;
import cn.ux.jxxt.dto.ContractDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.ProjectContractService;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
public class ContractController {

    @Autowired
    private ProjectContractService contractService;

    @Autowired
    private GeneralMessage message;

    /**
     * 添加合同
     * @param contractData
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/contract/")
    public ResponseEntity<?> addContract(@Valid @RequestBody ContractData contractData, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加合同失败，请重新添加", bindingResult);
        }
        ContractDTO returnDTO = contractService.addContract(contractData);
        if (returnDTO.getError() != null) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 获取合同数据，分页
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/contract/", params = {"page", "rowsPerPage", "sortBy", "descending","search","startDate","endDate"})
    public ResponseEntity<?> getContract(@RequestParam("page") int page,
                                         @RequestParam("rowsPerPage") int per_page,
                                         @RequestParam("sortBy") String sortBy,
                                         @RequestParam("descending") boolean descending,
                                         @RequestParam("search") String search,
                                         @RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate) {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        ContractDTO returnDTO = contractService.getContractList(page, per_page, sortBy, descending, search,start,end);
        return ResponseEntity.ok(returnDTO.getContractDataPagination());
    }

    /**
     * 删除合同
     * @param contractNo
     * @return
     */
    @DeleteMapping(value = "/contract/", params = {"contractNo"})
    public ResponseEntity<?> deleteContract(@RequestParam("contractNo") String contractNo) {
        ContractDTO returnDTO = contractService.deleteContract(contractNo);
        contractService.deleteContractUserByNo(contractNo);
        if (!TextUtils.isEmpty(returnDTO.getError())) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 更新合同数据
     * @param contractData
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/contract/update/")
    public ResponseEntity<?> updateContract(@Valid @RequestBody ContractData contractData, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新合同失败，请重新添加", bindingResult);
        }
        ContractDTO returnDTO = contractService.updateContract(contractData);
        if (returnDTO.getError() != null) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 绑定项目与合同
     * @param contractData
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/contract/project/")
    public ResponseEntity<?> addProjectToContract(@Valid @RequestBody ContractData contractData,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("添加失败",bindingResult);
        }
        ContractDTO returnDTO = contractService.addProjectToContract(contractData);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.ok(returnDTO.getContractData());
        }
    }

    /**
     * 更新合同数据
     * @param contractData
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/contract/project/update/")
    public ResponseEntity<?> updateProjectToContract(@Valid @RequestBody ContractData contractData,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("更新失败",bindingResult);
        }
        ContractDTO returnDTO = contractService.updateProjectToContract(contractData);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.ok(returnDTO.getContractData());
        }
    }

    /**
     * 获取未被选择的合同
     * @return
     */
    @GetMapping(value = "/contract/")
    public ResponseEntity<?> getContractNoChoose(){
        ContractDTO returnDTO = contractService.getContractUnselected();
        return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getDataList());
    }

    //根据合同编号获取对应数据
    @GetMapping(value = "/contract/info/",params = {"contractNo"})
    public ResponseEntity<?> getContractInfo(@RequestParam("contractNo") String contractNo){
        ContractDTO returnDTO = contractService.getContractInfo(contractNo);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getContractData());
    }

    /**
     * 获取合同编号最大数
     * @return
     */
    @GetMapping(value = "/contract/getContractNumMax")
    public ResponseEntity<?> getContractNumMax() {
        int numMax = 0;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int month= cal.get(Calendar.MONTH)  + 1 ;//获取月份
        String ymstr = Integer.toString(year) + String.format("%02d", month);
        List<String> contractnumlist = contractService.getContractNumList(ymstr);
        for (String num: contractnumlist
        ) {
            num = num.replace(ymstr , "");
            if( Integer.parseInt(num) > numMax){
                numMax = Integer.parseInt(num);
            }
        }
        //ContractDTO returnDTO = contractService.getContractList(page, per_page, sortBy, descending, search,start,end);
        return ResponseEntity.ok( ymstr + String.format("%02d", numMax + 1));
    }
}
