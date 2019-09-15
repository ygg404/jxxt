package cn.ux.jxxt.domain.custom;

public class OutPutWrap {
    private Long id;
    private Long typeId;                                //类型Id
    private Float projectRatio;                             //难度系数
    private Float workLoad;                                 //工作量
    private String projectNo;                           //项目编号
    private Float projectOutPut;                        //已核算产值
    private Long groupId;                               //组id
    private String typeName;
    private String typeUnit;
    private Float typeOutput;
    private boolean isCheck;                    //是否已选中

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Float getProjectRatio() {
        return projectRatio;
    }

    public void setProjectRatio(Float projectRatio) {
        this.projectRatio = projectRatio;
    }

    public Float getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(Float workLoad) {
        this.workLoad = workLoad;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public Float getProjectOutPut() {
        return projectOutPut;
    }

    public void setProjectOutPut(Float projectOutPut) {
        this.projectOutPut = projectOutPut;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
