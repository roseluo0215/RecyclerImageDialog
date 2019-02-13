package com.lc.recycleimagedialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author : lc
 *         date : 19/2/13
 */
public class ShowRecyclerImageListAdapter
    extends RecyclerView.Adapter<ShowRecyclerImageListAdapter.ShowRecyclerImageViewHolder> {

  private Context mContext;
  private List<String> mData;

  ShowRecyclerImageListAdapter(Context context, List<String> data) {
    mContext = context;
    mData = data;
  }

  @NonNull
  @Override
  public ShowRecyclerImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ImageView imageView = new ImageView(mContext);
    ViewGroup.LayoutParams layoutParams =
        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
    // 使图片充满控件大小
    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    imageView.setLayoutParams(layoutParams);
    return new ShowRecyclerImageViewHolder(imageView);
  }

  @Override
  public void onBindViewHolder(@NonNull ShowRecyclerImageViewHolder showRecyclerImageViewHolder,
      int i) {
    if (mData == null) {
      return;
    }
    String url = mData.get(i);
    if (TextUtils.isEmpty(url)) {
      return;
    }
    ImageView imageView = showRecyclerImageViewHolder.mImageView;
    if (imageView != null) {
      Glide.with(mContext).load(url).into(imageView);
    }
  }

  @Override
  public int getItemCount() {
    return mData == null ? 0 : mData.size();
  }

  class ShowRecyclerImageViewHolder extends RecyclerView.ViewHolder {
    ImageView mImageView;

    ShowRecyclerImageViewHolder(@NonNull View itemView) {
      super(itemView);
      mImageView = (ImageView) itemView;
    }
  }
}
