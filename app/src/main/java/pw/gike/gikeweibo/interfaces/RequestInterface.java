package pw.gike.gikeweibo.interfaces;

import java.util.Map;

import pw.gike.gikeweibo.bean.statuses.Weibo;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RequestInterface {

    @GET("{api_type}/{api_detail}") // e.g. statuses/home_timeline.json
    Call<Object> getCall(@Path("api_type") String api_type, @Path("api_detail") String api_detail, @QueryMap Map<String, Object> params);
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法

    @FormUrlEncoded
    @POST("{api_type}/{api_detail}") // e.g. statuses/update.json
    Call<Object> postCall(@Path("api_type") String api_type, @Path("api_detail") String api_detail, @FieldMap(encoded = true) Map<String, Object> fields);
}
