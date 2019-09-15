package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.UserDao;
import cn.ux.jxxt.dao.WorkGroupDao;
import cn.ux.jxxt.domain.UserGroup;
import cn.ux.jxxt.domain.WorkGroup;
import cn.ux.jxxt.domain.custom.UserWrap;
import cn.ux.jxxt.dto.WorkGroupDTO;
import cn.ux.jxxt.service.WorkGroupService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.PaginationUtil;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WorkGroupServiceImpl implements WorkGroupService {

    @Autowired
    private WorkGroupDao workGroupDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LogDao logDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 添加作业组
     * @param workGroupDTO
     * @return
     */
    @Override
    public WorkGroupDTO addGroup(WorkGroupDTO workGroupDTO) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        //先查询该作业组是否存在
        WorkGroup workGroup = workGroupDao.queryGroupByName(workGroupDTO.getgName());
        if (workGroup != null) {
            returnDTO.setError("作业组已存在");
        } else {
            workGroupDTO.setCreateTime(Timestamp.valueOf(sdf.format(new Date())));      //设置创建时间
            for(UserWrap user : userDao.getUser()){
                if(user.getUserName().equals(workGroupDTO.getHeadMan())){
                    workGroupDTO.setHeadId(user.getId());
                }
                if(user.getUserName().equals(workGroupDTO.getDeputyLeader())){
                    workGroupDTO.setDeputyId(user.getId());
                }
            }
            WorkGroup maxGroup = workGroupDao.getGroupByMax();
            workGroupDTO.setfId(maxGroup.getfId() + 1);
            workGroupDao.insertGroup(workGroupDTO);                                     //新增作业组数据
            returnDTO.setSuccess("增加作业组成功");
            returnDTO.setWorkGroup(workGroupDao.queryGroupByName(workGroupDTO.getgName()));         //返回执行成功的作业组数据
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "添加作业组 : " + workGroupDTO.getgName()));
        return returnDTO;
    }

    @Override
    public WorkGroupDTO upItem(WorkGroupDTO workGroup){
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        WorkGroup wg = workGroupDao.getGroupById(workGroup.getId());
        WorkGroup maxGroup = workGroupDao.getGroupByMax();
        WorkGroup minGroup = workGroupDao.getGroupByMin();
        if(wg.getfId() == minGroup.getfId()){
            long wgid = wg.getfId();
            long maxId = maxGroup.getfId();
            wg.setfId(maxId);
            maxGroup.setfId(wgid);
            workGroupDao.updateGroupFid(wg);
            workGroupDao.updateGroupFid(maxGroup);
        }else{
            WorkGroup top = workGroupDao.getTopGroupById(workGroup.getfId());            //获取当前的上一条数据
            long wFid = wg.getfId();
            long tFid = top.getfId();
            top.setfId(wFid);
            wg.setfId(tFid);
            workGroupDao.updateGroupFid(wg);
            workGroupDao.updateGroupFid(top);
        }
        returnDTO.setSuccess("替换成功");
        return returnDTO;
    }


    /**
     * 获取所有作业组
     * @return
     */
    @Override
    public WorkGroupDTO getGroup() {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        returnDTO.setWorkGroups(workGroupDao.queryGroup());
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看作业组"));
        return returnDTO;
    }

    /**
     * 获取所有作业组(分页)
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @param search
     * @return
     */
    @Override
    public WorkGroupDTO getGroupByPaginated(int page, int per_page, String sortBy, boolean descending, String search) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("per_page",per_page);
        paramsMap.put("offset",(page-1) * per_page);
        paramsMap.put("sortBy",sortBy);
        paramsMap.put("desc",descending);
        if(search != null && !search.equals("")){
            paramsMap.put("search",search);
        }
        Long total = workGroupDao.getGroupsNumber(paramsMap);
        List<WorkGroup> groups = workGroupDao.queryGroupsPaginated(paramsMap);
        //修改输出的日期格式为"yyyy-MM-dd HH:mm:ss"
        for(WorkGroup group : groups){
            group.setTimeData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(group.getCreateTime()));
        }
        returnDTO.setGroupPagination(PaginationUtil.paginate(page,per_page,total,groups));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看作业组"));
        return returnDTO;
    }

    /**
     * 根据指定id删除作业组
     * @param groupId
     * @return
     */
    @Override
    public WorkGroupDTO deleteGroup(Long groupId) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        WorkGroup workGroup = workGroupDao.queryGroupById(groupId);
        if (workGroup == null) {
            returnDTO.setError("查询不到id为 " + groupId + "的作业组。");
        } else {
            workGroupDao.deleteGroup(groupId);
            returnDTO.setSuccess("成功删除作业组");
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "删除作业组"));
        return returnDTO;
    }

    /**
     * 更新作业组
     * @param workGroupDTO
     * @return
     */
    @Override
    public WorkGroupDTO updateGroup(Long groupId, WorkGroupDTO workGroupDTO) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        if (workGroupDao.queryGroupById(groupId) == null) {        //根据作业组id查询是否存在该作业组
            returnDTO.setError("该作业组不存在");
        } else {
            workGroupDTO.setId(groupId);
            for(UserWrap user : userDao.getUser()){
                if(user.getUserName().equals(workGroupDTO.getHeadMan())){
                    workGroupDTO.setHeadId(user.getId());
                }
                if(user.getUserName().equals(workGroupDTO.getDeputyLeader())){
                    workGroupDTO.setDeputyId(user.getId());
                }
            }
            workGroupDao.updateGroup(workGroupDTO);
            Map<String, Object> groupMap = new HashMap<>();
            groupMap.put("userId", workGroupDTO.getHeadId());
            groupMap.put("groupId", groupId);
            if(workGroupDao.getUserGroupById(workGroupDTO.getHeadId()) !=null) {
                workGroupDao.deleteUserGroup(workGroupDTO.getHeadId());
            }
            userDao.addUserGroup(groupMap);
            returnDTO.setWorkGroup(workGroupDao.queryGroupById(groupId));
            returnDTO.setSuccess("更新成功");
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "更新作业组"));
        return returnDTO;
    }

    /**
     * 根据作业组id查询作业组
     * @param groupId
     * @return
     */
    @Override
    public WorkGroupDTO getGroupById(Long groupId) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        WorkGroup workGroup = workGroupDao.queryGroupById(groupId);
        if (workGroup == null) {    //先判断是否有这条数据
            returnDTO.setError("查询不到id为" + groupId + "的作业组。");
        } else {
            returnDTO.setWorkGroup(workGroup);
        }
        return returnDTO;
    }

    /**
     * 根据作业组名查询作业组
     * @param groupName
     * @return
     */
    @Override
    public WorkGroupDTO getGroupByName(String groupName) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        WorkGroup workGroup = workGroupDao.queryGroupByName(groupName);
        if(workGroup == null){          //先判断是否有这条数据
            returnDTO.setError("查询不到名称为"+groupName+"的作业组。");
        }else{
            returnDTO.setWorkGroup(workGroup);
        }
        return returnDTO;
    }

    @Override
    public List<String> getWorkName(String projectNo) {
        List<String> list = workGroupDao.getWorkName(projectNo);
        return list;
    }

    @Override
    public WorkGroupDTO addGroupInfo(UserGroup userGroup) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        if(workGroupDao.getGroupByName(userGroup.getName()) != null){
            returnDTO.setError("该作业组已存在，请勿重复添加");
            return returnDTO;
        }
        workGroupDao.addGroupInfo(userGroup);
        returnDTO.setSuccess("添加成功");
        return returnDTO;
    }

    @Override
    public WorkGroupDTO deleteGroupInfo(long groupId) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        workGroupDao.deleteGroupById(groupId);               //删除部门
        workGroupDao.deleteGroupUserById(groupId);          //删除从属用户
        workGroupDao.deleteGroupByFid(groupId);             //删除从属部门
        returnDTO.setSuccess("删除成功");
        return returnDTO;
    }

    @Override
    public WorkGroupDTO selectGroup(String userAccount) {
        WorkGroupDTO returnDTO = new WorkGroupDTO();
        List<WorkGroup> list = workGroupDao.selectGroup();
        Long id = workGroupDao.selectUserId(userAccount);
        WorkGroup wg = new WorkGroup();
        for(WorkGroup w : list){
            if(w.getHeadId() == id){
                wg.setId(w.getId());
            }
        }
        returnDTO.setWorkGroup(wg);
        return returnDTO;
    }

}
