package cai.project.module.ftp.server;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;

import cai.project.module.common_view.LinNotify;
import cai.project.module.ftp.MainActivity;
import cai.project.module.ftp.R;

/**
 * Created by Administrator on 2018.03.28.
 */

public class FtpService extends Service{


    private FtpServer server;
    private String user = "admin";
    private String password = "123456";
    private static String rootPath;
    private int port = 2221;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();

        startFTP();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        release();

        Toast.makeText(this, "关闭ftp服务", Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化
     *
     * @throws FtpException
     */
    public void init() throws FtpException {
        release();
        startFtp();
    }

    private void startFtp() throws FtpException {
        FtpServerFactory serverFactory = new FtpServerFactory();

        //设置访问用户名和密码还有共享路径
        BaseUser baseUser = new BaseUser();
        baseUser.setName(user);
        baseUser.setPassword(password);
        baseUser.setHomeDirectory(rootPath);

        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new WritePermission());
        baseUser.setAuthorities(authorities);
        serverFactory.getUserManager().save(baseUser);


        ListenerFactory factory = new ListenerFactory();
        factory.setPort(port); //设置端口号 非ROOT不可使用1024以下的端口
        serverFactory.addListener("default", factory.createListener());

        server = serverFactory.createServer();
        server.start();
    }


    /**
     *启动FTP服务器
     */
    public void startFTP(){
        try {
            if (isFTPRun()){
                Toast.makeText(this, "ftp服务已启动成功", Toast.LENGTH_SHORT).show();
            }else{
                init();
                Toast.makeText(this, "启动ftp服务成功", Toast.LENGTH_SHORT).show();
            }
        } catch (FtpException e) {
            e.printStackTrace();
            Toast.makeText(this, "启动ftp服务失败", Toast.LENGTH_SHORT).show();
        }
    }





    /**
     * 是否正在运行ftp
     * @return
     *      true 正在运行
     *      false 停止
     */
    public boolean isFTPRun(){
        if (server != null){
           return !server.isStopped();
        }else{
            return false;
        }
    }




    /**
     * 释放资源
     */
    public void release() {
        stopFtp();
    }

    private void stopFtp() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }

}
