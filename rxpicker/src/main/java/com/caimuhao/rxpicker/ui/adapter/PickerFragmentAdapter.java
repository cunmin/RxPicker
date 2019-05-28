package com.caimuhao.rxpicker.ui.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.caimuhao.rxpicker.R;
import com.caimuhao.rxpicker.bean.CameraItem;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.caimuhao.rxpicker.bean.Item;
import com.caimuhao.rxpicker.utils.PickerConfig;
import com.caimuhao.rxpicker.utils.RxPickerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Smile
 * @time 2017/4/19  上午10:43
 * @desc ${TODD}
 */
public class PickerFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int CAMERA_TYPE = 0;
  private static final int NORMAL_TYPE = 1;

  private View.OnClickListener cameraClickListener;

  private int imageWidth;
  private PickerConfig config;

  private List<Item> datas;
  private List<ImageItem> checkImage;

  public PickerFragmentAdapter(int imageWidth) {
    config = RxPickerManager.getInstance().getConfig();
    this.imageWidth = imageWidth;
    datas = new ArrayList<>();
    checkImage = new ArrayList<>();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (R.layout.item_picker == viewType) {
      return new PickerViewHolder(
              LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picker, parent, false));
    } else {
      return new CustomViewHolder(
              LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }
  }

  @Override public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
    if (Item.TYPE_CAMERA == datas.get(position).getType()) {
      holder.itemView.setOnClickListener(cameraClickListener);
      return;
    }
    final int dataPosition = position;//config.isShowCamera() ? position - 1 : position;

    final Item imageItem = datas.get(dataPosition);
    PickerViewHolder pickerViewHolder = (PickerViewHolder) holder;
    pickerViewHolder.bind(imageItem);

    pickerViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        config.getOnClickListener().onImageClick(PickerFragmentAdapter.this,holder,dataPosition,config);
      }
    });
    pickerViewHolder.cbCheck.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        config.getOnClickListener().onImageSelect(PickerFragmentAdapter.this,holder,dataPosition,config);
      }
    });
  }

  @Override public int getItemCount() {
    return null==datas?0:datas.size();
  }

  @Override public int getItemViewType(int position) {
    return datas.get(position).getLayoutId();
  }

  public <T extends Item> void setData(List<T> data) {
    datas.clear();
    if(config.isShowCamera()){
      datas.add(new CameraItem(cameraClickListener));
    }
    if(null!=data){
      datas.addAll(data);
    }
  }

  public void setCameraClickListener(View.OnClickListener cameraClickListener) {
    this.cameraClickListener = cameraClickListener;
  }

  public ArrayList<ImageItem> getCheckImage() {
    return (ArrayList<ImageItem>) checkImage;
  }

  private class PickerViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private AppCompatCheckBox cbCheck;

    private PickerViewHolder(View itemView) {
      super(itemView);
      imageView = (ImageView) itemView.findViewById(R.id.iv_image);
      cbCheck = (AppCompatCheckBox) itemView.findViewById(R.id.cb_check);
    }

    private void bind(Item imageItem) {
      ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
      layoutParams.width = imageWidth;
      layoutParams.height = imageWidth;
      imageView.setLayoutParams(layoutParams);

      RxPickerManager.getInstance().display(imageView, imageItem.getPath(), imageWidth, imageWidth);
      cbCheck.setVisibility(config.isSingle() ? View.GONE : View.VISIBLE);
      cbCheck.setChecked(checkImage.contains(imageItem));
    }
  }

  private class CustomViewHolder extends RecyclerView.ViewHolder {

    private CustomViewHolder(View itemView) {
      super(itemView);
    }
  }

  public Item getItem(int position){
      return datas.get(position);
  }
}


