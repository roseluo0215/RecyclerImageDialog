package com.lc.image;

import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.ebo.citylife.R;

/**
 * @author : lc
 *         date : 18/12/15
 */
public class ImageLoaderImp implements IImageLoader {

  private RequestQueue mQueue;

  ImageLoaderImp(RequestQueue queue) {
    mQueue = queue;
  }

  @Override
  public void loadImage(String url, ImageView imageView, int width, int height, int defaultImage,
      int failedImage, int scaleType) {
    if (null == url || null == imageView) {
      return;
    }
    ImageLoader.ImageListener listener =
        ImageLoader.getImageListener(imageView, defaultImage, failedImage);
    ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
    imageLoader.get(url, listener, width, height, getScaleType(scaleType));

  }

  @Override
  public void loadImage(String url, ImageView imageView, int defaultImage, int failedImage,
      int scaleType) {
    loadImage(url, imageView, 0, 0, defaultImage, failedImage, scaleType);

  }

  @Override
  public void loadImage(String url, ImageView imageView, int defaultImage, int failedImage) {
    loadImage(url, imageView, defaultImage, failedImage, -1);
  }

  @Override
  public void loadImage(String url, ImageView imageView, int scaleType) {
    // todo 默认显示图片和加载失败显示图片需要修改
    loadImage(url, imageView, R.mipmap.bg, R.mipmap.bg);
  }

  @Override
  public void loadImage(String url, ImageView imageView) {
    loadImage(url, imageView, -1);
  }

  private ImageView.ScaleType getScaleType(int scaleType) {
    switch (scaleType) {
      case 0:
        return ImageView.ScaleType.MATRIX;
      case 1:
        return ImageView.ScaleType.FIT_XY;
      case 2:
        return ImageView.ScaleType.FIT_START;
      case 3:
        return ImageView.ScaleType.FIT_CENTER;
      case 4:
        return ImageView.ScaleType.FIT_END;
      case 5:
        return ImageView.ScaleType.CENTER;
      case 6:
        return ImageView.ScaleType.CENTER_CROP;
      case 7:
        return ImageView.ScaleType.CENTER_INSIDE;
      default:
        return ImageView.ScaleType.FIT_XY;
    }

  }

  private static class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mLruCache;

    BitmapCache() {
      int cacheSize = 5 * 1024 * 1024;
      mLruCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
          return bitmap.getRowBytes() * bitmap.getHeight();
        }
      };
    }

    @Override
    public Bitmap getBitmap(String url) {
      return mLruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
      mLruCache.put(url, bitmap);
    }
  }
}
