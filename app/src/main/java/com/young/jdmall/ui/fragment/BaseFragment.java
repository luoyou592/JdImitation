package com.young.jdmall.ui.fragment;

import android.widget.Toast;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxFragment;
import com.young.jdmall.ui.utils.JudgeNetwork;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 25505 on 2017/8/1.
 */

public abstract class BaseFragment extends RxFragment {
    /**
     * 线程调度
     */
    protected <T> ObservableTransformer<T, T> compose(final LifecycleTransformer<T> lifecycle) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                // 可添加网络连接判断等
                                if (!JudgeNetwork.isNetworkAvailable(getActivity())) {
                                    Toast.makeText(getActivity(), "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(lifecycle);
            }
        };
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = JDMallApplication.getRefWatcher(getActivity());
        refWatcher.watch(getActivity());
    }*/
}
