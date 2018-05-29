package com.example.authorlibrary;

/**
 * Created by DELL on 2017/8/3.
 */

public class AuthorBean {

    /**
     * code : 20000
     * msg : succeed
     * desc : 成功
     * data : {"code":"6FuDsL","redirect_uri":"http://your_callback_uri ","state":null,"nonce":"000ab2954e934b6390724ccbcb26f6e9"}
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
         * code : 6FuDsL
         * redirect_uri : http://your_callback_uri
         * state : null
         * nonce : 000ab2954e934b6390724ccbcb26f6e9
         */

        private String code;
        private String redirect_uri;
        private Object state;
        private String nonce;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRedirect_uri() {
            return redirect_uri;
        }

        public void setRedirect_uri(String redirect_uri) {
            this.redirect_uri = redirect_uri;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }
    }
}
