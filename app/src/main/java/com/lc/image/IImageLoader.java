package com.lc.image;

import android.widget.ImageView;

/**
 * @author : lc
 *         date : 18/12/15
 */
public interface IImageLoader {

  /**
   * 通过url加载网络图片并展示
   * @param url 请求图片的url
   * @param imageView 加载图片的view
   * @param width 设置宽
   * @param height 设置高
   * @param defaultImage 加载中的默认图片
   * @param failedImage 加载失败后展示的图
   * @param scaleType ImageView加载类型
   **/
  void loadImage(String url, ImageView imageView, int width, int height, int defaultImage,
      int failedImage, int scaleType);

  void loadImage(String url, ImageView imageView, int defaultImage, int failedImage, int scaleType);

  void loadImage(String url, ImageView imageView, int defaultImage, int failedImage);

  void loadImage(String url, ImageView imageView, int scaleType);

  void loadImage(String url, ImageView imageView);


}
