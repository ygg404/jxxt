package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.ChartDataDao;
import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.ProjectDao;
import cn.ux.jxxt.dao.WorkGroupDao;
import cn.ux.jxxt.domain.*;
import cn.ux.jxxt.domain.custom.OutPutWrap;
import cn.ux.jxxt.dto.ChartDataDTO;
import cn.ux.jxxt.dto.OutPutDTO;
import cn.ux.jxxt.dto.ProjectDTO;
import cn.ux.jxxt.service.ChartDataService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChartDataServiceImpl implements ChartDataService {

    @Autowired
    private ChartDataDao chartDataDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private WorkGroupDao workGroupDao;

    //收费统计
    @Override
    public ChartDataDTO getChartData(String startDate, String endDate, String project_charge){
        ChartDataDTO returnDTO = new ChartDataDTO();
        Map<String, Object> params = new HashMap<>();
        if(!TextUtils.isEmpty(startDate.trim())){
            params.put("startDate",startDate);
        }
        if(!TextUtils.isEmpty(endDate.trim())){
            params.put("endDate",endDate);
        }
        if(!"全部".equals(project_charge) && !TextUtils.isEmpty(project_charge.trim())){
            params.put("project_charge",project_charge);
        }
        List<ChartData> charts = chartDataDao.getCharDatas(params);
        List<ChartData> chartDatas = new ArrayList<>();
        for(ChartData chartData : charts){
            Float totalMoney = projectDao.getProjectMoney(chartData.getProjectNo());       //总金额
            if(totalMoney == null){
                totalMoney = 0.0f;
            }
            ProjectUser projectUser = projectDao.getProjectUserByNo(chartData.getProjectNo());
            Float getMoney = 0f;        //已收
            Float expenditure = 0f;     //支出
            Float projectOutPut = 0f;   //总产值
            for(ProjectAccount projectAccount : projectDao.getProjectAccountData(chartData.getProjectNo())){
                if(projectAccount.getAccountType() == 0){           //0为收款数据，1为支出数据
                    getMoney += projectAccount.getAccountNum();
                }else{
                    expenditure += projectAccount.getAccountNum();
                }
            }
            float output = 0f; //产值
            List<OutPutWrap> outPutWraps = projectDao.getOutPut(chartData.getProjectNo());
            if(outPutWraps.size() > 0){
                //添加产值数据
                for(OutPutWrap outPutWrap : outPutWraps){
                    if(outPutWrap != null) {
                        output = outPutWrap.getWorkLoad() * outPutWrap.getProjectRatio();
                        projectOutPut += output;
                    }
                }
            }
            ChartData data = new ChartData();
            data.setProjectNo(chartData.getProjectNo());
            data.setProjectName(chartData.getProjectName());
            data.setProjectAuthorize(chartData.getProjectAuthorize());
            data.setUserName(chartData.getUserName());
            data.setUserPhone(chartData.getUserPhone());
            data.setProjectReceivable(totalMoney);
            data.setProjectNotReceipts(totalMoney - getMoney);
            data.setProjectActuallyReceipts(getMoney);
            data.setProjectCharge(chartData.getProjectCharge());
            chartDatas.add(data);

        }
        returnDTO.setChartDataList(chartDatas);
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "公司业务收费统计表"));
        return returnDTO;
    }

    @Override
    public OutPutDTO getOutPut(String startDate, String endDate) {
        OutPutDTO returnDTO = new OutPutDTO();
        Map<String, Object> params = new HashMap<>();
        if (!TextUtils.isEmpty(startDate.trim())) {
            params.put("startDate", startDate);
        }
        if (!TextUtils.isEmpty(endDate.trim())) {
            params.put("endDate", endDate);
        }
        List<WorkGroup> workGroup = workGroupDao.queryGroup();
        returnDTO.setWorkGroupList(workGroup);
        List<WorkGroup> list = new ArrayList<>();
        for(WorkGroup w : workGroup) {
            int project_sum = 0;  //项目数
            Float OutPut_num = 0f; //产值
            params.put("id",w.getId());
            List<PlanRate> rateList = chartDataDao.getProjectSum(params);
            project_sum = rateList.size();
            for(PlanRate p : rateList){
                if(p.getActuallyOutput() > 0 ){
                    OutPut_num += p.getActuallyOutput();
                }else{
                    OutPut_num += p.getProject_output();
                }
            }
            WorkGroup wg = new WorkGroup();
            wg.setgName(w.getgName());
            wg.setProjectSum(project_sum);
            wg.setOutPutNum(OutPut_num);
            list.add(wg);
        }
        returnDTO.setWorkGroupList(list);
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "各部门汇总产值统计表"));
        return returnDTO;
    }

    //根据时间类型获取各部门汇总产值
    @Override
    public OutPutDTO getOutPut2(String startDate, String endDate, int workId) {
        OutPutDTO returnDTO = new OutPutDTO();
        Map<String, Object> params = new HashMap<>();
        if (!TextUtils.isEmpty(startDate.trim())) {
            params.put("startDate", startDate);
        }
        if (!TextUtils.isEmpty(endDate.trim())) {
            params.put("endDate", endDate);
        }
        List<WorkGroup> workGroup;
        if (workId > 0) {
            //查询id为workId的工作组得到组名
            workGroup = chartDataDao.getGroup(workId);
        } else {
            //查询所有的工作组得到组名
            workGroup = workGroupDao.queryGroup();
        }
        returnDTO.setWorkGroupList(workGroup);
        List<WorkGroup> list = new ArrayList<>();
        for(WorkGroup w : workGroup) {
            int ProjectSum = 0;  //项目数
            Float OutPutNum = 0f; //产值
            Float Sums = 0f;    //每个项目的产值
            params.put("id",w.getId());
            //查询指定工作组的所有项目
            List<PlanRate> rateList = chartDataDao.getProjectSum(params);
            ProjectSum = rateList.size();

            List<PlanRate> planRateList = new ArrayList<>();
            if(ProjectSum > 0){
                for(PlanRate p : rateList){
                    //得出该项目小组获得的产值
                    if(p.getActuallyOutput() > 0 ) {
                        OutPutNum = p.getActuallyOutput();
                    }else{
                        OutPutNum = p.getProject_output();
                    }
                    Sums += OutPutNum;
                }
                //查询指定的小组在某时间段的项目
                List<PlanRate> list2 = chartDataDao.getProjects(params);
                Float Output_num = 0f; //产值
                Float sum = 0f;
                for (PlanRate prl : list2) {
                    //得出该项目小组获得的产值
                    if(prl.getActuallyOutput() > 0 ) {
                        Output_num = prl.getActuallyOutput();
                    }else{
                        Output_num = prl.getProject_output();
                    }
                    PlanRate pr = new PlanRate();
                    pr.setId(prl.getId());
                    pr.setProjectName(prl.getProjectName());
                    pr.setProjectType(prl.getProjectType());
                    pr.setFinishDateTime(prl.getFinishDateTime());
                    pr.setOutputNum(Output_num);
                    planRateList.add(pr);
                }
            }

            WorkGroup wg = new WorkGroup();
            wg.setgName(w.getgName());
            wg.setProjectSum(ProjectSum);
            wg.setOutPutNum(Sums);
            wg.setPlanRateList(planRateList);
            list.add(wg);
        }
        returnDTO.setWorkGroupList(list);
        return returnDTO;
    }

    //根据时间类型获取各部门质量统计数据
    @Override
    public OutPutDTO getQualityChart(String startDate, String endDate, int workId){
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(0);
        OutPutDTO returnDTO = new OutPutDTO();
        Map<String, Object> params = new HashMap<>();
        if (!TextUtils.isEmpty(startDate.trim())) {
            params.put("startDate", startDate);
        }
        if (!TextUtils.isEmpty(endDate.trim())) {
            params.put("endDate", endDate);
        }
        List<WorkGroup> workGroup;
        if(workId > 0){
            workGroup = chartDataDao.getGroup(workId);
        }
        else{
            workGroup = workGroupDao.queryGroup();
        }
        returnDTO.setWorkGroupList(workGroup);
        List<WorkGroup> list = new ArrayList<>();
        for(WorkGroup w : workGroup) {
            int project_sum = 0;  //项目数
            int excellent=0;           //优
            int good=0;                //良
            int qualified=0;           //合格
            String Excellent_rate = "0%";      //优良率
            int Excellent_number = 0;       //优良项目数
            params.put("id",w.getId());
            List<PlanRate> rateList = chartDataDao.getProjectQuality(params);
            project_sum = rateList.size();
            if(project_sum > 0){
                for(PlanRate p : rateList){
                    if(p.getQuality_score()!=null){
                        if(p.getQuality_score() >= 90){
                            excellent ++;
                        }else if(p.getQuality_score() >=75 && p.getQuality_score()<90){
                            good ++;
                        }else if(p.getQuality_score() >=60 && p.getQuality_score()<75){
                            qualified++;
                        }
                    }

                }
                Excellent_number = excellent+good;
                Excellent_rate = nf.format(Excellent_number / project_sum);
            }
            List<WorkGroup> list2 = new ArrayList<>();
            if(project_sum > 0) {
                List<PlanRate> rateList2 = chartDataDao.getQuality(params);
                for (PlanRate p : rateList2) {
                    String qualityLevel = "";    //质量等级
                    if(p.getQuality_score() != null){
                        if (p.getQuality_score() >= 90) {
                            qualityLevel = "优";
                        } else if (p.getQuality_score() >= 75 && p.getQuality_score() < 90) {
                            qualityLevel = "良";
                        } else if (p.getQuality_score() >= 60 && p.getQuality_score() < 75) {
                            qualityLevel = "合格";
                        } else {
                            qualityLevel = "不合格";
                        }
                    }
                    WorkGroup w2 = new WorkGroup();
                    if(p.getqUserAccount() != null){
                        w2.setqUserName(chartDataDao.getUserName(p.getqUserAccount()));
                    }

                    if(p.getcUserAccount() != null){
                        w2.setcUserName(chartDataDao.getUserName(p.getcUserAccount()));
                    }
                    w2.setProjectName(p.getProjectName());
                    w2.setQuality_score(p.getQuality_score());
                    w2.setQuality_level(qualityLevel);
                    w2.setUserName(p.getUserName());
                    list2.add(w2);
                }

            }

            WorkGroup wg = new WorkGroup();
            wg.setgName(w.getgName());
            wg.setProjectSum(project_sum);
            wg.setExcellent(excellent);
            wg.setGood(good);
            wg.setQualified(qualified);
            wg.setExcellent_number(Excellent_number);
            wg.setExcellent_rate(Excellent_rate);
            wg.setWorkGroupList(list2);
            list.add(wg);
        }
        returnDTO.setWorkGroupList(list);
        return returnDTO;
    }

    //获取所有业务员
    public ProjectDTO getprojectCharge(){
        ProjectDTO returnDTO = new ProjectDTO();
        List<String> projectList = projectDao.getBusinessName();

        List<Project> ps = new ArrayList<>();
        for(int i = 0 ; i < projectList.size(); i ++){
            Project p1 = new Project();
            p1.setProjectCharge(projectList.get(i));
            ps.add(p1);
        }
        returnDTO.setProjectList(ps);
        return returnDTO;
    }
}
