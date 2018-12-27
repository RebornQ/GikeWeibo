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

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;


import java.util.HashMap;
import java.util.Map;

import pw.gike.gikeweibo.API;
import pw.gike.gikeweibo.R;
import pw.gike.gikeweibo.bean.Weibo;
import pw.gike.gikeweibo.util.NetUtils;
import pw.gike.gikeweibo.util.StringUtils;

public class MainActivity extends AppCompatActivity implements NetUtils.CallbackData {

    private Button btCopyToken;

    private TextView tvToken;

    private Weibo resultWeibo;

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
                request(mAccessToken);
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

    private void request(Oauth2AccessToken mAccessToken) {
        // 请求所需的参数（动态参数）
        Map<String, String> params = new HashMap<>();
        params.put("access_token", mAccessToken.getToken());
        // 发出请求
        NetUtils.request(MainActivity.this, mAccessToken, API.type_statuses, API.home_timeline, params);
//                        NetUtils.setDataListener(MainActivity.this);
        // 设置数据返回监听器
        NetUtils.setDataListener(new NetUtils.CallbackData() {
            @Override
            public void backData(Weibo resultWeibo) {
                MainActivity.this.resultWeibo = resultWeibo;
                if (MainActivity.this.resultWeibo != null) {
                    Log.i("MainActivityCallback", resultWeibo.getTotalNumber().toString());
                }

                if (resultWeibo != null) {
                    tvToken.setText(resultWeibo.getStatuses().get(1).getText());
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
                        if (resultWeibo != null) {
                            tvToken.setText(resultWeibo.getStatuses().get(1).getText());
                        } else {
                            Toast.makeText(MainActivity.this, "获取信息失败！", Toast.LENGTH_SHORT).show();
                        }
                        StringUtils.putTextIntoClip(MainActivity.this, mAccessToken.getToken());
                        Toast.makeText(MainActivity.this, "复制成功！", Toast.LENGTH_SHORT).show();
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
                        request(mAccessToken);
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
    public void backData(Weibo resultWeibo) {
        this.resultWeibo = resultWeibo;
        if (this.resultWeibo != null) {
            Log.i("MainActivityCallback", resultWeibo.getTotalNumber().toString());
        }
    }
}
