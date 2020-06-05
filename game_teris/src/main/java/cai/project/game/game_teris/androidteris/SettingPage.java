package cai.project.game.game_teris.androidteris;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import cai.project.game.game_teris.R;
import cai.project.game.game_teris.R2;
import cai.project.game.game_teris.Tool.SPUtils;
import cai.project.game.game_teris.constant.Constant;

public class SettingPage extends BaseActivity implements OnClickListener,OnSeekBarChangeListener {

	public TextView speed;
	public TextView Transverse;
	public TextView Longitudinal;
	public Button setting;
	public Button cancel;
	public TextView touchLength;
	
	
	public SeekBar speed2;
	public SeekBar Transverse2;
	public SeekBar Longitudinal2;
	public SeekBar touchLength2;
	
	public BackService backPlay;
	
	public int speed_value;
	public int Transverse_value;
	public int Longitudinal_value;
	public int touchLength_value;
	
	Handler handler= new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			switch (msg.what) {
			case 1:
				speed.setText((800+msg.arg1-500)+"");
				
				break;
			case 2:
				Transverse.setText((10+msg.arg1-5)+"");
				break;
			case 3:
				Longitudinal.setText((13+msg.arg1-5)+"");
				break;
			case 4:
				touchLength.setText((100+msg.arg1-50)+"");
				break;

			default:
				break;
			}
			
			
		}	
	};
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_page);
		
		speed = (TextView) findViewById(R.id.speed);
		Transverse = (TextView) findViewById(R.id.Transverse);
		Longitudinal = (TextView) findViewById(R.id.Longitudinal);
		touchLength = (TextView) findViewById(R.id.touchLength);
		
		setting = (Button)findViewById(R.id.setting);
		cancel = (Button) findViewById(R.id.cancel);
		speed2 = (SeekBar) findViewById(R.id.speed1);
		Transverse2 =(SeekBar) findViewById(R.id.Transverse1);
		Longitudinal2 = (SeekBar) findViewById(R.id.Longitudinal1);
		touchLength2 = (SeekBar) findViewById(R.id.touchLength1);
		
		
		getGameSharedPreference();
		Log.d("TAG===>","speed_value"+speed_value);
		
		speed.setText(speed_value+"");
		Transverse.setText(Transverse_value+"");
		Longitudinal.setText(Longitudinal_value+"");
		touchLength.setText(touchLength_value+"");
		
		speed2.setProgress(speed_value-800+500);
		Transverse2.setProgress(Transverse_value-10+5);
		Longitudinal2.setProgress(Longitudinal_value-13+5);
		touchLength2.setProgress(touchLength_value-100+50);
		
		Intent intent = new Intent(this,BackService.class);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
		
		setting.setOnClickListener(this);
		cancel.setOnClickListener(this);
		speed2.setOnSeekBarChangeListener(this);
		Transverse2.setOnSeekBarChangeListener(this);
		Longitudinal2.setOnSeekBarChangeListener(this);
		touchLength2.setOnSeekBarChangeListener(this);
	}
	
	
	private ServiceConnection conn  =new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
			BackService.LocalBinder binder =  (BackService.LocalBinder)service;
			backPlay = binder.getService();
			Log.d("TAg===>","backPlay ok");

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		
	};
	
	
	

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.setting){
			backPlay.playOne();
			Constant.SPEED = 800+speed2.getProgress()-500;
			Constant.Cell_widthCount = Integer.parseInt(Transverse.getText().toString());
			Constant.Cell_heightCount = Integer.parseInt(Longitudinal.getText().toString());
			Constant.TouchLength = Integer.parseInt(touchLength.getText().toString());

			setGameSharedPreference();
			finish();
		}else if (v.getId() == R.id.cancel){
			backPlay.playTwo();
			finish();
		}
		

		
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		backPlay.playThree();
         if(seekBar==speed2){
			Message message = new Message();
			message.what=1;
			message.arg1=progress;
			handler.sendMessage(message);
		}else if(seekBar == Transverse2){
			
			Message message = new Message();
			message.what=2;
			message.arg1=progress;
			handler.sendMessage(message);
			
		}else if(seekBar == Longitudinal2){
			
			Message message = new Message();
			message.what=3;
			message.arg1=progress;
			handler.sendMessage(message);
			
		}else if(seekBar == touchLength2){
			
			Message message = new Message();
			message.what=4;
			message.arg1=progress;
			handler.sendMessage(message);
			
		}
	
		
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		unbindService(conn);
	}
	
	public void setGameSharedPreference(){

		SPUtils.saveInt(this,Constant.KEY_SPEED, Constant.SPEED);
		SPUtils.saveInt(this,Constant.KEY_CELL_WIDTHCOUNT, Constant.Cell_widthCount);
		SPUtils.saveInt(this,Constant.KEY_CELL_HEIGHTCOUNT, Constant.Cell_heightCount);
		SPUtils.saveInt(this,Constant.KEY_TOUCHLENGTH, Constant.TouchLength);
	}
	
   public void getGameSharedPreference(){

	   speed_value= SPUtils.getInt(this,Constant.KEY_SPEED, 800);
	   Transverse_value = SPUtils.getInt(this,Constant.KEY_CELL_WIDTHCOUNT, 10);
	   Longitudinal_value = SPUtils.getInt(this,Constant.KEY_CELL_HEIGHTCOUNT, 14);
	   touchLength_value= SPUtils.getInt(this,Constant.KEY_TOUCHLENGTH, 100);


	}
   

	
}
