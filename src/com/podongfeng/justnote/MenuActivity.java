package com.podongfeng.justnote;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.mogu.firstsweetdialog.R;
import com.podongfeng.justnote.adapter.NoteListAdapter;
import com.podongfeng.justnote.db.DbHandler;
import com.podongfeng.justnote.util.Const;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

public class MenuActivity extends Activity implements OnClickListener {
	
	private Button btnNewNote;
	private ListView lvMenu;
	
	private DbHandler dbHandler = null;
	
	List<Map<String, Object>> noteList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题  
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_menu);
		init_db();
		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		noteList = dbHandler.getNoteList();
		lvMenu.setAdapter(new NoteListAdapter(getApplicationContext(), noteList));
	}
	
	private void initView() {
		btnNewNote = (Button) findViewById(R.id.btn_new_note);
		btnNewNote.setOnClickListener(this);
		lvMenu = (ListView) findViewById(R.id.lv_menu);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), DetailActivity.class);
		startActivity(intent);
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
}
