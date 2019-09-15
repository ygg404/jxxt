package cn.ux.jxxt.domain;

import javax.validation.constraints.NotBlank;

public class PassWord {
    private String userAccount;
    @NotBlank(message = "旧密码不能为空")
    private String oldPassWord;
    @NotBlank(message = "新密码不能为空")
    private String newPassWord;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }
}
