package com.young.jdmall.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
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
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.HotSearchInfoBean;
import com.young.jdmall.network.NetworkManage;
import com.young.jdmall.ui.utils.PreferenceUtils;
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
    @BindView(R.id.search_linear)
    LinearLayout mSearchLinear;
    @BindView(R.id.search_clean_button)
    LinearLayout mSearchCleanButton;
    @BindView(R.id.type_1)
    LinearLayout mType1;
    @BindView(R.id.type_2)
    RelativeLayout mType2;
    @BindView(R.id.search_linear_history)
    LinearLayout mSearchLinearHistory;

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

    @Override
    protected void onStart() {
        super.onStart();
        List<String> searchList = PreferenceUtils.getSearchList(this);
        if (searchList != null) {
            loadDataToTypeSearchList(searchList);
            mType2.setVisibility(View.VISIBLE);
            mType1.setVisibility(View.GONE);
        } else {
            mType1.setVisibility(View.VISIBLE);
            mType2.setVisibility(View.GONE);
        }
    }

    private void loadData() {
        NetworkManage.getJDMallService().listHotSearch().enqueue(new Callback<HotSearchInfoBean>() {
            @Override
            public void onResponse(Call<HotSearchInfoBean> call, Response<HotSearchInfoBean> response) {
                HotSearchInfoBean body = response.body();
                loadDataToFlowLayout(body);
                loadDataToLinearLayout(body);
            }

            @Override
            public void onFailure(Call<HotSearchInfoBean> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void loadDataToLinearLayout(HotSearchInfoBean body) {
        List<String> searchKeywords = body.getSearchKeywords();

        for (int i = 0; i < 1; i++) {
            for (final String searchKeyword : searchKeywords) {
                TextView textView = new TextView(this);
                textView.setText(searchKeyword);
                textView.setBackgroundResource(R.drawable.search_flow_item_gb);
                textView.setGravity(Gravity.CENTER);
                int padding2 = getResources().getDimensionPixelSize(R.dimen.dp_2);
                int padding10 = getResources().getDimensionPixelSize(R.dimen.dp_10);
                textView.setPadding(padding10, padding2, padding10, padding2);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(searchKeyword);
                    }
                });
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int margin5 = getResources().getDimensionPixelSize(R.dimen.dp_5);
                layoutParams.setMargins(margin5, 0, margin5, 0);
                mSearchLinear.addView(textView, layoutParams);
            }
        }
    }

    private void loadDataToFlowLayout(HotSearchInfoBean body) {
        List<String> searchKeywords = body.getSearchKeywords();

        for (int i = 0; i < 2; i++) {
            for (final String searchKeyword : searchKeywords) {
                TextView textView = new TextView(this);
                textView.setText(searchKeyword);
                textView.setBackgroundResource(R.drawable.search_flow_item_gb);
                textView.setGravity(Gravity.CENTER);
                int padding2 = getResources().getDimensionPixelSize(R.dimen.dp_2);
                int padding10 = getResources().getDimensionPixelSize(R.dimen.dp_10);
                textView.setPadding(padding10, padding2, padding10, padding2);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(searchKeyword);
                    }
                });
                mSearchFlowLayout.addView(textView);
            }
        }
    }


    private void loadDataToTypeSearchList(List<String> searchList) {
        int padding = getResources().getDimensionPixelSize(R.dimen.dp_14);
        mSearchLinearHistory.removeAllViews();
        for (int i = 0; i < searchList.size(); i++) {
            if (i != 0) {
                View view = new View(this);
                view.setBackgroundColor(0xFFEEEEEE);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
                layoutParams.setMargins(padding, 0, 0, 0);
                mSearchLinearHistory.addView(view, layoutParams);
            }
            final String s = searchList.get(i);
            TextView textView = new TextView(this);
            textView.setText(s);
            textView.setPadding(padding, padding, padding, padding);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(s);
                }
            });
            mSearchLinearHistory.addView(textView);
        }
    }

    private void startActivity(String searchKeyword) {
        Intent intent = new Intent(SearchActivity.this, TypeActivity.class);
        intent.putExtra("name", searchKeyword);
        startActivity(intent);
        PreferenceUtils.addSearchList(SearchActivity.this, searchKeyword);
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

    private void showIsCleanDialog() {
        View view = View.inflate(this, R.layout.layout_serch_dialog, null);
        View confirm = view.findViewById(R.id.confirm);
        View abolish = view.findViewById(R.id.abolish);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.show();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.deleteSearchList(SearchActivity.this);
                mType1.setVisibility(View.VISIBLE);
                mType2.setVisibility(View.GONE);
                alertDialog.dismiss();
            }
        });
        abolish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(view);
//        dialog.show();

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.

    }

    @OnClick({R.id.search_back, R.id.search_clean, R.id.search_submit, R.id.search_clean_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_clean:
                mSearchInput.setText("");
                break;
            case R.id.search_submit:
                startActivity(TextUtils.isEmpty(mSearchInput.getText().toString().trim()) ? "8.4帮宝适超级新品日" : mSearchInput.getText().toString().trim());
                break;
            case R.id.search_clean_button:
                showIsCleanDialog();
                break;
        }
    }
}
