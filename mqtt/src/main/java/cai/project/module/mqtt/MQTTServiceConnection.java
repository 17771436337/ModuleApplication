package cai.project.module.mqtt;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import cai.project.module.mqtt.service.MQTTClientService;

public class MQTTServiceConnection implements ServiceConnection {

    private MQTTClientService mqttService;
    private IGetMessageCallBack IGetMessageCallBack;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        mqttService = ((MQTTClientService.CustomBinder)iBinder).getService();
        mqttService.setIGetMessageCallBack(IGetMessageCallBack);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    public MQTTClientService getMqttService(){
        return mqttService;
    }

    public void setIGetMessageCallBack(IGetMessageCallBack IGetMessageCallBack){
        this.IGetMessageCallBack = IGetMessageCallBack;
    }

}
