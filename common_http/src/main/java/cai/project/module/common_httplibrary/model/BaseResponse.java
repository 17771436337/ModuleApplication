package cai.project.module.common_httplibrary.model;


import java.io.Serializable;

/**根据不同的数据类型，抽出一个抽象接口*/
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 2215924580235306936L;

    private int code;//编码
    private String msg;//信息类型
    private T data;//数据信息


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
