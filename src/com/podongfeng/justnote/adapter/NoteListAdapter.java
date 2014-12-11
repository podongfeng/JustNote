package com.podongfeng.justnote.adapter;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.mogu.firstsweetdialog.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class NoteListAdapter extends BaseAdapter {
	
	private List<Map<String, Object>> list;
	
	private LayoutInflater mInflater;
	
	public NoteListAdapter(Context context, List<Map<String, Object>> list) {
		this.mInflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null) {
			holder=new ViewHolder();
			convertView = mInflater.inflate(R.layout.note_item, null);
			holder.tv_item_content = (TextView) convertView.findViewById(R.id.tv_item_content);
			holder.tv_item_update_time = (TextView) convertView.findViewById(R.id.tv_item_update_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		holder.tv_item_content.setText(String.valueOf(list.get(position).get("content")));
		Integer updateTime = Integer.parseInt(String.valueOf(list.get(position).get("update_time")));
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(updateTime*1000L);
		StringBuffer updateTimeBuffer = new StringBuffer();
		updateTimeBuffer.append(calendar.get(Calendar.YEAR)).append("Äê");
		updateTimeBuffer.append(calendar.get(Calendar.MONTH)+1).append("ÔÂ");
		updateTimeBuffer.append(calendar.get(Calendar.DATE)).append("ÈÕ");
		updateTimeBuffer.append(" ");
		updateTimeBuffer.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":").append(calendar.get(Calendar.MINUTE));
		holder.tv_item_update_time.setText(String.valueOf(list.get(position).get("age")));
		holder.tv_item_update_time.setText(updateTimeBuffer.toString());
		return convertView;
	}
	
	class ViewHolder {
		TextView tv_item_content;
		TextView tv_item_update_time;
	}

}
