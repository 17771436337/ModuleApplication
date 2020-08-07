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
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;

import cai.project.game.game_teris.R;
import cai.project.game.game_teris.R2;
import cai.project.game.game_teris.Tool.SPUtils;
import cai.project.game.game_teris.constant.Constant;
import cai.project.module.common_arouter.GamePath;
import cai.project.module.common_mvp.presenter.BasePresenter;

@Route(path = GamePath.GAME_TERIS)
public class HomePage extends BaseActivity implements OnClickListener{

	public ImageView startGame;
	public ImageView setting;
	public ImageView music;
	public ImageView about;
	private Context mContext;
	
	public BackService backPlay;


	@Override
	protected void getIntents(Intent intent) {
		getGameSharedPreference();
	}

	@Override
	protected BasePresenter createPresenter() {
		return new BasePresenter();
	}

	@Override
	public int getLayoutId() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		return R.layout.home_page;
	}

	@Override
	public void initView() {
		super.initView();
		startGame = (ImageView) findViewById(R.id.startGame);

		setting = (ImageView) findViewById(R.id.setUp);
		music = (ImageView) findViewById(R.id.music);
		about = (ImageView) findViewById(R.id.aboutMe);

		startGame.setOnClickListener(this);
		setting.setOnClickListener(this);
		music.setOnClickListener(this);
		about.setOnClickListener(this);



	}

	@Override
	public void initData() {
		Intent intent = new Intent(this,BackService.class);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);


		Log.d("TAg===>","backPlay0.5 ok");
	}


	
	private ServiceConnection conn  =new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
			BackService.LocalBinder binder =  (BackService.LocalBinder)service;
			backPlay = binder.getService();
			backPlay.CreateBackSound();
			Log.d("TAg===>","backPlay ok");

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		
	};
	
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub


		if (v.getId() == R.id.startGame){
			backPlay.playFour();
			Intent intent = new Intent(this,MainActivity.class);

			startActivity(intent);
			backPlay.playFive();

		}else if(v.getId() == R.id.setUp){
			backPlay.playOne();

			Intent intent1 = new Intent(this,SettingPage.class);

			startActivity(intent1);
		}else if(v.getId() == R.id.music){
			backPlay.playOne();

			Intent intent2 = new Intent(this,MusicPage.class);
			startActivity(intent2);

		}else if(v.getId() == R.id.aboutMe){
			backPlay.playFour();
			backPlay.playFive();

			Intent intent3 = new Intent(this,AboutActivity.class);
			startActivity(intent3);

		}
		
	}
	

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);
	}



	public void getGameSharedPreference(){
		Constant.SPEED = SPUtils.getInt(this,Constant.KEY_SPEED, 800);
		 Constant.Cell_widthCount = SPUtils.getInt(this,Constant.KEY_CELL_WIDTHCOUNT, 10);
		 Constant.Cell_heightCount = SPUtils.getInt(this,Constant.KEY_CELL_HEIGHTCOUNT, 14);
		 Constant.TouchLength = SPUtils.getInt(this,Constant.KEY_TOUCHLENGTH, 100);
		 Constant.GridPaint = SPUtils.getBoolean(this,Constant.KEY_ISBUTTONGRIDLINE, true);
	}



}
