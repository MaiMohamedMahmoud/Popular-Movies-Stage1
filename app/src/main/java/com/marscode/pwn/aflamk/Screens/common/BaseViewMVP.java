package com.marscode.pwn.aflamk.Screens.common;

import android.view.View;

import com.marscode.pwn.aflamk.Screens.common.ViewMVP;

public class BaseViewMVP implements ViewMVP {

    private View rootview;

    @Override
    public View getRootView() {
        return rootview;
    }

    @Override
    public void setRootView(View view) {
        rootview = view;
    }

}
