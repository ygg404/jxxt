package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.CheckQuality;
import cn.ux.jxxt.domain.QualityScore;
import cn.ux.jxxt.dto.CheckQualityDTO;

import java.util.List;

public interface ProjectQualityService {
    CheckQualityDTO addQuality(CheckQuality checkQuality);

    CheckQualityDTO updateQuality(CheckQuality checkQuality);

    CheckQualityDTO getQualityData(String project_no);

    CheckQualityDTO addQualityScore(CheckQualityDTO scores);

    CheckQualityDTO updateQualityScore(CheckQualityDTO scores);

    CheckQualityDTO getQualityScore(String projectNo);

    CheckQualityDTO addFinishDateTime(CheckQuality checkQuality);

    CheckQualityDTO editCutoffDateTime(CheckQuality checkQuality);

    CheckQualityDTO putToOutPut(CheckQuality checkQuality);

}
