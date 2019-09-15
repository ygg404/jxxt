package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.Filelist;
import cn.ux.jxxt.dto.FilelistDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileListService {
    FilelistDTO getFileList(int page, int per_page, String sortBy, boolean descending, String search, String startDate, String endDate,String userId,String typeId);

    FilelistDTO getFilelistByFileNo(int fileNo);

    FilelistDTO deleteFilelistByFileno(int fileNo);

    FilelistDTO addfileList(Filelist filelist);

    FilelistDTO updateFileList(Filelist filelist);

    FilelistDTO uploadfile(MultipartFile file);

}
