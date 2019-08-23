package com.lc.image;


import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.ebo.citylife.common.utils.GsonUtils;
import com.ebo.citylife.common.utils.MainThreadPostUtils;
import com.ebo.common.utils.LogUtil;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : lc
 *         date : 18/12/11
 */
public class GsonRequest<T> extends Request<T> {

  private final Response.Listener<T> mListener;

  private Class<T> mClass;

  private Map<String, String> mParams;

  private String mRequestBody;

  GsonRequest(IGsonRequest.RequestParams requestParams, Response.Listener<T> listener,
      Response.ErrorListener errorListener) {
    super(requestParams.getRequestMethod(), requestParams.getUrl(), errorListener);
    if (requestParams.getRequestMethod() == Method.GET) {
      this.mParams = requestParams.getMap();
    } else {
      getRequestBodyString(requestParams.getMap());
    }
    mClass = requestParams.getClazz();
    mListener = listener;
  }

  private void getRequestBodyString(Map<String, String> params) {
    if (params == null) {
      return;
    }
    JSONObject jsonObject = new JSONObject(params);
    mRequestBody = jsonObject.toString();
  }

  @Override
  protected Map<String, String> getParams() {
    return mParams;
  }

  @Override
  protected Response<T> parseNetworkResponse(NetworkResponse response) {
    try {
      String jsonString =
          new String(response.data, HttpHeaderParser.parseCharset(response.headers));
      LogUtil.d("CityLife_request_json", jsonString);
      return Response.success(GsonUtils.fromJson(jsonString, mClass),
          HttpHeaderParser.parseCacheHeaders(response));
    } catch (UnsupportedEncodingException e) {
      return Response.error(new ParseError(e));
    }
  }

  @Override
  protected void deliverResponse(T response) {
    if (response == null) {
      MainThreadPostUtils.toast("response is null");
      return;
    }
    if (mListener != null) {
      mListener.onResponse(response);
    }
  }

  @Override
  public Map<String, String> getHeaders() {
    HashMap<String, String> map = new HashMap<>(1);
    map.put("Content-Type", "application/json");

    return map;
  }

  @Override
  public byte[] getBody() {
    try {
      return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
    } catch (Exception e) {
      return null;
    }
  }
}
