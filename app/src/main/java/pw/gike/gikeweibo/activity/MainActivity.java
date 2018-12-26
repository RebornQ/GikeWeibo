package pw.gike.gikeweibo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pw.gike.gikeweibo.R;
import pw.gike.gikeweibo.bean.Status;
import pw.gike.gikeweibo.bean.Weibo;
import pw.gike.gikeweibo.interfaces.GetRequestInterface;
import pw.gike.gikeweibo.util.StringUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btToWBAuth, btCopyToken;

    private TextView tvToken;

    private final static String baseURL = "https://api.weibo.com/2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setListener();
    }

    private void initView() {
        btToWBAuth = findViewById(R.id.bt_goto_WBAuth);
        btCopyToken = findViewById(R.id.bt_copy_token);
        tvToken = findViewById(R.id.tv_show_token);
    }

    private void setListener() {

        btToWBAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 先判断是否已登录，登录则直接获取 token 执行请求操作
                Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(MainActivity.this);
                if (mAccessToken != null) {
                    if (mAccessToken.isSessionValid()) {
                        // 已登录，执行请求操作
                        Toast.makeText(MainActivity.this, "Token: " + mAccessToken.getToken(), Toast.LENGTH_SHORT).show();
                        request(mAccessToken);
                    } else {
                        Toast.makeText(MainActivity.this, "Token 已失效", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, WBAuthActivity.class);
                        startActivityForResult(intent, RESULT_FIRST_USER);
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, WBAuthActivity.class);
                    startActivityForResult(intent, RESULT_FIRST_USER);
                }
            }
        });

        btCopyToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(MainActivity.this);
                if (mAccessToken != null) {
                    if (mAccessToken.isSessionValid()) {
                        // 已登录，执行请求操作
                        tvToken.setText(mAccessToken.getToken());
                        StringUtils.putTextIntoClip(MainActivity.this, mAccessToken.getToken());
                        Toast.makeText(MainActivity.this, "复制成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Token 已失效", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void request(Oauth2AccessToken mAccessToken) {

//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Weibo.class, new StatusModelAdapter())
//                .create();

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create())   //设置使用Gson解析(记得加入依赖)
                //然后将上面的GsonConverterFactory.create()替换成我们自定义的ResponseConverterFactory.create()
//                .addConverterFactory(ResponseConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequestInterface request = retrofit.create(GetRequestInterface.class);

        // 请求所需的参数（动态参数）
        Map<String, String> params = new HashMap<>();
        params.put("access_token", mAccessToken.getToken());

        //对 发送请求 进行封装
        Call<Weibo> call = request.getCall(params);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Weibo>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Weibo> call, Response<Weibo> response) {
                // 步骤7：处理返回的数据结果
                List<Status> statusesList = response.body().getStatuses();
//                Toast.makeText(MainActivity.this, statusesList.get(0).getText(), Toast.LENGTH_SHORT).show();

                if (statusesList != null && !statusesList.isEmpty()) {
                    Toast.makeText(MainActivity.this, statusesList.get(0).getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Can't get result", Toast.LENGTH_SHORT).show();

                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Weibo> call, Throwable throwable) {
//                System.out.println("连接失败");
                throwable.printStackTrace();
                Toast.makeText(MainActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            if (data != null) {
//                String name = data.getStringExtra("extra_data_tag");
//                Toast.makeText(MainActivity.this, "Return MainActivity", Toast.LENGTH_SHORT).show();
                Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(this);
                if (mAccessToken != null) {
                    if (mAccessToken.isSessionValid()) {
                        Toast.makeText(MainActivity.this, "Token: " + mAccessToken.getToken(), Toast.LENGTH_SHORT).show();
//                        request(mAccessToken);
                    } else {
                        Toast.makeText(MainActivity.this, "Token 已失效", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Can't get token", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
