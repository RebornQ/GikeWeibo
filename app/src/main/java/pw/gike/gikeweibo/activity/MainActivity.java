package pw.gike.gikeweibo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.ArrayList;
import java.util.List;

import pw.gike.gikeweibo.API;
import pw.gike.gikeweibo.R;
import pw.gike.gikeweibo.adapter.WeiboAdapter;
import pw.gike.gikeweibo.bean.comments.Comment;
import pw.gike.gikeweibo.bean.statuses.Status;
import pw.gike.gikeweibo.bean.statuses.Weibo;
import pw.gike.gikeweibo.util.NetUtils;
import pw.gike.gikeweibo.util.StringUtils;
import pw.gike.gikeweibo.util.requests.WeiboRequests;

public class MainActivity extends AppCompatActivity implements NetUtils.CallbackDataListener,WeiboAdapter.CallbackListener {

    private ImageButton btComment;

    private EditText etComment;

    private LinearLayout lyComment;

    private Long statusId;

    private Weibo resultWeibo;

    private List<Status> statusList = new ArrayList<>();

//    private String resultJson;

    private RecyclerView recyclerView;

    private WeiboAdapter weiboAdapter;

    private boolean isLoadMore = false;

    private boolean isDataInited = false;

    private Integer currentPage = 1; // 获取到的微博列表当前页码  // 页码等于-1时代表出错，不再自增

    private Integer lastPage = 1; // 获取到的微博列表最后页码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setListener();

        // 先判断是否已登录，登录则直接获取 token 执行请求操作
        Oauth2AccessToken mAccessToken = checkAccessToken(this);
        if (mAccessToken != null) {
            // 已登录，执行请求操作
            Toast.makeText(MainActivity.this, "已登录", Toast.LENGTH_SHORT).show();
            WeiboRequests.getWeiboRequest(this, mAccessToken, currentPage);
        }
    }

    private void initView() {
        etComment = findViewById(R.id.et_comment);
        btComment = findViewById(R.id.bt_send);
        lyComment = findViewById(R.id.ly_comment);
        lyComment.setVisibility(View.GONE); // View.VISIBLE

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setListener() {

        // 设置请求的数据返回监听器
        NetUtils.setDataListener(this);

        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = String.valueOf(etComment.getText());
                Oauth2AccessToken mAccessToken = checkAccessToken(MainActivity.this);
                if (mAccessToken != null) {
                    if (comment != null && !comment.equals("") && currentPage > 0) {
                        WeiboRequests.commentWeiboRequest(MainActivity.this, mAccessToken, comment, statusId);
                        lyComment.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private Oauth2AccessToken checkAccessToken(Context context) {
        Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(context);
        if (mAccessToken != null && mAccessToken.getToken() != null && !mAccessToken.getToken().equals("")) {
            if (mAccessToken.isSessionValid()) {
                return mAccessToken;
            } else {
                Toast.makeText(context, "Token已失效，请重新登录", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, WBAuthActivity.class);
                startActivityForResult(intent, RESULT_FIRST_USER);
                return null;
            }
        } else {
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, WBAuthActivity.class);
            startActivityForResult(intent, RESULT_FIRST_USER);
            return null;
        }
    }

    private void initData() {
        statusList = resultWeibo.getStatuses();
        if (!isDataInited) {
            weiboAdapter = new WeiboAdapter(this, lyComment, this, statusList);
            recyclerView.setAdapter(weiboAdapter);
            recyclerView.addOnScrollListener(monScrollListener);
            isDataInited = true;
        }
    }

    private void setWeibo(Object result) {
        // 通过 Gson 把 Json 反序列化为 Weibo 对象
        MainActivity.this.resultWeibo = new Gson().fromJson(StringUtils.objectToJsonString(result), Weibo.class);

        try {
//            String tvShowStr = "当前页码：" + currentPage + "\n"
//                    + MainActivity.this.resultWeibo.getStatuses().get(0).getText();
//            tvToken.setText(tvShowStr);
            initData();
            lastPage = currentPage;
        } catch (IndexOutOfBoundsException e) {
            currentPage = -1;  // 页码等于-1时代表出错，不再自增
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "已到最后一页: " + lastPage, Toast.LENGTH_SHORT).show();
        }
    }
    //本段可直接Copy，作用是监听Recycleview是否滑动到底部
    private int mLastVisibleItemPosition;
    private RecyclerView.OnScrollListener monScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (weiboAdapter != null) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == weiboAdapter.getItemCount()) {
                    //发送网络请求获取更多数据
                    Oauth2AccessToken mAccessToken = checkAccessToken(MainActivity.this);
                    if (mAccessToken != null) {
                        // 已登录，执行请求操作
                        if (currentPage > 0) {
                            currentPage++;
                            WeiboRequests.getWeiboRequest(MainActivity.this, mAccessToken, currentPage);
                            if (isLoadMore) {
                                weiboAdapter.addList(resultWeibo.getStatuses());
                                weiboAdapter.notifyDataSetChanged();
                                isLoadMore = false;
                            }
                        } else if (currentPage == -1) {
                            Toast.makeText(MainActivity.this, "已到最后一页: " + lastPage, Toast.LENGTH_SHORT).show();
                            isLoadMore = false;
                        }
                    }
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            if (data != null) {
//                String name = data.getStringExtra("extra_data_tag");
//                Toast.makeText(MainActivity.this, "Return MainActivity", Toast.LENGTH_SHORT).show();
                Oauth2AccessToken mAccessToken = checkAccessToken(this);
                if (mAccessToken != null) {
                    Toast.makeText(MainActivity.this, "Token: " + mAccessToken.getToken(), Toast.LENGTH_SHORT).show();
                    WeiboRequests.getWeiboRequest(this, mAccessToken, currentPage);
                }
            } else {
                Toast.makeText(MainActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == 0) {
            return;
        }
    }

    @Override
    public void callBack(Object result, String api) {
        if (result == null) {
            Toast.makeText(MainActivity.this, "获取信息失败！", Toast.LENGTH_SHORT).show();
            return;
        }
//        this.resultJson = StringUtils.objectToJsonString(result);
        // 根据回传的 api 字串判断执行哪些操作
        switch (api) {
            case API.type_statuses + API.home_timeline:
                // 获取请求结果成功后的操作
                setWeibo(result);
                isLoadMore = true;
                break;
            case API.type_comments + API.comment_create:
                Comment comment = new Gson().fromJson(StringUtils.objectToJsonString(result), Comment.class);
                if (comment.getId() != null) {
                    Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();
//                    tvToken.setText(comment.getText());
                    etComment.setText("");
                }
                break;
        }
    }

    @Override
    public void callback(Object data) {
        statusId = (Long) data;
    }
}
