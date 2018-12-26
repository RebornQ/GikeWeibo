package pw.gike.gikeweibo.interfaces;

import java.util.Map;

import pw.gike.gikeweibo.bean.Weibo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GetRequestInterface {

    /**
     * https://api.weibo.com/2/statuses/home_timeline.json?access_token=xxxxxx
     * 参数说明
                    必选      类型及范围	            说明
     access_token	true	   string	    采用OAuth授权方式为必填参数，OAuth授权后获得。
     since_id	    false	    int64	    若指定此参数，则返回ID比since_id大的微博（即比since_id时间晚的微博），默认为0。
     max_id	        false	    int64	    若指定此参数，则返回ID小于或等于max_id的微博，默认为0。
     count	        false	    int	        单页返回的记录条数，最大不超过100，默认为20。
     page	        false	    int	        返回结果的页码，默认为1。
     base_app	    false	    int	        是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0。
     feature	    false	    int	        过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
     trim_user	    false	    int	        返回值中user字段开关，0：返回完整user字段、1：user字段仅返回user_id，默认为0。
     *
     */
    @GET("statuses/home_timeline.json")
    Call<Weibo> getCall(@QueryMap Map<String, String> params);
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法
}
