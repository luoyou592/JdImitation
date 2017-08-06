package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.young.jdmall.R;
import com.young.jdmall.bean.RecommendGoodsBean;
import com.young.jdmall.ui.widget.SexGirlPagerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * item页底部推荐商品适配器
 */
public class ItemRecommendGoodsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<RecommendGoodsBean> data;
    public List<String> imgUrls = new ArrayList<>();

    public ItemRecommendGoodsAdapter(Context context, List<RecommendGoodsBean> data) {
        this.context = context;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501748584716&di=16da50a0f9934d77462d67f317dce3ef&imgtype=0&src=http%3A%2F%2Fdynamic-image.yesky.com%2F740x-%2FuploadImages%2F2015%2F159%2F20%2FHV9K66V8I35T.jpg");
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502552743&di=9d04a0d2ddd8c8b1dc7e2c8aa37b9e89&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F160328%2F16-16032Q63140.jpg");
        imgUrls.add("https://img.alicdn.com/bao/uploaded/i1/TB1TsjkSpXXXXabaFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
        imgUrls.add("https://img.alicdn.com/bao/uploaded/i2/TB16b6pSpXXXXcgXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
        imgUrls.add("https://img.alicdn.com/bao/uploaded/i4/TB17i22RFXXXXXcXpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
        imgUrls.add("https://img.alicdn.com/bao/uploaded/i3/TB1U6wjSXXXXXbZXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
    }

    public void setData(List<RecommendGoodsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<RecommendGoodsBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<RecommendGoodsBean> getData() {
        return this.data;
    }

    public void clearData() {
        this.data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_recommend_goods_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RecommendGoodsBean recommendGoods = data.get(position);
        holder.tv_goods_name.setText(recommendGoods.getTitle());
        holder.tv_goods_price.setText("￥" + recommendGoods.getCurrentPrice());
        holder.sdv_goods.setImageURI(Uri.parse(recommendGoods.getImag()));
        holder.tv_goods_old_price.setText("￥" + recommendGoods.getPrice());
        return convertView;
    }

    class ViewHolder {
        private SimpleDraweeView sdv_goods;
        private TextView tv_goods_name, tv_goods_price, tv_goods_old_price;

        public ViewHolder(View convertView) {
            sdv_goods = (SimpleDraweeView) convertView.findViewById(R.id.sdv_goods);
            tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
            tv_goods_price = (TextView) convertView.findViewById(R.id.tv_goods_price);
            tv_goods_old_price = (TextView) convertView.findViewById(R.id.tv_goods_old_price);
            tv_goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SexGirlPagerDialog girlPagerDialog = new SexGirlPagerDialog(context,R.style.Dialog);
                    girlPagerDialog.setData(imgUrls,0);

                    girlPagerDialog.show();
                }
            });
        }
    }
}
