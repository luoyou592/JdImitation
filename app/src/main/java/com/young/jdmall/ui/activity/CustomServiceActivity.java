package com.young.jdmall.ui.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.ChatMsgEntity;
import com.young.jdmall.ui.adapter.CustomServiceAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 *  创建者:   royal
 *  创建时间:  2017/7/30 0030 21:35
 *  描述：    客服模拟聊天界面
 */

public class CustomServiceActivity extends Activity implements OnClickListener{
	private ImageView backButton;
	private TextView titleView;
	private Button mBtnSend;// 发送btn
    private EditText mEditTextContent;  
    private ListView mListView;  
    private CustomServiceAdapter mAdapter;//
    private InputMethodManager mInputMethodManager;
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组
    
    private final static int COUNT = 1;// 初始化数组总数
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        
        setTheme(R.style.MyTheme);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_imitate_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_title);
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initView();

    }  
      
    public void initView() {  
    	backButton=(ImageView)findViewById(R.id.imageButtonback);
    	titleView=(TextView)findViewById(R.id.textViewtitle);
        mListView = (ListView) findViewById(R.id.listview4_3);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
        initData();// 初始化数据


        mBtnSend.setOnClickListener(this);  
        mListView.setSelection(mAdapter.getCount() - 1);
        backButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                KeyEvent backEvent=new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK);
                onKeyDown(KeyEvent.KEYCODE_BACK, backEvent);
            }
        });
    }


    /**
     * 模拟加载消息历史，实际开发可以从数据库中读出
     */
    public void initData() {  
        for (int i = 0; i < COUNT; i++) {  
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate("2012-09-22 18:00:02");  
            if (i % 2 == 0) {  
                entity.setName("客服");
                entity.setMsgType(true);// 收到的消息
            } else {  
                entity.setName("本人");
                entity.setMsgType(false);// 自己发送的消息
            }  
            entity.setMessage("请问有什么服务吗？");
            mDataArrays.add(entity);  
        }  
  
        mAdapter = new CustomServiceAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);
    }  
  
    @Override  
    public void onClick(View v) {
        send();
        if (mInputMethodManager.isActive()) {
            //隐藏输入法
            mInputMethodManager.hideSoftInputFromWindow(mEditTextContent.getWindowToken(), 0);
        }
    }

    /**
     * 发送消息时，获取当前事件
     *
     * @return 当前时间
     */
    private String getDate() {  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        return format.format(new Date());    
    }

    /**
     * 发送消息
     */
    private void send() {  
        String contString = mEditTextContent.getText().toString();  
        if (contString.length() > 0) {  
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setName("本人");
            entity.setDate(getDate());  
            entity.setMessage(contString);  
            entity.setMsgType(false);  
  
            mDataArrays.add(entity);  
            mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变
  
            mEditTextContent.setText("");// 清空编辑框数据
  
            mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
        }  

	        
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
			Builder builder=new Builder(CustomServiceActivity.this);
            builder.setTitle("提示");
            builder.setMessage("是否退出");
            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					finish();
				}
			});
            builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
				
				}
			});
            builder.create().show();
        }
		return super.onKeyDown(keyCode, event);
	}
}
