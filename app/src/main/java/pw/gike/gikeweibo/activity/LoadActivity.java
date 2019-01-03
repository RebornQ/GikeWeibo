package pw.gike.gikeweibo.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import pw.gike.gikeweibo.R;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_load);
        // 等待时间
        new Handler().postDelayed(r, 2500);

    }
    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(LoadActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

}
