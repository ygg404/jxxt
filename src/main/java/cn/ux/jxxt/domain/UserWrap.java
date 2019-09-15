package cn.ux.jxxt.domain;

public class UserWrap {
    private Long id;
    private String userName;
    private String role;
    private String group;
    private String headMan;
    private String deputyLeader;


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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getHeadMan() {
        return headMan;
    }

    public void setHeadMan(String headMan) {
        this.headMan = headMan;
    }

    public String getDeputyLeader() {
        return deputyLeader;
    }

    public void setDeputyLeader(String deputyLeader) {
        this.deputyLeader = deputyLeader;
    }
}
