package pw.gike.gikeweibo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import pw.gike.gikeweibo.R;

public class MainActivity extends AppCompatActivity {

    private Button btToWBAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btToWBAuth = findViewById(R.id.bt_goto_WBAuth);
        btToWBAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(MainActivity.this);
                if (mAccessToken != null) {
                    if (mAccessToken.isSessionValid()) {
                        Toast.makeText(MainActivity.this, "Token: " + mAccessToken.getToken(), Toast.LENGTH_SHORT).show();
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
