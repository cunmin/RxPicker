package com.caimuhao.rxpicker.bean;

import android.view.View;

import com.caimuhao.rxpicker.R;

public class CameraItem implements Item{

    private View.OnClickListener cameraClickListener;

    public CameraItem(View.OnClickListener cameraClickListener) {
        this.cameraClickListener = cameraClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_camera;
    }

    @Override
    public int getType() {
        return TYPE_CAMERA;
    }

    @Override
    public void onClick(View v) {
        if(null!=cameraClickListener){
            cameraClickListener.onClick(v);
        }
    }

    @Override
    public String getPath() {
        return null;
    }


}
