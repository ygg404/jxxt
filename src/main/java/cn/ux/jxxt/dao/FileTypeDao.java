package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.FileType;
import cn.ux.jxxt.dto.FiletypeDTO;

import java.util.List;

public interface FileTypeDao {
    /**
     * 获取所有文件类型列表
     * @return
     */
    public List<FileType> getFileTypeList();

    /**
     * 添加文件类型
     */
    public int addFiletype(FileType fileType);

    /**
     * 根据id 删除文件类型
     */
    public int deleteFiletypeById(int id);
    /**
     * 通过父id获取所有文件类型列表
     * @return
     */
    public List<FileType> getFileTypeListByPid(int pid);

    /**
     * 更新文件类目
     */
    public int updateFiletype(FileType fileType);

    /**
     * 获取同父类下文件类目的最大排序号
     */
    public int getFiletypeByMax(int pid);

    /**
     * 获取同父类下文件类目的最小排序号
     */
    public int getFiletypeByMin(int pid);

    /**
     * 获取同父类中 顺序排前一位的类目
     */
    public FileType getTopFiletypeBySort(FileType fileType);

    /**
     * 更新类目顺序
     */
    public int updateFiletypeSort(FileType fileType);
}
