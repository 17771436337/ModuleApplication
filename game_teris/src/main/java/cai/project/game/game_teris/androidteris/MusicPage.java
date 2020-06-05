package cai.project.game.game_teris.androidteris;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import cai.project.game.game_teris.R;
import cai.project.game.game_teris.R2;
import cai.project.game.game_teris.Tool.SPUtils;
import cai.project.game.game_teris.constant.Constant;


public class MusicPage extends BaseActivity implements OnClickListener {

	public CheckBox backMusic;
	public CheckBox buttonMusic;
	public CheckBox buttonGridLine;
	public Button setting;
	public Button cancel;
	
	public Boolean isBackMusic;
	public Boolean isButtonMusic;
	public Boolean isButtonGridLine;
	
	public BackService backPlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.music_page);
		
		backMusic = (CheckBox) findViewById(R.id.backmusic);
		buttonMusic =  (CheckBox) findViewById(R.id.buttonmusic);
		buttonGridLine = (CheckBox) findViewById(R.id.buttonGridLine);
		setting = (Button)findViewById(R.id.setting);
		cancel = (Button) findViewById(R.id.cancel);
		getGameSharedPreference();
		
		backMusic.setChecked(isBackMusic);
		buttonMusic.setChecked(isButtonMusic);
		buttonGridLine.setChecked(isButtonGridLine);
		
		Intent intent = new Intent(this,BackService.class);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
		
		setting.setOnClickListener(this);
		cancel.setOnClickListener(this);
		
	}
	
	
	private ServiceConnection conn  =new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
			BackService.LocalBinder binder =  (BackService.LocalBinder)service;
			backPlay = binder.getService();
			Log.d("TAg===>",(backPlay==null)+ "ok");

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		
	};
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub


		if (v.getId() == R.id.setting){
			backPlay.playTwo();
			isButtonMusic = buttonMusic.isChecked();
			isBackMusic = backMusic.isChecked();
			isButtonGridLine = buttonGridLine.isChecked();
			Constant.GridPaint = isButtonGridLine;
			backPlay.isMusic= isButtonMusic;
			backPlay.isBackMusic = isBackMusic;

		}else if (v.getId() == R.id.cancel){
			if(!isBackMusic){
				backPlay.pauseBackMusic();
			}else{
				backPlay.startBackMusic();
			}

			setGameSharedPreference();
			finish();
		}else if(v.getId() == R.id.cancel){
			backPlay.playTwo();
			finish();
		}


	}
	
	
	
	public void setGameSharedPreference(){

		SPUtils.saveBoolean(this,"isBackMusic", isBackMusic);
		SPUtils.saveBoolean(this,"isButtonMusic", isButtonMusic);
		SPUtils.saveBoolean(this,"isButtonGridLine", isButtonGridLine);

	}
	
    public void getGameSharedPreference(){
		isBackMusic = SPUtils.getBoolean(this,"isBackMusic", true);
		isButtonMusic = SPUtils.getBoolean(this,"isButtonMusic", true);
		isButtonGridLine = SPUtils.getBoolean(this,"isButtonGridLine", true);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		unbindService(conn);
		finish();
	}

	
}
