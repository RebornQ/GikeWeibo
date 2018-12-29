package pw.gike.gikeweibo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import pw.gike.gikeweibo.API;
import pw.gike.gikeweibo.R;
import pw.gike.gikeweibo.bean.statuses.Weibo;
import pw.gike.gikeweibo.util.NetUtils;
import pw.gike.gikeweibo.util.StringUtils;
import pw.gike.gikeweibo.util.requests.WeiboRequests;

public class MainActivity extends AppCompatActivity implements NetUtils.CallbackDataListener {

    private Button btCopyToken;

    private TextView tvToken;

    private Weibo resultWeibo;

//    private String resultJson;

    private Integer currentPage = 1; // 获取到的微博列表当前页码  // 页码等于-1时代表出错，不再自增

    private Integer lastPage = 1; // 获取到的微博列表最后页码

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
                WeiboRequests.getWeiboRequest(this, mAccessToken, currentPage);
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

    private void setListener() {

        // 设置请求的数据返回监听器
        NetUtils.setDataListener(this);

        btCopyToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(MainActivity.this);
                if (mAccessToken != null && mAccessToken.getToken() != null && !mAccessToken.getToken().equals("")) {
                    if (mAccessToken.isSessionValid()) {
                        // 已登录，执行请求操作
                        if (currentPage > 0) {
                            currentPage++;
                            WeiboRequests.getWeiboRequest(MainActivity.this, mAccessToken, currentPage);
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

    private void setWeibo(Object result) {
        // 通过 Gson 把 Json 反序列化为 Weibo 对象
        MainActivity.this.resultWeibo = new Gson().fromJson(StringUtils.objectToJsonString(result), Weibo.class);

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
                        WeiboRequests.getWeiboRequest(this, mAccessToken, currentPage);
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
    public void callBack(Object result, String api) {
        if (result == null) {
            Toast.makeText(MainActivity.this, "获取信息失败！", Toast.LENGTH_SHORT).show();
            return;
        }
//        this.resultJson = StringUtils.objectToJsonString(result);
        // 根据回传的 api 字串判断执行哪些操作
        switch (api) {
            case API.type_statuses + API.home_timeline:
                // 获取请求结果成功后的操作
                setWeibo(result);
                break;
            case API.type_comments + API.comment_create:
                break;
        }
    }
}
