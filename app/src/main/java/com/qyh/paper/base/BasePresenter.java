package com.qyh.paper.base;

import android.os.Bundle;

import com.qyh.litemvp.nucleus.presenter.RxPresenter;

import icepick.Icepick;

public class BasePresenter<ViewType> extends RxPresenter<ViewType> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Icepick.restoreInstanceState(this, savedState);
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
        Icepick.saveInstanceState(this, state);
    }
}
