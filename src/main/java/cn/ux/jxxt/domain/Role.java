package cn.ux.jxxt.domain;

import javax.validation.constraints.NotBlank;

public class Role {
    private Long id;
    @NotBlank(message = "name不能为空")
    private String name;

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
    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + "]";
    }
}
