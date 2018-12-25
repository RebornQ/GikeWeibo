package pw.gike.gikeweibo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import pw.gike.gikeweibo.R;
import pw.gike.gikeweibo.util.DateUtils;

public class WBAuthActivity extends AppCompatActivity {

    /**
     * 微博 Web 授权类，提供登陆等功能
     */
    private SsoHandler mSsoHandler;

    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;

    private TextView tvToken;

    private Button btGetToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbauth);
        initView();

        mSsoHandler = new SsoHandler(WBAuthActivity.this);

        // 获取Token
        btGetToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSsoHandler.authorize(new SelfWbAuthListener());
            }
        });
    }

    private void initView() {
        tvToken = findViewById(R.id.tv_show_token);
        btGetToken = findViewById(R.id.bt_get_token);
    }

    class SelfWbAuthListener implements WbAuthListener {
        @Override
        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(oauth2AccessToken.getBundle());
            if (mAccessToken.isSessionValid()) {
                // 保存 Token
                AccessTokenKeeper.writeAccessToken(WBAuthActivity.this, mAccessToken);
                // 获取 Token 后的操作
                tvToken.setText(String.valueOf("Token: " + mAccessToken.getToken()
                        + "\nPhone: " + mAccessToken.getPhoneNum()
                        + "\nUid: " + mAccessToken.getUid()
                        + "\nRefreshToken: " + mAccessToken.getRefreshToken()
                        + "\nExpiresTime: " + DateUtils.getDateToString(mAccessToken.getExpiresTime(), DateUtils.FORMAT_DATE_TIME_SECOND)
                ));

                Toast.makeText(WBAuthActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            } else {
                // 当您注册的应用程序签名不正确时，就会收到错误 Code，请确保签名正确
                String code = oauth2AccessToken.getBundle().getString("code", "");
                String message = "授权失败";
                if (!TextUtils.isEmpty(code) || !code.equals("")) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(WBAuthActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void cancel() {
            Toast.makeText(WBAuthActivity.this, "取消授权", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Toast.makeText(WBAuthActivity.this, "Auth exception : " + wbConnectErrorMessage.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // SSO 授权回调
        // 重要：发起 SSO 登录的 Activity 必须重写 onActivityResult
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
