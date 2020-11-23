package cai.project.module.common_httplibrary;


/**自定义一个接口错误*/
public class OkGoHttpException extends Exception {

    private int code;//错误编号

    public OkGoHttpException(int code,String message ) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
