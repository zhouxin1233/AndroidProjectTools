package com.tools.activity;


import android.view.View;
import android.widget.Button;

import com.tools.MyApplication;
import com.tools.R;
import com.tools.base.BaseActivity;
import com.tools.been.IdCardBean;
import com.tools.callback.OkHttpStopCallback;
import com.tools.callback.OnGetByteArrayListener;
import com.tools.callback.OnGetJsonObjectListener;
import com.tools.callback.OnGetOkhttpStringListener;
import com.tools.constant.Urls;
import com.tools.utils.ActivityManagerUtils;
import com.tools.utils.OkHttpUtils;
import com.tools.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

/**
 * OkHttp封装后网络请求详解及示例
 */
public class OkHttpActivity extends BaseActivity {
    private OkHttpUtils mOkHttpUtils;
    private IdCardBean mIdCardBean;
    private Button btn_get_one;
    private Button btn_get_two;
    private Button btn_get_three;
    private Button btn_get_five;
    private Button btn_get_six;
    private Button btn_get_seven;
    private Map<String, String> params;

    @Override
    protected void initView() {
        ActivityManagerUtils.getInstance().addActivity(this);
        setContentView(R.layout.activity_okhttp);
        btn_get_one = (Button) findViewById(R.id.btn_get_one);
        btn_get_two = (Button) findViewById(R.id.btn_get_two);
        btn_get_three = (Button) findViewById(R.id.btn_get_three);
        btn_get_five = (Button) findViewById(R.id.btn_get_five);
        btn_get_six = (Button) findViewById(R.id.btn_get_six);
        btn_get_seven = (Button) findViewById(R.id.btn_get_seven);
        mOkHttpUtils = MyApplication.getApp().getOkHttpUtils();
        params = new HashMap<>();
        params.put("id", "342401199309037434");
    }

    @Override
    protected void initData() {
        mOkHttpUtils.post(Urls.OKHTTP_POS_TURL, params, null, new OkHttpStopCallback<IdCardBean>() {
            @Override
            public void onSuccess(Response response, IdCardBean idCardBean) {
//                LogUtil.E("post" + idCardBean.getRetMsg());
                ToastUtils.show(getApplicationContext(),"post" + idCardBean.getRetMsg());
            }
        });
    }

    @Override
    protected void setOnclickListener() {
        super.setOnclickListener();
        /**
         * 请求指定的url返回的结果是json字符串
         */
        btn_get_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOkHttpUtils.asyncJsonStringByURL(Urls.OKHTTP_GET_URL, new OnGetOkhttpStringListener() {
                    @Override
                    public void onResponse(String result) {
                        if (result != null && !result.equals("")) {
//                            Log.e(Constant.TAG, "asyncJsonObjectByURL" + result);
                            ToastUtils.show(getApplicationContext(),"asyncJsonObjectByURL" + result);

                        }
                    }
                });
            }
        });

        /**
         *请求返回的是jsonOject对象
         */
        btn_get_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOkHttpUtils.asyncJsonObjectByURL(Urls.OKHTTP_GET_URL, new OnGetJsonObjectListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        Log.e(Constant.TAG, "asyncJsonObjectByURL: " + jsonObject.toString());
                        try {
//                            Log.e(Constant.TAG, "onResponse: " + jsonObject.getString("errNum").toString());
                            ToastUtils.show(getApplicationContext(),"onResponse: " + jsonObject.getString("errNum").toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }


        });

        /**
         * 请求返回的是byte字节数组
         */
        btn_get_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOkHttpUtils.asyncGetByteByURL(Urls.OKHTTP_GET_URL, new OnGetByteArrayListener() {
                    @Override
                    public void onResponse(byte[] result) {
                        if (result != null) {
//                            Log.e(Constant.TAG, "onResponse: " + result);
                            ToastUtils.show(getApplicationContext(),"onResponse: " + result);

                        }
                    }
                });
            }
        });

        /**
         * 表单提交，post请求，返回jsonobject对象
         */
        btn_get_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOkHttpUtils.sendComplexForm(Urls.OKHTTP_GET_URL, params, new OnGetJsonObjectListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        Log.e(Constant.TAG, "asyncJsonObjectByURL: " + jsonObject.toString());
                        try {
//                            Log.e(Constant.TAG, "onResponse: " + jsonObject.getString("errNum").toString());
                            ToastUtils.show(getApplicationContext(),"onResponse: " + jsonObject.getString("errNum").toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        /**
         *GET请求返回结果是 实体类对象 类型
         */
        btn_get_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 返回String类型
                 */
//                mOkHttpUtils.get(GetUrl, String.class, new OkHttpStopCallback<String>() {
//                    @Override
//                    public void onSuccess(Response response, String s) {
//                        Log.e(Constant.TAG, "string.class" + s);
//                    }
//
//                });

                /**
                 * get请求返回Bean对象
                 */
                mOkHttpUtils.get(Urls.OKHTTP_GET_URL, IdCardBean.class, new OkHttpStopCallback<IdCardBean>() {

                    @Override
                    public void onSuccess(Response response, IdCardBean idCardBean) {
                        if (idCardBean != null) {
                            mIdCardBean = idCardBean;
//                            Log.e(Constant.TAG, mIdCardBean.getRetMsg());
                            ToastUtils.show(getApplicationContext(),mIdCardBean.getRetMsg());
                        } else {
//                            Log.e(Constant.TAG, "idCardBean为空了");
                            ToastUtils.show(getApplicationContext(),"idCardBean为空了");
                        }
                    }
                });
            }
        });
        /**
         * POST请求返回结果是 实体类对象 类型
         */
        btn_get_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOkHttpUtils.post(Urls.OKHTTP_POS_TURL, params, null, new OkHttpStopCallback<IdCardBean>() {
                    @Override
                    public void onSuccess(Response response, IdCardBean idCardBean) {
//                        Log.e(Constant.TAG, "post" + idCardBean.getRetMsg());
                        if (idCardBean != null) {
                            mIdCardBean = idCardBean;
//                            Log.e(Constant.TAG, mIdCardBean.getRetMsg());
                            ToastUtils.show(getApplicationContext(),mIdCardBean.getRetMsg());
                        } else {
//                            Log.e(Constant.TAG, "idCardBean为空了");
                            ToastUtils.show(getApplicationContext(),"idCardBean为空了");
                        }

                    }
                });
            }
        });

    }
}
