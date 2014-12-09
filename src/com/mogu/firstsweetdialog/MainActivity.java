package com.mogu.firstsweetdialog;

import com.mogu.firstsweetdialog.sweet.SweetAlertDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btn_basic, btn_text, btn_error, btn_warn, btn_success;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		// 基本dialog 
		btn_basic = (Button) findViewById(R.id.btn_basic);
		btn_basic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new SweetAlertDialog(MainActivity.this)
			    .setTitleText("Here's a message!")
			    .show();
			}
		});
		
		// 标题内容弹框
		btn_text = (Button) findViewById(R.id.btn_text);
		btn_text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new SweetAlertDialog(MainActivity.this)
			    .setTitleText("Here's a message!")
			    .setContentText("It's pretty, isn't it?")
			    .show();
			}
		});
		
		// 错误信息弹框
		btn_error = (Button) findViewById(R.id.btn_error);
		btn_error.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
			    .setTitleText("Oops...")
			    .setContentText("Something went wrong!")
			    .show();
			}
		});
		
		// 警告信息弹框
		btn_warn = (Button) findViewById(R.id.btn_warn);
		btn_warn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
			    .setTitleText("Are you sure?")
			    .setContentText("Won't be able to recover this file!")
			    .setConfirmText("Yes,delete it!")
			    .show();
			}
		});
		
		// 成功信息弹框
		btn_success = (Button) findViewById(R.id.btn_success);
		btn_success.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
			    .setTitleText("Good job!")
			    .setContentText("You clicked the button!")
			    .show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
