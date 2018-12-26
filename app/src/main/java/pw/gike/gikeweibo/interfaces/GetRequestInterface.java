package pw.gike.gikeweibo.interfaces;

import pw.gike.gikeweibo.bean.Weibo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRequestInterface {

    // https://api.weibo.com/2/statuses/home_timeline.json?access_token=xxxxxx
    @GET("statuses/home_timeline.json")
    Call<Weibo> getCall(@Query("access_token") String access_token);
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法
}
