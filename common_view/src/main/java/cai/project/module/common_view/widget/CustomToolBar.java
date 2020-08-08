package cai.project.module.common_view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cai.project.module.common_utils.codeutils.SizeUtils;
import cai.project.module.common_view.R;
/** 封装标题*/
public class CustomToolBar extends RelativeLayout {
    private static final int MODE_IMAGE = 0;
    private static final int MODE_TEXT = 1;

    //--------------------
    private Context context;
    private View leftView;
    private View rightView;
    private TextView tvTitle;

    //---------------------------
    private int isLeftTextOrImageMode = MODE_IMAGE;//判断左边是用图片还是文字，true为图片
    private int  isRightTextOrImageMode = MODE_TEXT;//判断右边是用图片还是文字，true为图片
    private boolean showRightView = false;//右边View是否显示
    private boolean showLeftView = true;//左边View是否显示

    private String strTitle;//设置标题的文字信息
    private int colorTitleText;//设置标题文字颜色
    private float sizeTitleText;//设置标题文字大小

    private String strRightText;//右边文字信息
    private int colorRightText;//设置右边文字的颜色
    private Drawable imageRight;//设置右边图片
    private float sizeRightText;//设置右边文字大小
    private int imageRightWidth;//设置右边图片的宽度
    private int imageRightHeight;//设置右边图片的宽度

    private String strLeftText;//左边文字信息
    private int colorLeftText;//设置左边文字的颜色
    private Drawable imageLeft;//设置右边图片
    private float sizeLeftText;//设置标题文字大小
    private int imageLeftWidth;//设置右边图片的宽度
    private int imageLeftHeight;//设置右边图片的宽度

    public CustomToolBar(Context context) {
        this(context, null);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        if (attrs != null) {
            setAttrs(attrs);
        }
        init();
    }

