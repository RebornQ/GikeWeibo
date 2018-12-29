package pw.gike.gikeweibo.util.requests;

import android.content.Context;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.HashMap;
import java.util.Map;

import pw.gike.gikeweibo.API;
import pw.gike.gikeweibo.util.NetUtils;

public class WeiboRequests {

    /**
     * 获取最新的微博列表
     * @param context 上下文
     * @param mAccessToken OAuth授权后获得的 accessToken
     * @param currentPage 获取到的微博列表当前页码
     * */
    public static void getWeiboRequest(Context context, Oauth2AccessToken mAccessToken, Integer currentPage) {
        // 请求所需的参数（动态参数）
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", mAccessToken.getToken());    // 采用OAuth授权方式为必填参数，OAuth授权后获得。
        params.put("count", "20"); // 单页返回的记录条数，最大不超过100，默认为20。
        params.put("page", String.valueOf(currentPage));   // 返回结果的页码，默认为1。
        // 发出请求
//        Call<Weibo> call = null;
        NetUtils.request(context, mAccessToken, NetUtils.REQUEST_GET,
                API.type_statuses, API.home_timeline, params);
//                        NetUtils.setDataListener(MainActivity.this);
    }

    /**
     * 发送一条微博
     * @param context 上下文
     * @param mAccessToken OAuth授权后获得的 accessToken
     * @param status 要发布的微博文本内容
     * */
    // 暂不可用，应用权限不足（Insufficient app permissions!），需要通过开发者认证
    public static void sendWeiboRequest(Context context, Oauth2AccessToken mAccessToken, String status) {
        if (status.length() > 140) {    // status是要发布的微博文本内容，必须做URLencode，内容不超过140个汉字
            Toast.makeText(context, "字数不能超过140个汉字！", Toast.LENGTH_SHORT).show();
            return;
        }
        // 请求所需的参数（动态参数）
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", mAccessToken.getToken());    // 采用OAuth授权方式为必填参数，OAuth授权后获得。
        params.put("status", status);   // status是要发布的微博文本内容
        // 发出请求
//        Call<Weibo> call = null;
        NetUtils.request(context, mAccessToken, NetUtils.REQUEST_POST,
                API.type_statuses, API.updata, params);
    }

    /**
     * 评论一条微博
     * @param context 上下文
     * @param mAccessToken OAuth授权后获得的 accessToken
     * @param comment 要评论的微博文本内容
     * @param id 需要评论的微博ID
     * */
    public static void commentWeiboRequest(Context context, Oauth2AccessToken mAccessToken,String comment, long id) {
        if (comment.length() > 140) {    // comment是要评论的微博文本内容，必须做URLencode，内容不超过140个汉字
            Toast.makeText(context, "字数不能超过140个汉字！", Toast.LENGTH_SHORT).show();
            return;
        }
        // 请求所需的参数（动态参数）
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", mAccessToken.getToken());    // 采用OAuth授权方式为必填参数，OAuth授权后获得。
        params.put("comment", comment);   // status是要发布的微博文本内容
        params.put("id", id);// 需要评论的微博ID。
        // 发出请求
//        Call<Comment> call = null;
        NetUtils.request(context, mAccessToken, NetUtils.REQUEST_POST,
                API.type_comments, API.comment_create, params);
    }

    /**
     * 转发一条微博
     * @param context 上下文
     * @param mAccessToken OAuth授权后获得的 accessToken
     * @param id 要转发的微博ID
     * @param status 添加的转发文本，必须做URLEncode，内容不超过140个汉字，不填则默认为“转发微博”。
     * */
    // 暂不可用，应用权限不足（Insufficient app permissions!），需要通过开发者认证
    public static void reportWeiboRequest(Context context, Oauth2AccessToken mAccessToken, long id, String status) {
        if (status.length() > 140) {    // 内容不超过140个汉字
            Toast.makeText(context, "字数不能超过140个汉字！", Toast.LENGTH_SHORT).show();
            return;
        }
        // 请求所需的参数（动态参数）
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", mAccessToken.getToken());    // 采用OAuth授权方式为必填参数，OAuth授权后获得。
        params.put("id", id);// 需要评论的微博ID。
        params.put("status", status);// 添加的转发文本，必须做URLEncode，内容不超过140个汉字，不填则默认为“转发微博”。
        // 发出请求
        NetUtils.request(context, mAccessToken, NetUtils.REQUEST_POST,
                API.type_comments, API.comment_create, params);
    }

}
