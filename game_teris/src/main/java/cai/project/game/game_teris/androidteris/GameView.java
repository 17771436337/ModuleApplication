package cai.project.game.game_teris.androidteris;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cai.project.game.game_teris.Tool.SPUtils;
import cai.project.game.game_teris.base.Ground;
import cai.project.game.game_teris.base.Shape;
import cai.project.game.game_teris.constant.Constant;

public class GameView extends View{
	private Context context;

    public Shape shape;
    public Ground ground;
	public static int Cell_Size;
	public static int Cell_widthCount;
	public static int Cell_heightCount;

	
	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		// TODO Auto-generated constructor stub
		Cell_widthCount= Constant.Cell_widthCount;
		Cell_heightCount=Constant.Cell_heightCount;

		initData();
	}



	public GameView(Context context, AttributeSet attrs) {
		this(context, attrs,-1);
	}

	public GameView(Context context) {
		this(context,null);
	}
	
	
	public void display(Shape shape,Ground ground){
		initData();
		this.shape = shape;	
		this.ground = ground;
		invalidate();
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//if(Cell_Size==0){

	//	Init();
//		}
		Paint paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(8);
		canvas.drawRect(0, 0, getWidth(), Cell_heightCount*Cell_Size+2, paint);
		
		Resources res = getResources();
		
		
		if(shape!=null&&ground!=null){

		shape.drawMe(canvas,res);
		ground.drawMe(canvas,res);
		}
		
	}
	


	private void initData() {
		int Width =getWidth();
		int Height = getHeight();

		Log.d("TAG===>WH","宽度："+Width+",高度："+Height);
		if (Width > 0 && Height > 0) {
			Cell_Size = Width / Constant.Cell_widthCount;
			Cell_heightCount=Height/Cell_Size;
			Constant.Cell_heightCount = Height / Cell_Size;
			SPUtils.saveInt(context, Constant.KEY_CELL_HEIGHTCOUNT, Constant.Cell_heightCount);
		}
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}
}
