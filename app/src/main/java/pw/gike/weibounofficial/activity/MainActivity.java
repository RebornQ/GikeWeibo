package pw.gike.weibounofficial.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pw.gike.weibounofficial.R;

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
                Intent intent = new Intent(MainActivity.this, WBAuthActivity.class);
                startActivity(intent);
            }
        });
    }
}
