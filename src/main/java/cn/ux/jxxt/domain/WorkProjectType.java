package cn.ux.jxxt.domain;

/**
 * 工作类型和项目类型关系
 */
public class WorkProjectType {
    private int id;
    private int wtypeId;
    private int ptypeId;
    private String ptypeName;


    public int getId() {
        return this.id;
    }

    public void setId( int id){
        this.id = id;
    }

    public int getWtypeId(){
        return this.wtypeId;
    }

    public void setWtypeId(int wtypeId){
        this.wtypeId = wtypeId;
    }

    public int getPtypeId(){
        return this.ptypeId;
    }

    public void setPtypeId(int ptypeId){
        this.ptypeId = ptypeId;
    }

    public String getPtypeName(){
        return  this.ptypeName;
    }

    public void setPtypeName(String ptypeName){
        this.ptypeName = ptypeName;
    }
}
