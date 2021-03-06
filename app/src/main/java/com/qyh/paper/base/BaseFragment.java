package com.qyh.paper.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qyh.litemvp.event.BusManager;
import com.qyh.litemvp.nucleus.presenter.Presenter;
import com.qyh.litemvp.nucleus.view.NucleusSupportFragment;

import icepick.Icepick;

/**
 * @Description: Fragment基类
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 2016-12-19 14:52
 */
public abstract class BaseFragment<P extends Presenter> extends NucleusSupportFragment<P> implements View.OnClickListener {
    protected Context mContext;
    protected Resources mResources;
    protected LayoutInflater mInflater;
    protected View mConvertView;
    private SparseArray<View> mViews;
    private boolean mIsRegisterEvent = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
        this.mResources = mContext.getResources();
        this.mViews = new SparseArray<>();
        this.mInflater = LayoutInflater.from(mContext);
        if (mIsRegisterEvent) {
            BusManager.getBus().register(this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        mConvertView = inflater.inflate(getLayoutID(), container, false);
        return mConvertView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        bindEvent();
        initData();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Icepick.saveInstanceState(this, bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIsRegisterEvent) {
            BusManager.getBus().unregister(this);
        }
    }

    @Override
    public void onClick(View view) {
        processClick(view);
    }

    protected <E extends View> E F(int viewId) {
        if (mConvertView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    protected <E extends View> void C(E view) {
        view.setOnClickListener(this);
    }

    public boolean isRegisterEvent() {
        return mIsRegisterEvent;
    }

    public BaseFragment setRegisterEvent(boolean mIsRegisterEvent) {
        this.mIsRegisterEvent = mIsRegisterEvent;
        return this;
    }

    /**
     * 布局的LayoutID
     *
     * @return
     */
    protected abstract int getLayoutID();

    /**
     * 初始化子View
     */
    protected abstract void initView(View contentView);

    /**
     * 绑定事件
     */
    protected abstract void bindEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 点击事件处理
     * @param view
     */
    protected abstract void processClick(View view);
}
