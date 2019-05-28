package com.caimuhao.rxpicker.bean;

import android.support.annotation.LayoutRes;
import android.view.View;

public interface Item {

    int TYPE_IMAGE = 0;
    int TYPE_CAMERA = 1;

    @LayoutRes int getLayoutId();

    int getType();

    void onClick(View v);

    String getPath();

}
