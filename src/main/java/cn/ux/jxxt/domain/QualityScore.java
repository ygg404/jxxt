package cn.ux.jxxt.domain;

public class QualityScore {
    private long id;                        //id
    private long type_id;                   //检查项id
    private String check_result;            //检查结果
    private String check_type;              //检查类型
    private String check_a;                //扣分42
    private String check_b;                 //扣分12
    private String check_c;                 //扣分4
    private String check_d;                 //扣分1
    private String project_no;              //项目编号

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getType_id() {
        return type_id;
    }

    public void setType_id(long type_id) {
        this.type_id = type_id;
    }

    public String getCheck_result() {
        return check_result;
    }

    public void setCheck_result(String check_result) {
        this.check_result = check_result;
    }

    public String getCheck_type() {
        return check_type;
    }

    public void setCheck_type(String check_type) {
        this.check_type = check_type;
    }

    public String getCheck_a() {
        return check_a;
    }

    public void setCheck_a(String check_a) {
        this.check_a = check_a;
    }

    public String getCheck_b() {
        return check_b;
    }

    public void setCheck_b(String check_b) {
        this.check_b = check_b;
    }

    public String getCheck_c() {
        return check_c;
    }

    public void setCheck_c(String check_c) {
        this.check_c = check_c;
    }

    public String getCheck_d() {
        return check_d;
    }

    public void setCheck_d(String check_d) {
        this.check_d = check_d;
    }

    public String getProject_no() {
        return project_no;
    }

    public void setProject_no(String project_no) {
        this.project_no = project_no;
    }
}
