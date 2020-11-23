package cai.project.module.common_httplibrary.model;


import java.io.Serializable;

/**处理部分信息返回为空*/
public class SimpleResponse implements Serializable {


    private static final long serialVersionUID = -2880620027457962207L;
    private int error_code;//编码
    private String reason;//信息类型


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

    public BaseResponse toResponse() {
        BaseResponse lzyResponse = new BaseResponse();
        lzyResponse.setCode(error_code);
        lzyResponse.setMsg(reason);
        return lzyResponse;
    }
}
