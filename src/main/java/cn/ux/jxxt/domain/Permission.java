package cn.ux.jxxt.domain;

public class Permission {
    private Long id;
    private String name;
    private String name_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_code() {
        return name_code;
    }

    public void setName_code(String name_code) {
        this.name_code = name_code;
    }

    @Override
    public String toString() {
        return "Permission [id=" + id + ", name=" + name + ", codename=" + name_code + "]";
    }
}
