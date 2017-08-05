package com.young.jdmall.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.gxz.PagerSlidingTabStrip;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.CommentInfoBean;
import com.young.jdmall.bean.ProductInfoBean;
import com.young.jdmall.bean.RecommendGoodsBean;
import com.young.jdmall.ui.activity.ProductDetaiActivity;
import com.young.jdmall.ui.adapter.ItemRecommendAdapter;
import com.young.jdmall.ui.adapter.NetworkImageHolderView;
import com.young.jdmall.ui.utils.PriceFormater;
import com.young.jdmall.ui.widget.CommentView;
import com.young.jdmall.ui.widget.DialogConfirmView;
import com.young.jdmall.ui.widget.SlideDetailsLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * item页ViewPager里的商品Fragment
 */
public class GoodsInfoFragment extends BaseFragment implements View.OnClickListener, SlideDetailsLayout.OnSlideDetailsListener {
    private PagerSlidingTabStrip psts_tabs;
    private SlideDetailsLayout sv_switch;
    private ScrollView sv_goods_info;
    private FloatingActionButton fab_up_slide;
    public ConvenientBanner vp_item_goods_img, vp_recommend;
    private LinearLayout ll_goods_detail, ll_goods_config;
    private TextView tv_goods_detail, tv_goods_config;
    private View v_tab_cursor;
    public FrameLayout fl_content;
    public LinearLayout ll_current_goods, ll_activity, ll_comment, ll_recommend, ll_pull_up, ll_info_comment;
    public TextView tv_no_comment, tv_goods_title, tv_new_price, tv_old_price, tv_current_goods, tv_comment_count, tv_good_comment;
    public List<String> imgUrls = new ArrayList<>();

