package cn.ux.jxxt.domain;

public class ProjectCoe {
    private String project_no;
    private String project_name;
    private String project_start_date_time;
    private int project_rate;
    private String project_output;
    private float project_actually_output;

    public String getProject_no(){
        return this.project_no;
    }

    public void setProject_no(String project_no){
        this.project_no = project_no;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_start_date_time(){
        return this.project_start_date_time;
    }

    public void setProject_start_date_time(String project_start_date_time){
        this.project_start_date_time = project_start_date_time;
    }

    public int getProject_rate(){
        return  this.project_rate;
    }

    public void  setProject_rate(int project_rate){
        this.project_rate = project_rate;
    }

    public String getProject_output(){
        return project_output;
    }

    public void setProject_output(String project_output){
        this.project_output = project_output;
    }


    public float getProject_actually_output(){
        return this.project_actually_output;
    }

    public void setProject_actually_output(float project_actually_output){
        this.project_actually_output = project_actually_output;
    }
}
