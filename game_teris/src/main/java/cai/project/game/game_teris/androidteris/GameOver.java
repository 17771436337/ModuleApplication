package cai.project.game.game_teris.androidteris;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cai.project.game.game_teris.R;

public class GameOver extends BaseActivity{

	public TextView textViewTime;
	public TextView textViewScore;
	public Button button1;

	public int score;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game_over);
		
		textViewTime = (TextView) findViewById(R.id.TextView1);
		textViewScore = (TextView) findViewById(R.id.TextView2);
		
		
		LinearLayout linear1 = (LinearLayout) findViewById(R.id.linear1);
		AnimationSet set =new AnimationSet(false);
		
		Animation animation1 = new ScaleAnimation(0, 1, 0, 1);
		animation1.setDuration(2000);
		set.addAnimation(animation1);
			
		linear1.setAnimation(set);
		
		
		
		button1 = (Button) findViewById(R.id.Button1);

		Intent intent = getIntent();
		
		final String str =intent.getStringExtra("time");
		Log.d("TAG===>",str);
		textViewTime.setText(str);
		score = Integer.parseInt(intent.getStringExtra("score"));
		textViewScore.setText(intent.getStringExtra("score"));
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameOver.this,MainActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
		

		
	}
	
	
	
	
	
	
	
}
