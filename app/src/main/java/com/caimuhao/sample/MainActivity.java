package com.caimuhao.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.caimuhao.rxpicker.widget.DividerGridItemDecoration;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private TextView tvSingleImg;
  private TextView tvMultiImg;

  private RecyclerView recyclerView;
  private PickerAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle("RxPicker");

    tvSingleImg = (TextView) findViewById(R.id.btn_single_img);
    tvSingleImg.setOnClickListener(this);

    tvMultiImg = (TextView) findViewById(R.id.btn_multi_img);
    tvMultiImg.setOnClickListener(this);

    adapter = new PickerAdapter();
    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    recyclerView.setAdapter(adapter);
  }

  @Override public void onClick(View v) {
    if (tvSingleImg == v) {
      Disposable subscribe = RxPicker.of().start(this).subscribe(new Consumer<List<ImageItem>>() {
        @Override public void accept(@NonNull List<ImageItem> imageItems) throws Exception {
          adapter.setData(imageItems);
        }
      });
    } else if (tvMultiImg == v) {
      Disposable subscribe = RxPicker.of()
          .single(false)
          .camera(true)
          .limit(3, 9)
          .start(this)
          .subscribe(new Consumer<List<ImageItem>>() {
            @Override public void accept(@NonNull List<ImageItem> imageItems) throws Exception {
              adapter.setData(imageItems);
            }
          });
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }
}
