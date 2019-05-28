package com.caimuhao.rxpicker.utils;

import android.support.v7.widget.RecyclerView;

import com.caimuhao.rxpicker.R;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.caimuhao.rxpicker.bean.Item;
import com.caimuhao.rxpicker.ui.adapter.PickerFragmentAdapter;
import com.caimuhao.rxpicker.ui.picker.PickerFragment;

import java.util.List;

public class DefaultClickListener implements OnClickListener{
    @Override
    public void onCameraClick(PickerFragment fragment, Callback callback) {
        fragment.takePictures();
    }

    @Override
    public void onImageClick(PickerFragmentAdapter adapter, RecyclerView.ViewHolder holder, int position,PickerConfig config) {
        onImageSelect(adapter,holder,position,config);
    }

    @Override
    public void onImageSelect(PickerFragmentAdapter adapter, RecyclerView.ViewHolder holder, int position, PickerConfig config) {
        List<ImageItem> checkImage = adapter.getCheckImage();
        final Item imageItem = adapter.getItem(position);
        if (config.isSingle()) {
            RxBus.singleton().post(imageItem);
        } else {
            int maxValue = config.getMaxValue();
            if (checkImage.size() == maxValue && !checkImage.contains(imageItem)) {
                T.show(holder.itemView.getContext(),holder.itemView.getContext().getString(R.string.max_select, config.getMaxValue()));
                return;
            }
            boolean b = checkImage.contains(imageItem) ? checkImage.remove(imageItem)
                    : checkImage.add((ImageItem) imageItem);
            adapter.notifyItemChanged(holder.getAdapterPosition());
        }
    }

    @Override
    public void onStart(PickerFragment fragment, Callback callback) {

    }
}
