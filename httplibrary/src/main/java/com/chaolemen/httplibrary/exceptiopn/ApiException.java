package com.chaolemen.httplibrary.exceptiopn;

public class ApiException extends Throwable{

    String msg;
    int code;
    public ApiException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
