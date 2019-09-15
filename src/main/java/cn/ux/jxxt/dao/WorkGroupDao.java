package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.UserGroup;
import cn.ux.jxxt.domain.WorkGroup;
import cn.ux.jxxt.dto.WorkGroupDTO;

import java.util.List;
import java.util.Map;

public interface WorkGroupDao {

    /**
     * 查询所有作业组
     * @return
     */
    public List<WorkGroup> queryGroup();

    /**
     * 新增一条作业组数据
     * @param workGroup
     * @return
     */
    public int insertGroup(WorkGroupDTO workGroup);

    /**
     * 根据id删除指定组数据
     * @param groupId
     * @return
     */
    public int deleteGroup(Long groupId);

    /**
     * 根据id更新指定组数据
     * @param workGroup
     * @return
     */
    public int updateGroup(WorkGroupDTO workGroup);

    /**
     * 根据id查询指定组数据
     * @param groupId
     * @return
     */
    public WorkGroup queryGroupById(Long groupId);

    /**
     * 根据作业组名称查询组数据
     * @param groupName
     * @return
     */
    public WorkGroup queryGroupByName(String groupName);


    public Long getGroupsNumber(Map<String, Object> paramsMap);

    /**
     * 获取作业组分页数据
     * @param paramsMap
     * @return
     */
    public List<WorkGroup> queryGroupsPaginated(Map<String, Object> paramsMap);

    public List<String> getWorkName(String projectNo);

    /**
     * 根据名称获取部门
     * @param name
     * @return
     */
    public UserGroup getGroupByName(String name);

    /**
     * 根据id获取部门
     * @param id
     * @return
     */
    public WorkGroup getGroupById(long id);

    /**
     * 获取从属部门
     * @param fId
     * @return
     */
    public List<UserGroup> getGroupByFid(long fId);

    /**
     * 添加部门
     * @param userGroup
     * @return
     */
    public int addGroupInfo(UserGroup userGroup);

    /**
     * 更新部门数据
     * @param userGroup
     * @return
     */
    public int updateGroupById(UserGroup userGroup);

    /**
     * 获取部门下的用户
     * @param id
     * @return
     */
    public List<UserGroup> getGroupUserById(long id);

    /**
     * 删除部门数据
     * @param id
     * @return
     */
    public int deleteGroupById(long id);

    /**
     * 删除从属部门数据
     * @param id
     * @return
     */
    public int deleteGroupByFid(long id);

    /**
     * 根据id删除部门下的用户
     * @param id
     * @return
     */
    public int deleteGroupUserById(long id);


    //查询用户所属得那个工作组的id
    public List<WorkGroup> selectGroup();

    public long selectUserId(String userAccount);

    public WorkGroup getUserGroupById(long userId);

    public WorkGroup getGroupByMin();

    public WorkGroup getGroupByMax();

    public int updateGroupFid(WorkGroup workGroup);

    public WorkGroup getTopGroupById(long f_id);

    public int deleteUserGroup(long userId);
}
