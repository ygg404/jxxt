package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.ProjectShortCut;
import cn.ux.jxxt.dto.ShortCutDTO;

public interface ShortCutService {

    ShortCutDTO getAllShort();

    ShortCutDTO insertShort(ProjectShortCut projectShortCut);

    ShortCutDTO updateShort(Long cutId, ProjectShortCut projectShortCut);

    ShortCutDTO deleteShort(Long cutId);

    ShortCutDTO getAllShortByPaginated(int page, int per_page, String sortBy, boolean descending);

    ShortCutDTO getAllShortType();

    ShortCutDTO getShortList(Long typeId);
}
