package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.FileData;
import cn.ux.jxxt.domain.Filelist;

import java.util.List;
import java.util.Map;

public interface FileListDao {

    /**
     * 获取所有上传文件的列表
     */
    public List<FileData> getFiledataList(Map<String, Object> params);

    /**
     * 更新指定的文件信息
     */
    public int updateFilelist(Filelist filelist);

    /**
     * 添加文件
     */
    public int addFilelist(Filelist filelist);

    /**
     * 删除文件
     */
    public int deleteFilelistByFileno(int fileNo);

    /**
     * 获取文件总和
     */
    public Long getFilelistCount(Map<String, Object> params);

    /**
     * 通过文件编号获取文件信息
     */
    public FileData getFiledataListByFileNo(int fileNo);
}
