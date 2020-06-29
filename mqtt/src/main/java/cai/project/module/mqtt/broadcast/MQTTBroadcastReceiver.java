package cai.project.module.mqtt.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cai.project.module.mqtt.IGetMessageCallBack;
import cai.project.module.mqtt.MQTTServiceConnection;
import cai.project.module.mqtt.service.MQTTClientService;

public class MQTTBroadcastReceiver extends BroadcastReceiver implements IGetMessageCallBack {

    private MQTTServiceConnection serviceConnection;

    private Context context;

    public static final String MQTT_START = "com.cmy.module.mqtt.start";
    public static final String MQTT_STOP = "com.cmy.module.mqtt.stop";
    public static final String MQTT_SEND = "com.cmy.module.mqtt.send";

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (serviceConnection == null) {
            serviceConnection = new MQTTServiceConnection();
            serviceConnection.setIGetMessageCallBack(this);
        }

       String action =  intent.getAction();
        switch (action){
            case MQTT_START://开始mqtt服务
                Intent it = new Intent(context, MQTTClientService.class);
                context.bindService(it, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case MQTT_SEND://发送消息
                String content = intent.getStringExtra("MESSAGE");
                MQTTClientService.publish(content);
                break;
            case MQTT_STOP://关闭mqtt服务
                context.unbindService(serviceConnection);
                break;
        }

    }

    @Override
    public void setMessage(String message) {

    }

}
