package com.podongfeng.justnote.util;

import android.database.Cursor;

public class EasyCursor {
	
	public static int getInt(Cursor cursor, String columnName) {
		return cursor.getInt(cursor.getColumnIndex(columnName));
	}
	
	public static String getString(Cursor cursor, String columnName) {
		return cursor.getString(cursor.getColumnIndex(columnName));
	}

}
