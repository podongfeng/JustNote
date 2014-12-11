package com.podongfeng.justnote;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.mogu.firstsweetdialog.R;
import com.podongfeng.justnote.db.DbHandler;
import com.podongfeng.justnote.util.Const;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends Activity implements OnClickListener {

	private Intent intent;

	private DbHandler dbHandler = null;

	private Button btnSave;
	private EditText etDetail;

	private Integer id = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detail);
		init_db();
		initView();
	}

	private void init_db() {
		try {
			String packageName = this.getPackageName();
			InputStream inputStream = getAssets().open(Const.DB_NAME);
			dbHandler = new DbHandler(packageName, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initView() {
		btnSave = (Button) findViewById(R.id.btn_save);
		etDetail = (EditText) findViewById(R.id.et_detail);
		btnSave.setOnClickListener(this);
		intent = getIntent();
		id = intent.getIntExtra("id", -1);
		if (id > -1) {
			String content = dbHandler.getContentById(id);
			if (content != null) {
				etDetail.setText(content);
				etDetail.setSelection(content.length());
			}
		}
	}

	@Override
	public void onClick(View v) {
		Map<String, String> map = new HashMap<>();
		map.put("content", etDetail.getText().toString());
		if (id > -1) {
			map.put("id", String.valueOf(id));
		}
		dbHandler.saveNote(map);
		finish();
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK    
                && event.getAction() == KeyEvent.ACTION_DOWN){
			finish();
		}
		return super.dispatchKeyEvent(event);
	}
}
