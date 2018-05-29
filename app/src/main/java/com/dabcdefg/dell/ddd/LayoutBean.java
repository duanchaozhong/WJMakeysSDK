package com.dabcdefg.dell.ddd;

/**
 * Created by DELL on 2018/5/28.
 */

public class LayoutBean {

    /**
     * code : 20000
     * msg : succeed
     * desc : 成功
     * data : 退出登录成功！
     * ok : true
     */

    private String code;
    private String msg;
    private String desc;
    private String data;
    private boolean ok;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
