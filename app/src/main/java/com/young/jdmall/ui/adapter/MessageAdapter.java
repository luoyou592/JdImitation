package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.young.jdmall.R;

/**
 * 作者：Administrator
 * 时间：2017/7/31
 * 包名：com.young.jdmall.ui.adapter
 * 公司：黑马程序员
 */

public class MessageAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    public static final int TYPE_TIME = 0;
    public static final int TYPE_NORMAL = 1;

    public MessageAdapter(Context context) {
        mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_TIME){
            //View view=View.inflate(mContext,R.layout.message_item_time,null);
            View view = LayoutInflater.from(mContext).inflate(R.layout.message_item_time, parent, false);
            return new TimeHolder(view);
        }else {
            View view = View.inflate(mContext, R.layout.message_item, null);
            return new NormalHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String time=null;
        int d=0;
        String title=null;
          switch (position){
              case 0:
                  time="11:02";
                  break;
              case 1:
                  d= R.mipmap.u1;
                  title="京东更疯狂";
                  break;
              case 2:
                  time="昨天10:05";
                  break;
              case 3:
                  d= R.mipmap.u2;
                  title="你的专属定制";
                  break;
              case 4:
                  time="昨天7:02";
                  break;
              case 5:
                  d= R.mipmap.u3;
                  title="京东周年庆";
                  break;
              case 6:
                  time="2017年7月29日";
                  break;
              case 7:
                  d= R.mipmap.u4;
                  title="京东超市特惠";
                  break;
          }
          if(time!=null){
              ((TimeHolder)holder).setData(time);
          }
          if(d!=0&&title!=null){
              ((NormalHolder)holder).setData(d,title);
          }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return TYPE_TIME;
        }else{
            return TYPE_NORMAL;
        }
    }

    private class NormalHolder extends RecyclerView.ViewHolder{

        private View mView;

        public NormalHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setData(int d,String title){
            mView.findViewById(R.id.message_iv).setBackgroundResource(d);
            ((TextView)mView.findViewById(R.id.message_title)).setText(title);
        }
    }

    private class TimeHolder extends RecyclerView.ViewHolder {
        private  View mView;

        public TimeHolder(View view) {
            super(view);
            mView=view;
        }
        public void setData(String time){
            ((TextView)mView.findViewById(R.id.tv_time)).setText(time);
        }
    }
}
