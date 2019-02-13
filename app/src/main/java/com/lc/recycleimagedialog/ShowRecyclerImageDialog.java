package com.lc.recycleimagedialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : lc
 *         date : 19/2/13
 * 
 *         这个dialog主要是为了展示一个类似viewpager翻页带有指示器的功能，可以左右翻页
 *         主要实现是通过recycleview来代替viewpager来实现的该功能。
 */
public class ShowRecyclerImageDialog extends AlertDialog {

  private LinearLayout mIndicator;

  private List<String> mUrlList;

  /**
   * 存放指示器
   */
  private List<View> mIndicatorViews = null;

  private int mPictureSize;

  ShowRecyclerImageDialog(Context context) {
    super(context, R.style.ShowImageDialog);
  }

  private Drawable mDrawable;

  public ShowRecyclerImageDialog(Context context, Drawable drawable) {
    super(context, R.style.ShowImageDialog);
    mDrawable = drawable;
  }

  public void setImageUrl(String url) {
    if (mUrlList == null) {
      mUrlList = new ArrayList<>(1);
    }
    mUrlList.add(url);
  }

  void setImageUrlList(List<String> urlList) {
    mUrlList = urlList;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialog_show_recycler_image);
    if (getWindow() == null) {
      return;
    }
    int screenHeight = DisplayUtils.getScreenHeight(getContext());
    int screenWidth = DisplayUtils.getScreenWidth(getContext());
    WindowManager.LayoutParams attributes = getWindow().getAttributes();
    attributes.width = screenWidth;
    attributes.height = screenHeight + 120;
    getWindow().setAttributes(attributes);
    setCanceledOnTouchOutside(false);

    RelativeLayout dialogBg = findViewById(R.id.rl_dialog);
    if (mDrawable != null) {
      dialogBg.setBackground(mDrawable);
    }
    mIndicator = findViewById(R.id.ll_indicator);
    ImageView dismissDialog = findViewById(R.id.iv_dismiss_dialog);
    dismissDialog.setOnClickListener(v -> dismiss());

    RecyclerView recyclerView = findViewById(R.id.rl_show_image);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    recyclerView.setLayoutManager(layoutManager);
    PagerSnapHelper linearSnapHelper = new PagerSnapHelper();

    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
          return;
        }
        View snapView = linearSnapHelper.findSnapView(layoutManager);
        int currentPosition = 0;
        if (snapView != null) {
          currentPosition = layoutManager.getPosition(snapView);
        }
        // 判断当前是最后一张图片的时候还继续往左边滑动或者只有一张照片的时候不去执行setSelectedPage方法，避免出错
        if (currentPosition == mPictureSize || mPictureSize == 1) {
          return;
        }
        setSelectedPage(currentPosition);
      }

    });

    linearSnapHelper.attachToRecyclerView(recyclerView);
    ShowRecyclerImageListAdapter adapter = new ShowRecyclerImageListAdapter(getContext(), mUrlList);
    recyclerView.setAdapter(adapter);
    mPictureSize = mUrlList.size();
    // 如果当前RecycleView只有一张图片就不去添加indicator指示器
    if (mPictureSize > 1) {
      initIndicator();
    }
  }

  /**
   * 设置选中页
   */
  private void setSelectedPage(int selected) {
    for (int i = 0; i < mPictureSize; i++) {
      mIndicatorViews.get(i).setBackgroundResource(
          i == selected
              ? R.drawable.show_recycler_image_dot_select
              : R.drawable.show_recycler_image_dot);
    }
  }

  /**
   * 初始化指示器,默认选中第一页
   */
  private void initIndicator() {
    if (mIndicatorViews == null) {
      mIndicatorViews = new ArrayList<>(mPictureSize);
    }
    View view;
    // 指示器的大小(dp)
    int dotSize = 15;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dotSize, dotSize);
    // 指示器间距(dp)
    int margins = 4;
    params.setMargins(margins, margins, margins, 10);
    for (int i = 0; i < mPictureSize; i++) {
      view = new View(getContext());
      view.setLayoutParams(params);
      view.setBackgroundResource(R.drawable.show_recycler_image_dot);
      mIndicatorViews.add(view);
      mIndicator.addView(view);
    }
    mIndicatorViews.get(0).setBackgroundResource(R.drawable.show_recycler_image_dot_select);
  }

  @Override
  public void dismiss() {
    super.dismiss();
  }

  @Override
  public void show() {
    super.show();
  }
}
