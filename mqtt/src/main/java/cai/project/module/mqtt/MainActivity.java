package cai.project.module.mqtt;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.mqtt.broadcast.MQTTBroadcastReceiver;

/**
 * MQTT协议
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.stop)
    Button stop;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mqtt_main_activity);
        ButterKnife.bind(this);

        MQTTBroadcastReceiver    myBroadcastReceiver = new MQTTBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MQTTBroadcastReceiver.MQTT_START);
        filter.addAction(MQTTBroadcastReceiver.MQTT_SEND);
        filter.addAction(MQTTBroadcastReceiver.MQTT_STOP);
        registerReceiver(myBroadcastReceiver, filter);
    }

    @OnClick({R.id.start, R.id.send, R.id.stop})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.start:
                intent.setAction(MQTTBroadcastReceiver.MQTT_START);
                break;
            case R.id.send:
                intent.setAction(MQTTBroadcastReceiver.MQTT_SEND);
                intent.putExtra("MESSAGE","发送测试数据");
                break;
            case R.id.stop:
                intent.setAction(MQTTBroadcastReceiver.MQTT_STOP);
                break;
        }
        sendBroadcast(intent);
    }
}
