package com.young.jdmall.ui.activity;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.ui.view.NewsDetailContainerGoodsView;
import com.young.jdmall.ui.view.NewsDetailContainerImageView;
import com.young.jdmall.ui.view.NewsDetailContainerTextView;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class NewsDetailActivity extends AppCompatActivity {

    private static final String TAG = "NewsDetailActivity";

    @BindView(R.id.news_detail_back)
    ImageView mNewsDetailBack;
    @BindView(R.id.news_detail_close)
    ImageView mNewsDetailClose;
    @BindView(R.id.news_detail_more)
    ImageView mNewsDetailMore;
    @BindView(R.id.news_detail_title)
    TextView mNewsDetailTitle;
    @BindView(R.id.news_detail_icon)
    ImageView mNewsDetailIcon;
    @BindView(R.id.news_detail_name)
    TextView mNewsDetailName;
    @BindView(R.id.news_detail_container)
    LinearLayout mNewsDetailContainer;
    @BindView(R.id.news_detail_icon1)
    ImageView mNewsDetailIcon1;
    @BindView(R.id.news_detail_name1)
    TextView mNewsDetailName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(0xAA000000);
        }
        initView();

        mNewsDetailTitle.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
        initData();
    }

    private void initData() {
        String icon = getIntent().getStringExtra("icon");
        String name = getIntent().getStringExtra("name");
        String title = getIntent().getStringExtra("title");
        String detail = getIntent().getStringExtra("detail");
        addContainer(detail);
        bindView(icon, name, title);
    }

    private void bindView(String icon, String name, String title) {
        Glide.with(this).load(icon).bitmapTransform(new CropCircleTransformation(this)).into(mNewsDetailIcon);
        Glide.with(this).load(icon).bitmapTransform(new CropCircleTransformation(this)).into(mNewsDetailIcon1);
        mNewsDetailName.setText(name);
        mNewsDetailName1.setText(name);
        mNewsDetailTitle.setText(title);
    }

    private void addContainer(String detail) {
        try {
            Document document = DocumentHelper.parseText(detail);
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                String name = element.getName();
                String stringValue = element.getStringValue();
                if ("text".equals(name)) {
                    NewsDetailContainerTextView containerTextView = new NewsDetailContainerTextView(this);
                    containerTextView.bindView(stringValue);
                    mNewsDetailContainer.addView(containerTextView);
                } else if ("image".equals(name)) {
                    NewsDetailContainerImageView containerImageView = new NewsDetailContainerImageView(this);
                    containerImageView.bindView(stringValue);
                    mNewsDetailContainer.addView(containerImageView);
                } else if ("goods".equals(name)) {
                    Iterator elementIterator = element.elementIterator();
                    String nae = "";
                    String icon = "";
                    String price = "";
                    String id = "";
                    while (elementIterator.hasNext()) {
                        Element next = (Element) elementIterator.next();
                        String nextName = next.getName();
                        String nextStringValue = next.getStringValue();
                        if ("name".equals(nextName)) {
                            nae = nextStringValue;
                        } else if ("icon".equals(nextName)) {
                            icon = nextStringValue;
                        } else if ("price".equals(nextName)) {
                            price = nextStringValue;
                        } else if ("id".equals(nextName)) {
                            id = nextStringValue;
                        }
                        Log.d(TAG, "addContainer: " + next.getName());
                    }
                    NewsDetailContainerGoodsView containerGoodsView = new NewsDetailContainerGoodsView(this);
                    containerGoodsView.bindView(nae, icon, price, id);
                    mNewsDetailContainer.addView(containerGoodsView);
                }


                Log.d(TAG, "addContainer: " + name + "|" + stringValue);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void initView() {

    }

    @OnClick({R.id.news_detail_back, R.id.news_detail_close, R.id.news_detail_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.news_detail_back:
                break;
            case R.id.news_detail_close:
                finish();
                break;
            case R.id.news_detail_more:
                showPopupWindow();
                break;
        }
    }

    private void showPopupWindow() {
        Toast.makeText(this, "点击了更多", Toast.LENGTH_SHORT).show();
        View popupWindowView = View.inflate(this, R.layout.layout_popup_window, null);
        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(popupWindowView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(mNewsDetailMore);
    }
}
