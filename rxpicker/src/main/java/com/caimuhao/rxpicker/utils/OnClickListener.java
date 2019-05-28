package com.caimuhao.rxpicker.utils;

import android.support.v7.widget.RecyclerView;

import com.caimuhao.rxpicker.bean.ImageItem;
import com.caimuhao.rxpicker.ui.adapter.PickerFragmentAdapter;
import com.caimuhao.rxpicker.ui.picker.PickerFragment;

import java.util.ArrayList;

public interface OnClickListener {

    void onCameraClick(PickerFragment fragment,Callback callback);

    void onImageClick(PickerFragmentAdapter adapter,RecyclerView.ViewHolder holder, int position,PickerConfig config);

    void onImageSelect(PickerFragmentAdapter adapter,RecyclerView.ViewHolder holder, int position,PickerConfig config);

    void onStart(PickerFragment fragment,Callback callback);

    interface Callback<T extends ImageItem>{
        void onCustomResult(ArrayList<T> data);
    }

}