    /**通过布局设置*/
    private void setAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar);

        strTitle = typedArray.getString(R.styleable.CustomToolBar_titleText);
        colorTitleText = typedArray.getColor(R.styleable.CustomToolBar_titleTextColor, Color.BLACK);
        sizeTitleText = typedArray.getDimension(R.styleable.CustomToolBar_titleTextSize, SizeUtils.sp2px(15));


        showRightView = typedArray.getBoolean(R.styleable.CustomToolBar_rightShow,false);
        isRightTextOrImageMode = typedArray.getInteger(R.styleable.CustomToolBar_rightShowView, MODE_TEXT);
        strRightText = typedArray.getString(R.styleable.CustomToolBar_rightText);
        colorRightText = typedArray.getColor(R.styleable.CustomToolBar_rightTextColor,Color.BLACK);
        imageRight = typedArray.getDrawable(R.styleable.CustomToolBar_rightImage);
        sizeRightText = typedArray.getDimension(R.styleable.CustomToolBar_rightTextSize,  SizeUtils.sp2px(10));
        imageRightWidth = typedArray.getDimensionPixelSize(R.styleable.CustomToolBar_rightImageWidth, SizeUtils.dp2px(30));
        imageRightHeight = typedArray.getDimensionPixelSize(R.styleable.CustomToolBar_rightImageHeight, SizeUtils.dp2px(30));


        showLeftView = typedArray.getBoolean(R.styleable.CustomToolBar_leftShow,true);
        isLeftTextOrImageMode = typedArray.getInteger(R.styleable.CustomToolBar_leftShowView,MODE_IMAGE);
        strLeftText = typedArray.getString(R.styleable.CustomToolBar_leftText);
        colorLeftText = typedArray.getColor(R.styleable.CustomToolBar_leftTextColor,Color.BLACK);
        imageLeft = typedArray.getDrawable(R.styleable.CustomToolBar_leftImage);
        sizeLeftText = typedArray.getDimension(R.styleable.CustomToolBar_leftTextSize, SizeUtils.sp2px(10));
        imageLeftWidth = typedArray.getDimensionPixelSize(R.styleable.CustomToolBar_leftImageWidth, SizeUtils.dp2px(30));
        imageLeftHeight = typedArray.getDimensionPixelSize(R.styleable.CustomToolBar_leftImageHeight, SizeUtils.dp2px(30));

        typedArray.recycle();//回收资源
    }

    private void init(){
        if (showLeftView){
            addLeftView();
        }
        if (showRightView){
            addRightView();
        }
        addtitleView();
    }

    /**添加标题*/
    private void addtitleView() {
        tvTitle = new TextView(context);
        tvTitle.setText(strTitle == null?"标题": strTitle);
        tvTitle .setGravity(Gravity.CENTER);//居中
        tvTitle.setTextColor(colorTitleText);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,sizeTitleText);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,SizeUtils.dp2px(10),0,SizeUtils.dp2px(10));
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        tvTitle.setLayoutParams(layoutParams);//上面设置控件的高宽后就落实
        addView(tvTitle);
    }
    /**添加左边视图*/
    private void addLeftView(){
        switch (isLeftTextOrImageMode){
            case MODE_IMAGE:
              leftView =  addLeftImageView();
                break;
            case MODE_TEXT:
                leftView =  addLeftTextView();
                break;
                default:
                  leftView = new View(context);
               break;
        }
        addView(leftView);
    }

    private void addRightView(){
        switch (isRightTextOrImageMode){
            case MODE_IMAGE:
              rightView =  addRightImageView();
                break;
            case MODE_TEXT:
                rightView =  addRightTextView();
                break;
                default:
                    rightView = new View(context);
                    break;
        }
        addView(rightView);
    }

    private View addRightTextView() {
        TextView textView = new TextView(context);
        textView .setGravity(Gravity.CENTER);//居中
        textView.setTextColor(Color.BLACK);
      if (!TextUtils.isEmpty(strRightText)) {
          textView.setText(strRightText);
      }
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,sizeRightText);
        textView.setTextColor(colorRightText);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMargins(0,SizeUtils.dp2px(10),SizeUtils.dp2px(10),SizeUtils.dp2px(10));
        textView.setLayoutParams(layoutParams);
     return textView;
    }

    private View addRightImageView() {
        ImageView  imageView = new ImageView(context);

     if (imageRight != null){
         imageView.setImageDrawable(imageRight);
     }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageRightWidth, imageRightHeight);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMargins(0,SizeUtils.dp2px(10),SizeUtils.dp2px(10),SizeUtils.dp2px(10));
        imageView.setLayoutParams(layoutParams);
       return imageView;
    }

    /**添加左边文字*/
    private View addLeftTextView(){
        TextView textView = new TextView(context);
        textView .setGravity(Gravity.CENTER);//居中
        textView.setTextColor(Color.BLACK);
        if (!TextUtils.isEmpty(strLeftText)) {
            textView.setText(strLeftText);
        }

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,sizeLeftText);
        textView.setTextColor(colorLeftText);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMargins(SizeUtils.dp2px(10),SizeUtils.dp2px(10),0,SizeUtils.dp2px(10));
        textView.setLayoutParams(layoutParams);//上面设置控件的高宽后就落实
        return textView;
    }

    /**添加左边图片*/
    private View addLeftImageView(){
        ImageView  imageView = new ImageView(context);
        if (imageLeft != null){
            imageView.setImageDrawable(imageLeft);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageLeftWidth, imageLeftHeight);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMargins(SizeUtils.dp2px(10),SizeUtils.dp2px(10),0,SizeUtils.dp2px(10));
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    //----------------------公共调用方法--------------------
    /**设置标题名称*/
    public void setTitle(CharSequence text){
        if (tvTitle != null){
            tvTitle.setText(text);
        }
    }

    /**设置左边的文字*/
    public void setLeftText(CharSequence text){
        if (showLeftView){//判断文字是否显示
            if (isLeftTextOrImageMode == MODE_TEXT){
                TextView textView = (TextView) leftView;
                textView.setText(text);
            }
        }
    }
    /**设置左边的图片*/
    public void setLeftImage(@DrawableRes int res){
        if (showLeftView) {//判断文字是否显示
            if (isLeftTextOrImageMode == MODE_IMAGE){
                ImageView imageView = (ImageView) leftView;
                imageView.setImageResource(res);
            }
        }
    }

    /**设置左边的图片*/
    public void setLeftImage(Drawable drawable){
        if (showLeftView) {//判断文字是否显示
            if (isLeftTextOrImageMode == MODE_IMAGE){
                ImageView imageView = (ImageView) leftView;
                imageView.setImageDrawable(drawable);
            }
        }
    }

    /**设置右边的文字*/
    public void setRightText(CharSequence text){
        if (showRightView){//判断文字是否显示
            if (isRightTextOrImageMode == MODE_TEXT){
                TextView textView = (TextView) rightView;
                textView.setText(text);
            }
        }
    }

    /**设置右边的图片*/
    public void setRightImage(@DrawableRes int res){
        if (showRightView) {//判断文字是否显示
            if (isRightTextOrImageMode == MODE_IMAGE){
                ImageView imageView = (ImageView) rightView;
                imageView.setImageResource(res);
            }
        }
    }

    /**设置右边的图片*/
    public void setRightImage(Drawable drawable){
        if (showRightView) {//判断文字是否显示
            if (isRightTextOrImageMode == MODE_IMAGE){
                ImageView imageView = (ImageView) rightView;
                imageView.setImageDrawable(drawable);
            }
        }
    }

    /**设置左边的单击监听*/
    public void setOnLeftClickListener(OnLeftClickListener listener){
        if (leftView != null) {
            leftView.setOnClickListener(listener);
        }
    }

    /**设置右边的单击监听*/
    public void setOnRightClickListener(OnRightClickListener listener){
        if (rightView != null) {
            rightView.setOnClickListener(listener);
        }
    }
    //------------------------------------------
    //左边的监听接口
    public interface OnLeftClickListener extends OnClickListener{}
    //右边的监听接口
    public interface OnRightClickListener extends OnClickListener{}


}
