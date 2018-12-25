package pw.gike.gikeweibo;

import android.app.Application;
import android.content.Context;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

public class MyApplication extends Application {

    private static Context context;

    public interface Constants {
        public static final String APP_KEY = "1588505149"; // 应用的APP_KEY
        public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";// 应用的回调页
        public static final String SCOPE = // 应用申请的高级权限
                "email,direct_messages_read,direct_messages_write,"
                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                        + "follow_app_official_microblog," + "invitation_write";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        WbSdk.install(this,new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL,
                Constants.SCOPE));
    }

    public static Context getContext() {
        return context;
    }
}
