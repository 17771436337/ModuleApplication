package cai.project.module.common_httplibrary.callback;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cai.project.module.common_httplibrary.OkGoHttpException;
import cai.project.module.common_httplibrary.OkGoHttpUtil;
import cai.project.module.common_httplibrary.convert.JsonConvert;

public abstract class JsonCallBack<T> extends AbsCallback<T>{

    private Type type;
    private Class<T> clazz;


    public JsonCallBack() { }

    public JsonCallBack(Type type) {
        this.type = type;
    }

    public JsonCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }



    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }


    @Override
    public void onError(Response<T> response) {
        super.onError(response);

        Throwable exception = response.getException();
        if(exception != null) exception.printStackTrace();
        if(exception instanceof UnknownHostException || exception instanceof ConnectException){
            Toast.makeText(OkGoHttpUtil.getApp(),"服务器连接失败，请检查后重试！",Toast.LENGTH_SHORT).show();
        }else if(exception instanceof SocketTimeoutException){
            Toast.makeText(OkGoHttpUtil.getApp(),"网络请求超时！",Toast.LENGTH_SHORT).show();
        }else if(exception instanceof HttpException){
            Toast.makeText(OkGoHttpUtil.getApp(),"服务器维护，请稍后再试(404,500)！",Toast.LENGTH_SHORT).show();
        }else if(exception instanceof StorageException){
            Toast.makeText(OkGoHttpUtil.getApp(),"SD卡不存在或者没有权限！",Toast.LENGTH_SHORT).show();
        } else if(exception instanceof OkGoHttpException){//自定义的错误信息
            //比如客户端与服务器交互之间出现的认为定义错误
//

            onErrorCode( ((OkGoHttpException) exception).getCode(),exception.getMessage());
        }

    }


    /**用户自定义code信息*/
    public void onErrorCode(int code,String msg){
        Log.d("接口测试：","错误code："+code+",错误信息:"+msg);
        Toast.makeText(OkGoHttpUtil.getApp(),"错误代码：" + code +
                     "，错误信息：" + msg,Toast.LENGTH_SHORT).show();

    }

}
