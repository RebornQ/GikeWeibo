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

    private Button btToWBAuth, btCopyToken;

    private TextView tvToken;

    private Weibo resultWeibo;

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

                        // 请求所需的参数（动态参数）
                        Map<String, String> params = new HashMap<>();
                        params.put("access_token", mAccessToken.getToken());
                        NetUtils.request(MainActivity.this, mAccessToken, API.type_statuses, API.home_timeline, params);
                        NetUtils.setDataListener(MainActivity.this);

                        if (resultWeibo != null) {
                            Log.i("MainActivity", resultWeibo.toString());
                        }
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

    @Override
    public void backData(Weibo resultWeibo) {
        this.resultWeibo = resultWeibo;
    }
}
