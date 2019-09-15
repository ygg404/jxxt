package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.ShortCutDao;
import cn.ux.jxxt.domain.ProjectShortCut;
import cn.ux.jxxt.dto.ShortCutDTO;
import cn.ux.jxxt.service.ShortCutService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.PaginationUtil;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShortCutServiceImpl implements ShortCutService {

    @Autowired
    private ShortCutDao shortCutDao;

    @Autowired
    private LogDao logDao;

    @Override
    public ShortCutDTO getAllShortByPaginated(int page, int per_page, String sortBy, boolean descending) {
        ShortCutDTO returnDTO = new ShortCutDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", per_page);
        params.put("offset", (page - 1) * per_page);
        params.put("sortBy", sortBy);
        params.put("desc", descending);
        Long total = shortCutDao.getShortNum(params);
        List<ProjectShortCut> shorts = shortCutDao.getShortByPagination(params);
        returnDTO.setShortCutPagination(PaginationUtil.paginate(page, per_page, total, shorts));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看快捷输入语"));
        return returnDTO;
    }

    @Override
    public ShortCutDTO getAllShortType() {
        ShortCutDTO returnDTO = new ShortCutDTO();
        returnDTO.setTypeList(shortCutDao.getShortType());
        return returnDTO;
    }

    @Override
    public ShortCutDTO getShortList(Long typeId) {
        ShortCutDTO returnDTO = new ShortCutDTO();
        returnDTO.setProjectList(shortCutDao.getShortList(typeId));
        return returnDTO;
    }

    @Override
    public ShortCutDTO getAllShort() {
        ShortCutDTO returnDTO = new ShortCutDTO();
        returnDTO.setProjectList(shortCutDao.queryAllShort());
        return returnDTO;
    }

    @Override
    public ShortCutDTO insertShort(ProjectShortCut projectShortCut) {
        ShortCutDTO returnDTO = new ShortCutDTO();
        projectShortCut.setTypeId(shortCutDao.getTypeId(projectShortCut.getShortName()));
        shortCutDao.insertShort(projectShortCut);
        returnDTO.setSuccess("新增成功");
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "增加快捷输入语，内容为:" + projectShortCut.getShortNote()));
        return returnDTO;
    }

    @Override
    public ShortCutDTO updateShort(Long cutId, ProjectShortCut projectShortCut) {
        ShortCutDTO returnDTO = new ShortCutDTO();
        ProjectShortCut iShortCut = shortCutDao.queryById(cutId);
        //ProjectShortCut nShortCut = shortCutDao.queryByName(projectShortCut.getShortName());
        if (iShortCut == null) {
            returnDTO.setError("查无id号为------" + cutId + "------的快捷输入");
            return returnDTO;
        }
        projectShortCut.setId(cutId);
        shortCutDao.updateShort(projectShortCut);
        returnDTO.setProjectShortCut(shortCutDao.queryById(cutId));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "更新快捷输入语"));
        return returnDTO;
    }

    @Override
    public ShortCutDTO deleteShort(Long cutId) {
        ShortCutDTO returnDTO = new ShortCutDTO();
        if (shortCutDao.queryById(cutId) == null) {
            returnDTO.setError("查无id号为------" + cutId + "------的快捷输入");
        } else {
            shortCutDao.deleteShort(cutId);
            returnDTO.setSuccess("删除成功");
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "删除快捷输入语"));
        return returnDTO;
    }
}
