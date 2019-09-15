package cn.ux.jxxt.domain;

import java.sql.Timestamp;

public class WorkType {
    private Long id;
    private String typeName;
    private String typeUnit;
    private Float typeOutput;
    private Timestamp startTime;
    private Timestamp updateTime;
    private String timeData;
    private String typeOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeUnit() {
        return typeUnit;
    }

    public void setTypeUnit(String typeUnit) {
        this.typeUnit = typeUnit;
    }

    public Float getTypeOutput() {
        return typeOutput;
    }

    public void setTypeOutput(Float typeOutput) {
        this.typeOutput = typeOutput;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getTimeData() {
        return timeData;
    }

    public void setTimeData(String timeData) {
        this.timeData = timeData;
    }

    public String getTypeOut() {
        return typeOut;
    }

    public void setTypeOut(String typeOut) {
        this.typeOut = typeOut;
    }
}
