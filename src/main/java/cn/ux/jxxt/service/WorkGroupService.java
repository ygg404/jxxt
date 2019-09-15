package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.UserGroup;
import cn.ux.jxxt.dto.WorkGroupDTO;

import java.util.List;

public interface WorkGroupService {

    WorkGroupDTO addGroup(WorkGroupDTO workGroup);

    WorkGroupDTO upItem(WorkGroupDTO workGroup);

    WorkGroupDTO getGroup();

    WorkGroupDTO getGroupByPaginated(int page, int per_page, String sortBy, boolean descending, String search);

    WorkGroupDTO deleteGroup(Long groupId);

    WorkGroupDTO updateGroup(Long groupId, WorkGroupDTO workGroup);

    WorkGroupDTO getGroupById(Long groupId);

    WorkGroupDTO getGroupByName(String groupName);

    List<String> getWorkName(String projectNo);

    WorkGroupDTO addGroupInfo(UserGroup userGroup);

    WorkGroupDTO deleteGroupInfo(long groupId);

    WorkGroupDTO selectGroup(String userAccount);
}
