package cai.project.module.common_httplibrary.model;


import java.io.Serializable;

/**根据不同的数据类型，抽出一个抽象接口*/
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 2215924580235306936L;

    private int error_code;//编码
    private String reason;//信息类型
    private T result;//数据信息


    public int getCode() {
        return error_code;
    }

    public void setCode(int code) {
        this.error_code = code;
    }

    public String getMsg() {
        return reason;
    }

    public void setMsg(String msg) {
        this.reason = msg;
    }

    public T getData() {
        return result;
    }

    public void setData(T data) {
        this.result = data;
    }
}
