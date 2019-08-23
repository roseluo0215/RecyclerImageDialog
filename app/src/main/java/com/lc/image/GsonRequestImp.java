package com.lc.image;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.ebo.citylife.common.base.model.BaseHttpModel;
import com.ebo.citylife.common.config.Constant;
import com.ebo.citylife.common.config.GlobalConfig;
import com.ebo.common.utils.AccountHelper;
import com.ebo.common.utils.UrlHelper;

import java.util.HashMap;

/**
 * @author : lc
 *         date : 19/3/23
 */
public class GsonRequestImp implements IGsonRequest {

  private RequestQueue mQueue;

  GsonRequestImp(RequestQueue queue) {
    mQueue = queue;
  }

  @Override
  public <T extends BaseHttpModel> void requestHttp(RequestParams requestParams,
      IDataCallback<T> callback) {

    // 封装url如果是get请求将urlParams拼接到url上面，如果是post请求就不需要拼接
    String url = UrlHelper.parseUrl(GlobalConfig.getAppContext(), requestParams.getUrl(),
        requestParams.getRequestMethod() == Request.Method.GET
            ? (HashMap<String, String>) requestParams.getMap()
            : null);
    requestParams.setUrl(url);
    GsonRequest<T> gsonRequest = new GsonRequest<>(requestParams, response -> {
      if (callback == null) {
        return;
      }
      if (TextUtils.equals(Constant.REQUEST_STATUS_SUCCEED, response.getStatus())) {
        callback.onSuccess(response);
      } else {
        callback.onFailed(response);
        if (TextUtils.equals(response.getErrorCode(), Constant.TOKEN_INVALID_CODE)) {
          AccountHelper.getInstance(GlobalConfig.getAppContext()).notifyTokenExpired();
        }
      }
    }, error -> {
      if (callback != null) {
        callback.onFailed(error.networkResponse);
      }
    });

    if (requestParams.getTag() != null) {
      gsonRequest.setTag(requestParams.getTag());
    }
    mQueue.add(gsonRequest);
  }
}
