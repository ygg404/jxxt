package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.Log;
import cn.ux.jxxt.vo.Pagination;

import java.sql.Timestamp;

public class LogDTO extends BasicDTO {
    private Long id;
    private String userName;
    private String userContent;
    private Timestamp operationTime;
    private String requestIp;
    private Pagination<Log> logPagination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent;
    }

    public Timestamp getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Timestamp operationTime) {
        this.operationTime = operationTime;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public Pagination<Log> getLogPagination() {
        return logPagination;
    }

    public void setLogPagination(Pagination<Log> logPagination) {
        this.logPagination = logPagination;
    }
}