    /**
     * 当前商品详情数据页的索引分别是图文详情、规格参数
     */
    private int nowIndex;
    private float fromX;
    public GoodsConfigFragment goodsConfigFragment;
    public GoodsInfoWebFragment goodsInfoWebFragment;
    private Fragment nowFragment;
    private List<TextView> tabTextList;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    public ProductDetaiActivity activity;
    private LayoutInflater inflater;
    private CommentView mCommentView;
    private ProductDetaiActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ProductDetaiActivity) context;
    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        Log.d("luoyou", "fragmentonResume");
        //开始自动翻页
        vp_item_goods_img.startTurning(4000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        vp_item_goods_img.stopTurning();
    }
    public void setSelectedGood(String text){
        tv_current_goods.setText(text);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        View rootView = inflater.inflate(R.layout.fragment_goods_info, null);
        mActivity = (ProductDetaiActivity) getActivity();
        initView(rootView);
        initListener();
        initData();
        return rootView;
    }

    private void initListener() {
        fab_up_slide.setOnClickListener(this);
        ll_current_goods.setOnClickListener(this);
        ll_activity.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
        ll_pull_up.setOnClickListener(this);
        ll_goods_detail.setOnClickListener(this);
        ll_goods_config.setOnClickListener(this);
        sv_switch.setOnSlideDetailsListener(this);
    }

    private void initView(View rootView) {
        psts_tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.psts_tabs);
        fab_up_slide = (FloatingActionButton) rootView.findViewById(R.id.fab_up_slide);
        sv_switch = (SlideDetailsLayout) rootView.findViewById(R.id.sv_switch);
        sv_goods_info = (ScrollView) rootView.findViewById(R.id.sv_goods_info);
        v_tab_cursor = rootView.findViewById(R.id.v_tab_cursor);
        vp_item_goods_img = (ConvenientBanner) rootView.findViewById(R.id.vp_item_goods_img);
        vp_recommend = (ConvenientBanner) rootView.findViewById(R.id.vp_recommend);
        fl_content = (FrameLayout) rootView.findViewById(R.id.fl_content);
        ll_current_goods = (LinearLayout) rootView.findViewById(R.id.ll_current_goods);
        ll_activity = (LinearLayout) rootView.findViewById(R.id.ll_activity);
        ll_comment = (LinearLayout) rootView.findViewById(R.id.ll_comment);
        ll_recommend = (LinearLayout) rootView.findViewById(R.id.ll_recommend);
        ll_pull_up = (LinearLayout) rootView.findViewById(R.id.ll_pull_up);
        ll_info_comment = (LinearLayout) rootView.findViewById(R.id.ll_info_comment);
        ll_goods_detail = (LinearLayout) rootView.findViewById(R.id.ll_goods_detail);
        ll_goods_config = (LinearLayout) rootView.findViewById(R.id.ll_goods_config);
        tv_goods_detail = (TextView) rootView.findViewById(R.id.tv_goods_detail);
        tv_goods_config = (TextView) rootView.findViewById(R.id.tv_goods_config);
        tv_goods_title = (TextView) rootView.findViewById(R.id.tv_goods_title);
        tv_new_price = (TextView) rootView.findViewById(R.id.tv_new_price);
        tv_old_price = (TextView) rootView.findViewById(R.id.tv_old_price);
        tv_current_goods = (TextView) rootView.findViewById(R.id.tv_current_goods);
        tv_comment_count = (TextView) rootView.findViewById(R.id.tv_comment_count);
        tv_good_comment = (TextView) rootView.findViewById(R.id.tv_good_comment);
        tv_no_comment = (TextView) rootView.findViewById(R.id.tv_no_comment);
        mCommentView = (CommentView) rootView.findViewById(R.id.comment_view);
        setDetailData();
        //setLoopView();  //加载数据后再调用
        setRecommendGoods();

        //设置文字中间一条横线
        tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        fab_up_slide.hide();

        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        vp_item_goods_img.setPageIndicator(new int[]{R.mipmap.index_white, R.mipmap.index_red});
        vp_item_goods_img.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        vp_recommend.setPageIndicator(new int[]{R.drawable.shape_item_index_white, R.drawable.shape_item_index_red});
        vp_recommend.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);


    }


    private void initData() {

        fragmentList = new ArrayList<>();
        tabTextList = new ArrayList<>();
        tabTextList.add(tv_goods_detail);
        tabTextList.add(tv_goods_config);
    }

    /**
     * 加载完商品详情执行
     */
    public void setDetailData() {
        goodsConfigFragment = new GoodsConfigFragment();
        goodsInfoWebFragment = new GoodsInfoWebFragment();
        fragmentList.add(goodsConfigFragment);
        fragmentList.add(goodsInfoWebFragment);

        nowFragment = goodsInfoWebFragment;
        fragmentManager = getChildFragmentManager();
        //默认显示商品详情tab
        fragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment).commitAllowingStateLoss();
    }

    /**
     * 设置推荐商品
     */
    public void setRecommendGoods() {
        List<RecommendGoodsBean> data = new ArrayList<>();
        data.add(new RecommendGoodsBean("丝袜薄款瘦腿袜竹纤维三角棉裆连裤袜美腿连体打底袜子女",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501748584716&di=16da50a0f9934d77462d67f317dce3ef&imgtype=0&src=http%3A%2F%2Fdynamic-image.yesky.com%2F740x-%2FuploadImages%2F2015%2F159%2F20%2FHV9K66V8I35T.jpg", new BigDecimal(1099), "9.9"));
        data.add(new RecommendGoodsBean("维多利亚的秘密 Victoria's Secret 性感系带口袋睡袍浴袍 241580 黑色 XS/S",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502552743&di=9d04a0d2ddd8c8b1dc7e2c8aa37b9e89&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F160328%2F16-16032Q63140.jpg", new BigDecimal(1099), "599"));
        data.add(new RecommendGoodsBean("百褶连衣裙 香影2017夏秋女装新款系带收腰裙子纯色休闲雪纺裙潮纯色百褶连衣裙",
                "https://img.alicdn.com/bao/uploaded/i1/TB1TsjkSpXXXXabaFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg", new BigDecimal(899), "239"));
        data.add(new RecommendGoodsBean("预售乐町2017秋季新款女毛衣毛针织衫开衫绣花甜美外套中长款时尚 ",
                "https://img.alicdn.com/bao/uploaded/i2/TB16b6pSpXXXXcgXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg", new BigDecimal(1299), "359"));
        data.add(new RecommendGoodsBean("4条浪莎内裤女士三角裤夏季透气高腰提臀透明蕾丝边性感短裤衩 ",
                "https://img.alicdn.com/bao/uploaded/i4/TB17i22RFXXXXXcXpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg", new BigDecimal(599), "199"));
        data.add(new RecommendGoodsBean("5双浪莎丝袜超薄空姐防勾丝连裤袜 夏季肉色丝袜隐形打底袜子女 ",
                "https://img.alicdn.com/bao/uploaded/i3/TB1U6wjSXXXXXbZXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg", new BigDecimal(299), "39"));

        List<List<RecommendGoodsBean>> handledData = handleRecommendGoods(data);




        //设置如果只有一组数据时不能滑动
        vp_recommend.setManualPageable(handledData.size() == 1 ? false : true);
        vp_recommend.setCanLoop(handledData.size() == 1 ? false : true);
        vp_recommend.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ItemRecommendAdapter();
            }
        }, handledData);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_pull_up:
                //上拉查看图文详情
                sv_switch.smoothOpen(true);
                break;

            case R.id.fab_up_slide:
                //点击滑动到顶部
                sv_goods_info.smoothScrollTo(0, 0);
                sv_switch.smoothClose(true);
                break;

            case R.id.ll_goods_detail:
                //商品详情tab
                nowIndex = 0;
                scrollCursor();
                switchFragment(nowFragment, goodsInfoWebFragment);
                nowFragment = goodsInfoWebFragment;
                break;

            case R.id.ll_goods_config:
                //规格参数tab
                nowIndex = 1;
                scrollCursor();
                switchFragment(nowFragment, goodsConfigFragment);
                nowFragment = goodsConfigFragment;
                break;
            case R.id.ll_comment:
                //切转到评论列表
                ((ProductDetaiActivity) getActivity()).mVpContent.setCurrentItem(2);
                break;
            case R.id.ll_current_goods:
                DialogConfirmView dialogConfirmView = new DialogConfirmView(getActivity(),mActivity.mProductInfoBean);
                dialogConfirmView.show();
                break;
            default:
                break;
        }
    }
    // TODO: 2017/8/3 设置详情页数据

    /**
     * 给商品轮播图设置图片路径
     */
    public void setLoopView() {
        List<String> imgUrls = new ArrayList<>();
        //从activity拿到数据
        if (mActivity.mProductInfoBean != null) {
            ProductInfoBean.ProductBean product = mActivity.mProductInfoBean.getProduct();
            //设置轮播图
            if (product.getPics() != null) {
                for (int i = 0; i < product.getPics().size(); i++) {
                    imgUrls.add(Constant.BASE_URL + product.getPics().get(i));
                }
            }

            //设置价格
            tv_new_price.setText(PriceFormater.format(product.getPrice()));
            tv_old_price.setText(PriceFormater.format(product.getMarketPrice()));
            //设置已选
            //tv_current_goods.setText(product.getProductProperty().get(0).getV() + ",1件");
            //设置评论数
            //有评论默认显示第一条
            CommentInfoBean commentBean = mActivity.mCommentInfoBean;
            if (commentBean.getComment().size() > 0) {
                tv_no_comment.setVisibility(View.GONE);//有评论则关闭无评论显示控件
                mCommentView.setVisibility(View.VISIBLE);
                mCommentView.setName(commentBean.getComment().get(0).getUsername());
                mCommentView.setTime(commentBean.getComment().get(0).getTime() + "年");
                mCommentView.setComment(commentBean.getComment().get(0).getContent());
            } else {
                tv_no_comment.setVisibility(View.VISIBLE);
                mCommentView.setVisibility(View.GONE);
            }
            tv_comment_count.setText("(" + commentBean.getComment().size() + ")");

        }
        //设置标题
        setTitle();

       /* imgUrls.add("http://img4.hqbcdn.com/product/79/f3/79f3ef1b0b2283def1f01e12f21606d4.jpg");
        imgUrls.add("http://img14.hqbcdn.com/product/77/6c/776c63e6098f05fdc5639adc96d8d6ea.jpg");
        imgUrls.add("http://img13.hqbcdn.com/product/41/ca/41cad5139371e4eb1ce095e5f6224f4d.jpg");
        imgUrls.add("http://img10.hqbcdn.com/product/fa/ab/faab98caca326949b87b770c8080e6cf.jpg");
        imgUrls.add("http://img2.hqbcdn.com/product/6b/b8/6bb86086397a8cd0525c449f29abfaff.jpg");*/
        //初始化商品图片轮播
        vp_item_goods_img.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, imgUrls);
    }

    //设置标题
    private void setTitle() {
        //从activity拿到数据
        if (mActivity.mDesInfoBean != null) {
            String productdesc = mActivity.mDesInfoBean.getProductdesc();
            tv_goods_title.setText(productdesc);
        }
    }


    @Override
    public void onStatucChanged(SlideDetailsLayout.Status status) {
        if (status == SlideDetailsLayout.Status.OPEN) {
            //当前为图文详情页
            fab_up_slide.show();
            activity.mVpContent.setNoScroll(true);
            activity.mTvTitle.setVisibility(View.VISIBLE);
            activity.mPstsTabs.setVisibility(View.GONE);
        } else {
            //当前为商品详情页
            fab_up_slide.hide();
            activity.mVpContent.setNoScroll(false);
            activity.mTvTitle.setVisibility(View.GONE);
            activity.mPstsTabs.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 滑动游标
     */
    private void scrollCursor() {
        TranslateAnimation anim = new TranslateAnimation(fromX, nowIndex * v_tab_cursor.getWidth(), 0, 0);
        anim.setFillAfter(true);//设置动画结束时停在动画结束的位置
        anim.setDuration(50);
        //保存动画结束时游标的位置,作为下次滑动的起点
        fromX = nowIndex * v_tab_cursor.getWidth();
        v_tab_cursor.startAnimation(anim);

        //设置Tab切换颜色
        for (int i = 0; i < tabTextList.size(); i++) {
            tabTextList.get(i).setTextColor(i == nowIndex ? getResources().getColor(R.color.text_red) : getResources().getColor(R.color.text_black));
        }
    }

    /**
     * 切换Fragment
     * <p>(hide、show、add)
     *
     * @param fromFragment
     * @param toFragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (nowFragment != toFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {    // 先判断是否被add过
                fragmentTransaction.hide(fromFragment).add(R.id.fl_content, toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    /**
     * 处理推荐商品数据(每两个分为一组)
     *
     * @param data
     * @return
     */
    public static List<List<RecommendGoodsBean>> handleRecommendGoods(List<RecommendGoodsBean> data) {
        List<List<RecommendGoodsBean>> handleData = new ArrayList<>();
        int length = data.size() / 2;
        if (data.size() % 2 != 0) {
            length = data.size() / 2 + 1;
        }
        for (int i = 0; i < length; i++) {
            List<RecommendGoodsBean> recommendGoods = new ArrayList<>();
            for (int j = 0; j < (i * 2 + j == data.size() ? 1 : 2); j++) {
                recommendGoods.add(data.get(i * 2 + j));
            }
            handleData.add(recommendGoods);
        }
        return handleData;
    }
}
