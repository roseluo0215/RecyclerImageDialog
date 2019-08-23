package com.lc.image;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ebo.citylife.common.base.model.BaseHttpModel;
import com.ebo.citylife.common.config.GlobalConfig;

/**
 * @author : lc
 *         date : 18/12/12
 */
public class GsonRequestProxy implements IGsonRequest {

  private volatile static GsonRequestProxy sInstance;

  private IGsonRequest mRequest;

  public static GsonRequestProxy getInstance() {
    if (sInstance == null) {
      synchronized (GsonRequestProxy.class) {
        if (sInstance == null) {
          sInstance = new GsonRequestProxy();
        }
      }
    }
    return sInstance;
  }

  private GsonRequestProxy() {
    RequestQueue queue = Volley.newRequestQueue(GlobalConfig.getAppContext());
    this.mRequest = new GsonRequestImp(queue);
  }

  @Override
  public <T extends BaseHttpModel> void requestHttp(RequestParams requestParams,
      IDataCallback<T> callback) {
    if (mRequest != null) {
      mRequest.requestHttp(requestParams, callback);
    }
  }

}
