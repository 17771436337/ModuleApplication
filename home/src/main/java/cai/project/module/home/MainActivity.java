package cai.project.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import cai.project.module.common_arouter.HomePath;
import cai.project.module.common_httplibrary.callback.JsonCallBack;
import cai.project.module.common_httplibrary.model.BaseResponse;
import cai.project.module.common_utils.codeutils.ToastUtils;
import cai.project.module.common_view.widget.CustomToolBar;

@Route(path = HomePath.ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity {
    CustomToolBar  titleView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        titleView = findViewById(R.id.title);
        titleView.setTitle("这是一个标题");


    }


//    public void click(View view){
//        OkGo.<BaseResponse<TestBean>>get("http://v.juhe.cn/toutiao/index")
//                .params("type","gi")
//                .params("key","")
//                .execute(new JsonCallBack<BaseResponse<TestBean>>() {
//
//                    @Override
//                    public void onSuccess(Response<BaseResponse<TestBean>> response) {
//                        TestBean bean =  response.body().getData();
//                        if (bean != null){
//                            ToastUtils.showShort(bean.getData().get(0).getDate());
//                        }else{
//                            ToastUtils.showShort("返回接口");
//                        }
//                    }
//                });
//
//    }

    public void click(View view){
        OkGo.<BaseResponse<TestBean>>get("http://v.juhe.cn/toutiao/index")
                .params("type","gi")
                .params("key","")
                .execute(new JsonCallBack<BaseResponse<TestBean>>() {

                    @Override
                    public void onSuccess(Response<BaseResponse<TestBean>> response) {
                        TestBean bean =  response.body().getData();
                        if (bean != null){
                            ToastUtils.showShort(bean.getData().get(0).getDate());
                        }else{
                            ToastUtils.showShort("返回接口");
                        }
                    }


                    @Override
                    public void onErrorCode(int code, String msg) {
//                        super.onErrorCode(code, msg);//重写方法时，需要将父类屏蔽掉
                        switch (code){
                            case 10001://key失效，做对应的处理
                                break;
                        }
                    }
                });

    }
}
