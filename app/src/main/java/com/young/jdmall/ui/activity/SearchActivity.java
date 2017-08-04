package com.young.jdmall.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.HotSearchInfoBean;
import com.young.jdmall.network.NetworkManage;
import com.young.jdmall.ui.view.FlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";

    @BindView(R.id.search_back)
    ImageView mSearchBack;
    @BindView(R.id.search_input)
    EditText mSearchInput;
    @BindView(R.id.search_clean)
    ImageView mSearchClean;
    @BindView(R.id.search_submit)
    TextView mSearchSubmit;
    @BindView(R.id.search_flow_layout)
    FlowLayout mSearchFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(0xAA000000);
        }

        initView();
        setListener();
        loadData();
    }

    private void loadData() {
        NetworkManage.getJDMallService().listHotSearch().enqueue(new Callback<HotSearchInfoBean>() {
            @Override
            public void onResponse(Call<HotSearchInfoBean> call, Response<HotSearchInfoBean> response) {
                HotSearchInfoBean body = response.body();
                loadDataToFlowLayout(body);
            }

            @Override
            public void onFailure(Call<HotSearchInfoBean> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void loadDataToFlowLayout(HotSearchInfoBean body) {
        List<String> searchKeywords = body.getSearchKeywords();

        for (int i = 0; i < 2; i++) {
            for (String searchKeyword : searchKeywords) {
                TextView textView = new TextView(this);
                textView.setText(searchKeyword);
                textView.setBackgroundResource(R.drawable.search_flow_item_gb);
                textView.setGravity(Gravity.CENTER);
                int padding2 = getResources().getDimensionPixelSize(R.dimen.dp_2);
                int padding10 = getResources().getDimensionPixelSize(R.dimen.dp_10);
                textView.setPadding(padding10, padding2, padding10, padding2);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                mSearchFlowLayout.addView(textView);
            }
        }
    }

    private void setListener() {
        mSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(mSearchInput.getText().toString())) {
                    mSearchClean.setEnabled(false);
                } else {
                    mSearchClean.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
        mSearchClean.setEnabled(false);
    }

    @OnClick({R.id.search_back, R.id.search_clean, R.id.search_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_clean:
                mSearchInput.setText("");
                break;
            case R.id.search_submit:
                break;
        }
    }
}
