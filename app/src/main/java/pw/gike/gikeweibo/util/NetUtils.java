package pw.gike.gikeweibo.util;

import android.content.Context;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.Map;

import pw.gike.gikeweibo.API;
import pw.gike.gikeweibo.interfaces.RequestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {

//    private static Weibo resultWeibo;

    public final static int REQUEST_GET = 0;

    public final static int REQUEST_POST = 1;

    private static CallbackDataListener callbackDataListener;

    public interface CallbackDataListener<T> {
        public void callBack(T result, String api);
    }

    public static void setDataListener(CallbackDataListener callbackDataListener) {
//        callbackDataListener.callBack(resultWeibo);
        NetUtils.callbackDataListener = callbackDataListener;
    }

    public static void request(final Context context, Oauth2AccessToken mAccessToken, final int requestType, final String api_type, final String api_detail, final Map<String, Object> params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //步骤4:创建Retrofit对象
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API.baseURL) // 设置 网络请求 Url
                        .addConverterFactory(GsonConverterFactory.create())   //设置使用Gson解析(记得加入依赖)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                // 步骤5:创建 网络请求接口 的实例
                RequestInterface request = retrofit.create(RequestInterface.class);

                //对 发送请求 进行封装(参考API.java)
//        Call<Weibo> call = null;
                Call<Object> call = null;
                if (requestType == NetUtils.REQUEST_GET) {
                    call = request.getCall(api_type, api_detail, params);
                } else if (requestType == NetUtils.REQUEST_POST) {
                    call = request.postCall(api_type, api_detail, params);
                }

                //步骤6:发送网络请求(异步)
                call.enqueue(new Callback<Object>() {
                    //请求成功时回调
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                        // 步骤7：处理返回的数据结果
                        Object result = response.body();
//                Toast.makeText(MainActivity.this, statusesList.get(0).getText(), Toast.LENGTH_SHORT).show();

                        if (result != null) {
//                    NetUtils.result = result;
                            callbackDataListener.callBack(result, api_type + api_detail);
                        } else {
                            Toast.makeText(context, "Can't get result", Toast.LENGTH_SHORT).show();

                        }
                    }

                    //请求失败时回调
                    @Override
                    public void onFailure(Call<Object> call, Throwable throwable) {
//                System.out.println("连接失败");
                        throwable.printStackTrace();
                        Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();

    }
}
