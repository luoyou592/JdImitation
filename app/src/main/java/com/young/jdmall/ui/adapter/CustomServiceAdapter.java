package com.young.jdmall.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.ChatMsgEntity;

import java.util.List;

/**
 * 消息ListView的Adapter
 */
public class CustomServiceAdapter extends BaseAdapter {

	private List<ChatMsgEntity> coll;// 消息对象数组
	private LayoutInflater mInflater;

	public CustomServiceAdapter(Context context, List<ChatMsgEntity> coll) {
		this.coll = coll;
		mInflater = LayoutInflater.from(context);
	}

	/*****************************************************/
	//得到Item的类型，是对方发过来的消息，还是自己发送出去的
	public int getItemViewType(int position) {
		return coll.get(position).getMsgType()?1:0;
	}

	public int getViewTypeCount() {
		return 2;
	}
	/******************************************************/
	public int getCount() {
		return coll.size();
	}

	public Object getItem(int position) {
		return coll.get(position);
	}

	public long getItemId(int position) {
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {

		ChatMsgEntity entity = coll.get(position);
		boolean isComMsg = entity.getMsgType();

		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (isComMsg) {
				convertView = mInflater.inflate(
						R.layout.item_msg_text_left, null);
				
			} else {
				convertView = mInflater.inflate(
						R.layout.item_msg_text_right, null);
				
			}

			viewHolder = new ViewHolder();
			viewHolder.tvSendTime = (TextView) convertView
					.findViewById(R.id.tv_sendtime);
			viewHolder.tvUserName = (TextView) convertView
					.findViewById(R.id.tv_username);
			viewHolder.tvContent = (TextView) convertView
					.findViewById(R.id.tv_chatcontent);
			viewHolder.isComMsg = isComMsg;

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvSendTime.setText(entity.getDate());
		viewHolder.tvUserName.setText(entity.getName());
		viewHolder.tvContent.setText(entity.getMessage());
		return convertView;
		
	}

	static class ViewHolder {
		public TextView tvSendTime;
		public TextView tvUserName;
		public TextView tvContent;
		public boolean isComMsg = true;
	}

}
