package cai.project.module.common_httplibrary.model;


import java.io.Serializable;

/**处理部分信息返回为空*/
public class SimpleResponse implements Serializable {


    private static final long serialVersionUID = -2880620027457962207L;

    private int code;//编码
    private String msg;//信息类型


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


    public BaseResponse toResponse() {
        BaseResponse lzyResponse = new BaseResponse();
        lzyResponse.setCode(code);
        lzyResponse.setMsg(msg);
        return lzyResponse;
    }
}
