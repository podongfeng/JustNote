package com.podongfeng.justnote.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.podongfeng.justnote.util.EasyCursor;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库操作类
 * 
 * @author mogu
 * 
 */
public class DbHandler {

	private String packageName;

	private InputStream inputStream;

	private SQLiteDatabase sqliteDB;

	public DbHandler(String packageName, InputStream inputStream) {
		this.packageName = packageName;
		this.inputStream = inputStream;
		init();
	}

	public void init() {
		if (!new File("/data/data/" + packageName + "/database.sqlite").exists()) {
			try {
				FileOutputStream out = new FileOutputStream("data/data/" + packageName + "/database.sqlite");

				byte[] buffer = new byte[1024];
				int readBytes = 0;

				while ((readBytes = inputStream.read(buffer)) != -1)
					out.write(buffer, 0, readBytes);

				inputStream.close();
				out.close();
			} catch (IOException e) {
			}
		}

		sqliteDB = SQLiteDatabase.openOrCreateDatabase("/data/data/" + packageName + "/database.sqlite", null);
	}

	public List<Map<String, Object>> getUserList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Cursor cursor = sqliteDB.rawQuery("select * from user", null);
		System.out.println("resultSize:" + cursor.getCount());
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", EasyCursor.getInt(cursor, "id"));
			map.put("name", EasyCursor.getString(cursor, "name"));
			list.add(map);
		}
		return list;
	}

	public void saveNote(Map<String, String> map) {
		if (map.containsKey("id")) {

		}
		String content = String.valueOf(map.get("content"));
		Calendar calendar = Calendar.getInstance();
		int currentTimeInt = (int) (calendar.getTimeInMillis() / 1000);
		String currentTimeStr = String.valueOf(currentTimeInt);
		StringBuffer updateMonthBuffer = new StringBuffer(calendar.get(Calendar.YEAR)).append(calendar
				.get(Calendar.MONTH + 1));
		String updateMonthStr = updateMonthBuffer.toString();
		if (map.containsKey("id")) {
			String id = String.valueOf(map.get("id"));
			ContentValues cv = new ContentValues();
			cv.put("content", content);
			cv.put("update_time", currentTimeStr);
			cv.put("update_month", updateMonthStr);
			sqliteDB.update("note", cv, "id=", new String[] { id });
		} else {
			ContentValues cv = new ContentValues();
			cv.put("content", content);
			cv.put("create_time", currentTimeStr);
			cv.put("update_time", currentTimeStr);
			cv.put("update_month", updateMonthStr);
			sqliteDB.insert("note", null, cv);
		}
	}
	
	public List<Map<String, Object>> getNoteList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Cursor cursor = sqliteDB.rawQuery("select * from note order by update_time", null);
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", EasyCursor.getInt(cursor, "id"));
			map.put("content", EasyCursor.getString(cursor, "content"));
			map.put("update_time", EasyCursor.getInt(cursor, "update_time"));
			list.add(map);
		}
		return list;
	}

}
