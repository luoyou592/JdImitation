package com.young.jdmall.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.CategoryBaseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/30.
 * 包名:com.example.administrator.jdx.View
 * 时间:2017/7/30
 */

public class CategoryBaseRightItemView extends LinearLayout {

    private static final String TAG = "BaseItemView";

    @BindView(R.id.view_base_title)
    TextView mViewBaseTitle;
    @BindView(R.id.table_layout)
    TableLayout mTableLayout;

    public CategoryBaseRightItemView(Context context) {
        this(context, null);
    }

    public CategoryBaseRightItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View root = LayoutInflater.from(getContext()).inflate(R.layout.item_test, this);
        ButterKnife.bind(this, this);

    }

    public void bind(int position, List<CategoryBaseBean> dataList) {
        mViewBaseTitle.setText(dataList.get(position).getTitle());


        mTableLayout.removeAllViews();
        int tableRowWidth = getResources().getDisplayMetrics().widthPixels - mTableLayout.getPaddingLeft() - mTableLayout.getPaddingRight();

        int infoItemWidth = tableRowWidth / 4;
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(infoItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

        List<CategoryBaseBean.CategoryBean> category = dataList.get(position).getCategory();


        //遍历数据
        for (int i = 0; i < category.size(); i++) {
            CategoryBaseBean.CategoryBean categoryBean = category.get(i);

            //创建一行
            TableRow tableRow = new TableRow(getContext());

            //添加一个
            CategoryBaseRightItemInfoView baseItemInfoView1 = new CategoryBaseRightItemInfoView(getContext());
            baseItemInfoView1.bindView(categoryBean);
            baseItemInfoView1.setLayoutParams(layoutParams);
            tableRow.addView(baseItemInfoView1);

            //添加一个
            CategoryBaseRightItemInfoView baseItemInfoView2 = new CategoryBaseRightItemInfoView(getContext());
            baseItemInfoView2.bindView(categoryBean);
            baseItemInfoView2.setLayoutParams(layoutParams);
            tableRow.addView(baseItemInfoView2);

            //添加一个
            CategoryBaseRightItemInfoView baseItemInfoView3 = new CategoryBaseRightItemInfoView(getContext());
            baseItemInfoView3.bindView(categoryBean);
            baseItemInfoView3.setLayoutParams(layoutParams);
            tableRow.addView(baseItemInfoView3);

            mTableLayout.addView(tableRow);
        }

    }
}
