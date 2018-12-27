package pw.gike.gikeweibo.util;

import android.content.Context;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.Map;

import pw.gike.gikeweibo.API;
import pw.gike.gikeweibo.bean.Weibo;
import pw.gike.gikeweibo.interfaces.RequestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {

//    private static Weibo resultWeibo;

    private static CallbackData callbackData;

    public interface CallbackData {
        public void backData(Weibo resultWeibo);
    }

    public static void setDataListener(CallbackData callbackData) {
//        callbackData.backData(resultWeibo);
        NetUtils.callbackData = callbackData;
    }

    public static void request(final Context context, Oauth2AccessToken mAccessToken, String api_type, String api_detail, Map<String, String> params) {

//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Weibo.class, new StatusModelAdapter())
//                .create();

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.baseURL) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create())   //设置使用Gson解析(记得加入依赖)
                //然后将上面的GsonConverterFactory.create()替换成我们自定义的ResponseConverterFactory.create()
//                .addConverterFactory(ResponseConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        // 步骤5:创建 网络请求接口 的实例
        RequestInterface request = retrofit.create(RequestInterface.class);

        //对 发送请求 进行封装(参考API.java)
        Call<Weibo> call = request.getCall(api_type, api_detail, params);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Weibo>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Weibo> call, Response<Weibo> response) {

                // 步骤7：处理返回的数据结果
                Weibo resultWeibo = response.body();
//                Toast.makeText(MainActivity.this, statusesList.get(0).getText(), Toast.LENGTH_SHORT).show();

                if (resultWeibo != null) {
                    Toast.makeText(context, resultWeibo.getStatuses().get(0).getText(), Toast.LENGTH_SHORT).show();
//                    NetUtils.resultWeibo = resultWeibo;
                    callbackData.backData(resultWeibo);
                } else {
                    Toast.makeText(context, "Can't get result", Toast.LENGTH_SHORT).show();

                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Weibo> call, Throwable throwable) {
//                System.out.println("连接失败");
                throwable.printStackTrace();
                Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
