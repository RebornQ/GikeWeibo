package pw.gike.gikeweibo;

public class API {

    // full url e.g.: baseURL + type_statuses + home_timeline

    public static final String baseURL = "https://api.weibo.com/2/";

    // type
    public static final String type_statuses = "statuses";
    public static final String type_comments = "comments";
    public static final String type_friendships = "friendships";
    public static final String type_trends = "trends";

    public static final String user_show = "users/show.json?uid={0}&access_token={1}";

    // statuses
    public static final String public_timeline = "public_timeline.json";   // ?access_token={0}
    public static final String friends_timeline = "friends_timeline.json";     // ?access_token={0}
    public static final String home_timeline = "home_timeline.json";   // ?count=20&access_token={0}
    public static final String bilateral_timeline = "bilateral_timeline.json"; // ?count=20&access_token={0}
    public static final String repost = "repost.json"; // 转发一条微博
    public static final String updata = "update.json"; // 发布一条微博
    public static final String user_timeline = "user_timeline.json";
    public static final String delete = "destroy.json";
    public static final String bilateral_timeline2 = "bilateral_timeline.json";
    public static final String mentions = "mentions.json";

    // comments
    public static final String comment_show = "show.json";     // ?access_token={0}
    public static final String comment_create = "create.json";
    public static final String replay = "reply.json";
    public static final String comment_mentions = "mentions.json";

    // friendships
    public static final String friendships_destroy = "destroy.json";
    public static final String friendships_create = "create.json";
    public static final String followers = "followers.json";
    public static final String friendship_friends = "friends.json";

    // trends
    public static final String weekly = "weekly.json";
    public static final String daily = "daily.json";
    public static final String hourly = "hourly.json";

    public static final String users = "search/suggestions/users.json";

    public static final String upload = "https://upload.api.weibo.com/2/statuses/upload.json";
}
