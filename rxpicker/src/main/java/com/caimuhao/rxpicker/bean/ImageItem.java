package com.caimuhao.rxpicker.bean;

import android.view.View;

import com.caimuhao.rxpicker.R;

import java.io.Serializable;

/**
 * @author Smile
 * @time 2017/4/19  上午11:50
 * @desc ${TODD}
 */
public class ImageItem implements Serializable, Comparable<ImageItem> ,Item{

  /**
   * Image id.
   */
  private int id;
  /**
   * Image path.
   */
  private String path;
  /**
   * Image name.
   */
  private String name;
  /**
   * The time to be added to the library.
   */
  private long addTime;

  public ImageItem(int id, String path, String name, long addTime) {
    this.id = id;
    this.path = path;
    this.name = name;
    this.addTime = addTime;
  }

  public ImageItem() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getAddTime() {
    return addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int compareTo(ImageItem o) {
    long time = o.getAddTime() - getAddTime();
    if (time > Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    } else if (time < -Integer.MAX_VALUE) return -Integer.MAX_VALUE;
    return (int) time;
  }

  @Override
  public int getLayoutId() {
    return R.layout.item_picker;
  }

  @Override
  public int getType() {
    return TYPE_IMAGE;
  }

  @Override
  public void onClick(View v) {

  }
}
