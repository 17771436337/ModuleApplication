package cai.project.module.common_view.animation.snow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.Random;

import cai.project.module.common_utils.codeutils.ImageUtils;

public class FallObject {
    private int initX;
    private int initY;
    private Random random;
    private int parentWidth;//父容器宽度
    private int parentHeight;//父容器高度
    private float objectWidth;//下落物体宽度
    private float objectHeight;//下落物体高度

    public float presentX;//当前位置X坐标
    public float presentY;//当前位置Y坐标
    public float presentSpeed;//当前下降速度

    private Bitmap bitmap;
    public Builder builder;


    private boolean isSpeedRandom;//物体初始下降速度比例是否随机
    private boolean isSizeRandom;//物体初始大小比例是否随机

    private static final int defaultSpeed = 10;//默认下降速度

    public int initSpeed;//初始下降速度
    public int initWindLevel;//初始风力等级

    private float angle;//物体下落角度

    private boolean isWindRandom;//物体初始风向和风力大小比例是否随机
    private boolean isWindChange;//物体下落过程中风向和风力是否产生随机变化

    private static final int defaultWindLevel = 0;//默认风力等级

    private static final int defaultWindSpeed = 10;//默认单位风速

    private static final float HALF_PI = (float) Math.PI / 2;//π/2


    public FallObject(Builder builder, int parentWidth, int parentHeight){
        random = new Random();
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        initX = random.nextInt(parentWidth);//随机物体的X坐标
        initY = random.nextInt(parentHeight)- parentHeight;//随机物体的Y坐标，并让物体一开始从屏幕顶部下落
        presentX = initX;
        presentY = initY;

        initSpeed = builder.initSpeed;

        presentSpeed = initSpeed;
        bitmap = builder.bitmap;
        objectWidth = bitmap.getWidth();
        objectHeight = bitmap.getHeight();


        this.builder = builder;
        isSpeedRandom = builder.isSpeedRandom;
        isSizeRandom = builder.isSizeRandom;

        isWindRandom = builder.isWindRandom;
        isWindChange = builder.isWindChange;
        initSpeed = builder.initSpeed;
        initWindLevel = builder.initWindLevel;
        randomSpeed();
        randomSize();
        randomWind();
    }

    private FallObject(Builder builder) {
        this.builder = builder;
        initSpeed = builder.initSpeed;
        bitmap = builder.bitmap;

        //------------------
        isSpeedRandom = builder.isSpeedRandom;
        isSizeRandom = builder.isSizeRandom;

        //--------------
        isWindRandom = builder.isWindRandom;
        isWindChange = builder.isWindChange;
        initWindLevel = builder.initWindLevel;
    }

    public static final class Builder {
        private int initSpeed;
        private Bitmap bitmap;

        private boolean isSpeedRandom;
        private boolean isSizeRandom;

        //-----------------
        private boolean isWindRandom;
        private boolean isWindChange;
        private int initWindLevel;

        public Builder(Bitmap bitmap) {
            this.initSpeed = defaultSpeed;
            this.bitmap = bitmap;
            this.isSpeedRandom = false;
            this.isSizeRandom = false;

            this.isWindRandom = false;
            this.isWindChange = false;
        }

        public Builder(Drawable drawable) {
            this.initSpeed = defaultSpeed;
            this.bitmap = ImageUtils.drawable2Bitmap(drawable);
            this.isSpeedRandom = false;
            this.isSizeRandom = false;

            this.isWindRandom = false;
            this.isWindChange = false;
        }

        /**
         * 设置风力等级、方向以及随机因素
         * @param level 风力等级（绝对值为 5 时效果会比较好），为正时风从左向右吹（物体向X轴正方向偏移），为负时则相反
         * @param isWindRandom 物体初始风向和风力大小比例是否随机
         * @param isWindChange 在物体下落过程中风的风向和风力是否会产生随机变化
         * @return
         */
        public Builder setWind(int level,boolean isWindRandom,boolean isWindChange){
            this.initWindLevel = level;
            this.isWindRandom = isWindRandom;
            this.isWindChange = isWindChange;
            return this;
        }

        /**
         * 设置物体的初始下落速度
         * @param speed
         * @return
         */
        public Builder setSpeed(int speed) {
            this.initSpeed = speed;
            return this;
        }

        /**
         * 设置物体的初始下落速度
         * @param speed
         * @param isRandomSpeed 物体初始下降速度比例是否随机
         * @return
         */
        public Builder setSpeed(int speed,boolean isRandomSpeed) {
            this.initSpeed = speed;
            this.isSpeedRandom = isRandomSpeed;
            return this;
        }



        /**
         * 设置物体大小
         * @param w
         * @param h
         * @return
         */
        public Builder setSize(int w, int h){
            this.bitmap = ImageUtils.scale(this.bitmap,w,h);
            return this;
        }

        /**
         * 设置物体大小
         * @param w
         * @param h
         * @param isRandomSize 物体初始大小比例是否随机
         * @return
         */
        public Builder setSize(int w, int h, boolean isRandomSize){
            this.bitmap = ImageUtils.scale(this.bitmap,w,h);
            this.isSizeRandom = isRandomSize;
            return this;
        }


        public FallObject build() {
            return new FallObject(this);
        }

    }




    /**
     * 绘制物体对象
     * @param canvas
     */
    public void drawObject(Canvas canvas){
        moveObject();
        canvas.drawBitmap(bitmap,presentX,presentY,null);
    }

    /**
     * 移动物体对象
     */
    private void moveObject(){
        moveX();
        moveY();
        if(presentY>parentHeight){
            reset();
        }
    }

    /**
     * Y轴上的移动逻辑
     */
    private void moveY(){
        presentY += presentSpeed;
    }


    /**
     * X轴上的移动逻辑
     */
    private void moveX(){
        presentX += defaultWindSpeed * Math.sin(angle);
        if(isWindChange){
            angle += (float) (random.nextBoolean()?-1:1) * Math.random() * 0.0025;
        }
    }



    /**
     * 随机风的风向和风力大小比例，即随机物体初始下落角度
     */
    private void randomWind(){
        if(isWindRandom){
            angle = (float) ((random.nextBoolean()?-1:1) * Math.random() * initWindLevel /50);
        }else {
            angle = (float) initWindLevel /50;
        }

        //限制angle的最大最小值
        if(angle>HALF_PI){
            angle = HALF_PI;
        }else if(angle<-HALF_PI){
            angle = -HALF_PI;
        }
    }

    /**
     * 重置object位置
     */
    private void reset(){
        presentY = -objectHeight;
        presentSpeed = initSpeed;

   //雪花的数量就会保持不变
        presentX = random.nextInt(parentWidth);
    }


    /**
     * 随机物体初始下落速度
     */
    private void randomSpeed(){
        if(isSpeedRandom){
            presentSpeed = (float)((random.nextInt(3)+1)*0.1+1)* initSpeed;//这些随机数大家可以按自己的需要进行调整
        }else {
            presentSpeed = initSpeed;
        }
    }

    /**
     * 随机物体初始大小比例
     */
    private void randomSize(){
        if(isSizeRandom){
            float r = (random.nextInt(10)+1)*0.1f;
            float rW = r * builder.bitmap.getWidth();
            float rH = r * builder.bitmap.getHeight();
            bitmap = ImageUtils.scale(builder.bitmap,(int)rW,(int)rH);
        }else {
            bitmap = builder.bitmap;
        }
        objectWidth = bitmap.getWidth();
        objectHeight = bitmap.getHeight();
    }
}
