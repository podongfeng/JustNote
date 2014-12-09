package com.mogu.firstsweetdialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends Activity implements AnimationListener {
	
	private TextView tv_splash;
	private Animation alphaAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 // 设置无标题  
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        // 设置全屏  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        setContentView(R.layout.activity_splash);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//			
//			@Override
//			public void run() {
//				Intent intent = new Intent();
//				intent.setClass(getApplicationContext(), MainActivity.class);
//				startActivity(intent);
//				finish();
//			}
//		}, 3000);
        tv_splash = (TextView)findViewById(R.id.tv_splash);
        alphaAnimation =  AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_anim);
        alphaAnimation.setFillEnabled(true); //启动Fill保持  
        alphaAnimation.setFillAfter(true);  //设置动画的最后一帧是保持在View上面
        tv_splash.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(this);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

}
