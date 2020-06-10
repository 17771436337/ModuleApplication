package cai.project.module.ftp.server;

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
import cai.project.module.common_utils.ServerUtils;
import cai.project.module.common_utils.WifiUtils;
import cai.project.module.ftp.R;

public class MainActivity extends FragmentActivity {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ftp_server_main_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ip = WifiUtils.getInstance().getIp();
        port = "2221";
        if (TextUtils.isEmpty(ip)) {
            tvUrl.setText("URL: ");
        } else {
            tvUrl.setText("URL: ftp://" + ip + ":" + port);
        }

        tvAccount.setText("账号：admin");
        tvPassword.setText("密码：123456");
    }

    @OnClick({R.id.bt_start, R.id.bt_setup})
    public void onClick(View view) {
        if (view.getId() == R.id.bt_start) {

            RxPermissions rxPermissions = new RxPermissions(this);

            rxPermissions
                    .request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) { // Always true pre-M
                            if (ServerUtils.isServiceRunning("cai.project.module.ftp.server.FtpService")) {
                                stopService(new Intent(this, FtpService.class));
                                btStart.setText("开始");
                            }else{
                                startService(new Intent(this,FtpService.class));
                                btStart.setText("停止");
                            }
                        } else {
                          Toast.makeText(MainActivity.this,"文件权限未开启",Toast.LENGTH_SHORT).show();
                        }
                    });


        } else if (view.getId() == R.id.bt_setup) {

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, FtpService.class));
    }
}
