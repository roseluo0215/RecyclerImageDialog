package com.lc.image;

import android.widget.ImageView;

/**
 * @author : lc
 *         date : 18/12/15
 */
public class ImageLoaderProxy implements IImageLoader {

  private static volatile ImageLoaderProxy sInstance;

  private ImageLoaderProxy() {}

  static ImageLoaderProxy getInstance() {
    if (sInstance == null) {
      synchronized (ImageLoaderProxy.class) {
        if (sInstance == null) {
          sInstance = new ImageLoaderProxy();
        }
      }
    }
    return sInstance;
  }

  private IImageLoader mImageLoader;

  void initImageLoader(IImageLoader imageLoader) {
    mImageLoader = imageLoader;
  }

  @Override
  public void loadImage(String url, ImageView imageView, int width, int height, int defaultImage,
      int failedImage, int scaleType) {
    if (null != mImageLoader) {
      mImageLoader.loadImage(url, imageView, width, height, defaultImage, failedImage, scaleType);
    }
  }

  @Override
  public void loadImage(String url, ImageView imageView, int defaultImage, int failedImage,
      int scaleType) {
    if (null != mImageLoader) {
      mImageLoader.loadImage(url, imageView, defaultImage, failedImage, scaleType);
    }
  }

  @Override
  public void loadImage(String url, ImageView imageView, int defaultImage, int failedImage) {
    if (null != mImageLoader) {
      mImageLoader.loadImage(url, imageView, defaultImage, failedImage);
    }
  }

  @Override
  public void loadImage(String url, ImageView imageView, int scaleType) {
    if (null != mImageLoader) {
      mImageLoader.loadImage(url, imageView, scaleType);
    }
  }

  @Override
  public void loadImage(String url, ImageView imageView) {
    if (null != mImageLoader) {
      mImageLoader.loadImage(url, imageView);
    }
  }

}
