package cn.ux.jxxt.web;

import cn.ux.jxxt.dto.*;
import cn.ux.jxxt.service.ChartDataService;
import cn.ux.jxxt.service.ProjectService;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ChartDataController {

    @Autowired
    private ChartDataService chartDataService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private GeneralMessage message;

    /**
     * 获取收费统计数据
     * @return
     */
    @GetMapping(value = "/toll/",params = {"startDate","endDate","project_charge"})
    public ResponseEntity<?> getToll(@RequestParam("startDate") String startDate,
                                     @RequestParam("endDate") String endDate,
                                     @RequestParam("project_charge") String project_charge) {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        ChartDataDTO returnDTO = chartDataService.getChartData(start,end,project_charge);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getChartDataList());
        }
    }

    /**
     * 获取业务员统计汇总表数据
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/project/business/",params = {"startDate","endDate"})
    public ResponseEntity<?> getBusinessChart(@RequestParam("startDate") String startDate,
                                              @RequestParam("endDate") String endDate){
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        ProjectDTO returnDTO = projectService.getProjectBusiness(start,end);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectCart());
    }

    /**
     * 获取产值统计表数据
     * @return
     */
    @GetMapping(value = "/outputs/",params = {"startDate","endDate"})
    public ResponseEntity<?> getOutPuts(@RequestParam("startDate") String startDate,
                                        @RequestParam("endDate") String endDate){
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        OutPutDTO returnDTO = chartDataService.getOutPut(start,end);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getWorkGroupList());
        }
    }


    /**
     * 根据时间类型获取各部门汇总产值
     * @return
     */
    @GetMapping(value = "/outputcharts/",params = {"startDate","endDate","workId"})
    public ResponseEntity<?> getOutPut(@RequestParam("startDate") String startDate,
                                       @RequestParam("endDate") String endDate,
                                       @RequestParam("workId") int workId){
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        OutPutDTO returnDTO = chartDataService.getOutPut2(start,end,workId);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getWorkGroupList());
        }
    }

    /**
     * 根据时间类型获取各部门质量统计数据
     * @return
     */
    @GetMapping(value = "/quality_chart/",params = {"startDate","endDate","workId"})
    public ResponseEntity<?> getQualityChart(@RequestParam("startDate") String startDate,
                                             @RequestParam("endDate") String endDate,
                                             @RequestParam("workId") int workId){
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        OutPutDTO returnDTO = chartDataService.getQualityChart(start,end,workId);
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getWorkGroupList());
        }
    }

    /*
     * 获取所有业务员
     */
    @GetMapping(value = "/projectCharge/")
    public ResponseEntity getprojectCharge(){
        ProjectDTO returnDTO = chartDataService.getprojectCharge();
        if(!TextUtils.isEmpty(returnDTO.getError())){
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getProjectList());
        }
    }
}
