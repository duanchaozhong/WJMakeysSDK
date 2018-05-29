package com.dabcdefg.dell.ddd;

/**
 * Created by DELL on 2018/5/28.
 */

public class AuBean {

    /**
     * code : 20000
     * msg : succeed
     * desc : 成功
     * data : {"nickname":"duanyu","mobile":"13164748588","uuid":"b06961b2ff6741d09c5793b0907d7ea6","nationCode":"+86","name":"13164748588@qeveworld.com"}
     * ok : true
     */

    private String code;
    private String msg;
    private String desc;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public static class DataBean {
        /**
         * nickname : duanyu
         * mobile : 13164748588
         * uuid : b06961b2ff6741d09c5793b0907d7ea6
         * nationCode : +86
         * name : 13164748588@qeveworld.com
         */

        private String nickname;
        private String mobile;
        private String uuid;
        private String nationCode;
        private String name;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getNationCode() {
            return nationCode;
        }

        public void setNationCode(String nationCode) {
            this.nationCode = nationCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
