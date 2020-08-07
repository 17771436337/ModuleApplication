package cai.project.module.ftp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import cai.project.module.common.BaseActivity;
import cai.project.module.common_mvp.presenter.BasePresenter;
import cai.project.module.common_utils.codeutils.NetworkUtils;
import cai.project.module.common_utils.codeutils.NotificationUtils;
import cai.project.module.common_utils.codeutils.ServiceUtils;
import cai.project.module.common_view.LinNotify;
import cai.project.module.ftp.server.FtpService;

import static android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_url)
    TextView tvUrl;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_setup)
    Button btSetup;



    //----------------------
    String ip;
    String port;

    @Override
    protected void getIntents(Intent intent) {

    }
    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.ftp_server_main_activity;
    }

    @Override
    public void initData() {
        ip = NetworkUtils.getIPAddress(true);
        port = "2221";
        if (TextUtils.isEmpty(ip)) {
            tvUrl.setText("URL: ");
        } else {
            tvUrl.setText("URL: ftp://" + ip + ":" + port);
        }

        tvAccount.setText("账号：admin");
        tvPassword.setText("密码：123456");



        if (ServiceUtils.isServiceRunning("cai.project.module.ftp.server.FtpService")) {
            btStart.setText("停止");
            toCreateNotification("FTP服务器运行中");
        } else {
            btStart.setText("开始");
            toCreateNotification("FTP服务器已关闭");
        }

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (ServiceUtils.isServiceRunning("cai.project.module.ftp.server.FtpService")) {
            btStart.setText("停止");
            toCreateNotification("FTP服务器运行中");
        } else {
            btStart.setText("开始");
            toCreateNotification("FTP服务器已关闭");
        }
    }



    @OnClick({R.id.bt_start, R.id.bt_setup})
    public void onClick(View view) {
        if (view.getId() == R.id.bt_start) {

            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions
                    .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) { // Always true pre-M
                            if (ServiceUtils.isServiceRunning("cai.project.module.ftp.server.FtpService")) {
                                stopService(new Intent(this, FtpService.class));
                                btStart.setText("开始");
                                toCreateNotification("FTP服务器运行中");
                            } else {
                                startService(new Intent(this, FtpService.class));
                                btStart.setText("停止");
                                toCreateNotification("FTP服务器已关闭");
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "文件权限未开启", Toast.LENGTH_SHORT).show();
                        }
                    });


        } else if (view.getId() == R.id.bt_setup) {

        }
    }

    public  void toCreateNotification(String message){
        LinNotify.show(this, 0, 0, "FTP服务器", null,
                message, PRIORITY_DEFAULT, null,null ,0, "chat", FtpService.class,MainActivity.class);
    }



}
