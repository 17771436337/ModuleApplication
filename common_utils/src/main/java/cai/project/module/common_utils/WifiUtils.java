package cai.project.module.common_utils;

import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public final class WifiUtils  extends IPUtils{

    private static WifiUtils instance;

    private WifiUtils() { }

    public static WifiUtils getInstance(){
        if (instance == null){
            instance = new WifiUtils();
        }
        return instance;
    }


    public  String getIp(){
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) Utils.getInstance().getApplication().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }
}
