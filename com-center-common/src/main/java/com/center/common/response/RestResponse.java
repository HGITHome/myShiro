package com.center.common.response;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {

    public static final int success = 200;

    public static final int fail = 500;

    public static final RestResponse SUCCESS = new RestResponse();


    private String msg = "success";

    private int code = success;

    private T data;

    public RestResponse() {
        super();
        this.code = success;
        this.data = null;
    }

    public RestResponse(T data) {
        super();
        this.code = success;
        this.data = data;
    }

    public RestResponse(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public RestResponse(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = fail;
    }

    /**
     *
     * 返回成功
     * @param data
     * @return
     */
    public RestResponse<T> returnSuccess(T data) {
        this.data = data;
        return this;
    }

    /**
     *
     * 返回失败
     *
     * @param code
     * @param msg
     * @return
     */
    public  RestResponse<T> returnFail(Integer code , String msg) {

        this.code = code;

        this.msg = msg;

        return  this;
    }

    public  RestResponse<T> returnFail(String msg) {

        this.code = 500;

        this.msg = msg;

        return  this;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
