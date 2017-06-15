package com.smilexie.retrofitsoap.webservice;

/**
 * Created by Administrator on 2017/3/17.
 */

public class ResponseBean<T> {
    public int code;
    public String msg;
    public T body;

    @Override
    public String toString() {
        return "ResponseBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", body=" + body +
                '}';
    }
}
