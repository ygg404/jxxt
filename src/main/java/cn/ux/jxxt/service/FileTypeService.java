package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.FileType;
import cn.ux.jxxt.dto.FilelistDTO;
import cn.ux.jxxt.dto.FiletypeDTO;

import java.util.List;

public interface FileTypeService {
    FiletypeDTO getFileTypeList();

    FiletypeDTO addFiletype(FileType filetype);

    FiletypeDTO deleteFiletypeById(int id);

    FiletypeDTO getFileTypeListByPid(int pid);

    FiletypeDTO updateFiletype(FileType fileType);

    int getFiletypeByMax(int pid);

    int getFiletypeByMin(int pid);

    FiletypeDTO getTopFiletypeBySort(FileType fileType);

    FiletypeDTO updateFiletypeSort(FileType fileType);
}
