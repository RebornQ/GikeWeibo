package pw.gike.gikeweibo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;


import java.util.HashMap;
import java.util.Map;

import pw.gike.gikeweibo.API;
import pw.gike.gikeweibo.R;
import pw.gike.gikeweibo.bean.statuses.Weibo;
import pw.gike.gikeweibo.util.NetUtils;
import pw.gike.gikeweibo.util.StringUtils;

public class MainActivity extends AppCompatActivity implements NetUtils.CallbackData {

    private Button btCopyToken;

    private TextView tvToken;

    private Weibo resultWeibo;

    private String resultJson;

    private int currentPage = 1; // 获取到的微博列表当前页码  // 页码等于-1时代表出错，不再自增

    private int lastPage = 1; // 获取到的微博列表最后页码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setListener();

        // 先判断是否已登录，登录则直接获取 token 执行请求操作
        Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(MainActivity.this);
        if (mAccessToken != null && mAccessToken.getToken() != null && !mAccessToken.getToken().equals("")) {
            if (mAccessToken.isSessionValid()) {
                // 已登录，执行请求操作
                Toast.makeText(MainActivity.this, "已登录", Toast.LENGTH_SHORT).show();
                getWeiboRequest(mAccessToken);
            } else {
                Toast.makeText(MainActivity.this, "Token已失效，请重新登录", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, WBAuthActivity.class);
                startActivityForResult(intent, RESULT_FIRST_USER);
            }
        } else {
            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, WBAuthActivity.class);
            startActivityForResult(intent, RESULT_FIRST_USER);
        }
    }

    private void initView() {
        btCopyToken = findViewById(R.id.bt_copy_token);
        tvToken = findViewById(R.id.tv_show_token);
    }

    private void getWeiboRequest(Oauth2AccessToken mAccessToken) {
        // 请求所需的参数（动态参数）
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", mAccessToken.getToken());    // 采用OAuth授权方式为必填参数，OAuth授权后获得。
        params.put("count", "20"); // 单页返回的记录条数，最大不超过100，默认为20。
        params.put("page", String.valueOf(currentPage));   // 返回结果的页码，默认为1。
        // 发出请求
//        Call<Weibo> call = null;
        NetUtils.request(MainActivity.this, mAccessToken, NetUtils.REQUEST_GET,
                API.type_statuses, API.home_timeline, params);
//                        NetUtils.setDataListener(MainActivity.this);
        // 设置数据返回监听器
        NetUtils.setDataListener(new NetUtils.CallbackData() {
            @Override
            public void backData(Object result) {

                if (result != null) {
                    // 获取请求结果成功后的操作
                    // 通过 Gson 把 Json 反序列化为 Weibo 对象
                    MainActivity.this.resultWeibo = new Gson().fromJson(StringUtils.objectToJsonString(result), Weibo.class);
                    Log.i("MainActivityCallback", MainActivity.this.resultWeibo.getTotalNumber().toString());

                    try {
                        String tvShowStr = "当前页码：" + currentPage + "\n"
                                + MainActivity.this.resultWeibo.getStatuses().get(0).getText();
                        tvToken.setText(tvShowStr);
                        lastPage = currentPage;
                    } catch (IndexOutOfBoundsException e) {
                        currentPage = -1;  // 页码等于-1时代表出错，不再自增
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "已到最后一页: " + lastPage, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "获取信息失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setListener() {

        btCopyToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(MainActivity.this);
                if (mAccessToken != null && mAccessToken.getToken() != null && !mAccessToken.getToken().equals("")) {
                    if (mAccessToken.isSessionValid()) {
                        // 已登录，执行请求操作
                        if (currentPage > 0) {
                            currentPage++;
                            getWeiboRequest(mAccessToken);
                        } else if (currentPage == -1) {
                            Toast.makeText(MainActivity.this, "已到最后一页: " + lastPage, Toast.LENGTH_SHORT).show();
                        }
//                        StringUtils.putTextIntoClip(MainActivity.this, mAccessToken.getToken());
//                        Toast.makeText(MainActivity.this, "复制成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Token已失效，请重新登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, WBAuthActivity.class);
                        startActivityForResult(intent, RESULT_FIRST_USER);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, WBAuthActivity.class);
                    startActivityForResult(intent, RESULT_FIRST_USER);
                }
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
                if (mAccessToken != null && mAccessToken.getToken() != null && !mAccessToken.getToken().equals("")) {
                    if (mAccessToken.isSessionValid()) {
                        Toast.makeText(MainActivity.this, "Token: " + mAccessToken.getToken(), Toast.LENGTH_SHORT).show();
                        getWeiboRequest(mAccessToken);
                    } else {
                        Toast.makeText(MainActivity.this, "Token已失效，请重新登录", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Can't get token", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == 0) {
            return;
        }
    }

    @Override
    public void backData(Object result) {
        this.resultJson = StringUtils.objectToJsonString(result);
    }
}
