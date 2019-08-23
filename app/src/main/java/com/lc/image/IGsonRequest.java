package com.lc.image;

import com.ebo.citylife.common.base.model.BaseHttpModel;

import java.util.Map;

/**
 * @author : lc
 *         date : 19/3/23
 */
public interface IGsonRequest {

  <T extends BaseHttpModel> void requestHttp(RequestParams requestParams,
                                             IDataCallback<T> callback);

  class RequestParams {
    private Map<String, String> mMap;
    private Class mClazz;
    private Object mTag;
    private String mUrl;
    private int mRequestMethod;

    public Map<String, String> getMap() {
      return mMap;
    }

    public void setMap(Map<String, String> map) {
      mMap = map;
    }

    Class getClazz() {
      return mClazz;
    }

    public void setClazz(Class clazz) {
      mClazz = clazz;
    }

    public Object getTag() {
      return mTag;
    }

    public void setTag(Object tag) {
      mTag = tag;
    }

    public String getUrl() {
      return mUrl;
    }

    public void setUrl(String url) {
      mUrl = url;
    }

    int getRequestMethod() {
      return mRequestMethod;
    }

    public void setRequestMethod(int requestMethod) {
      mRequestMethod = requestMethod;
    }
  }
}
