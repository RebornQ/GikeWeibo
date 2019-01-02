package pw.gike.gikeweibo.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.ArrayList;
import java.util.List;

import pw.gike.gikeweibo.R;
import pw.gike.gikeweibo.adapter.CommentAdapter;
import pw.gike.gikeweibo.bean.comments.Comments;
import pw.gike.gikeweibo.bean.comments.Comment;
import pw.gike.gikeweibo.bean.statuses.Status;
import pw.gike.gikeweibo.util.NetUtils;
import pw.gike.gikeweibo.util.StringUtils;
import pw.gike.gikeweibo.util.requests.WeiboRequests;

public class WbDetailActivity extends AppCompatActivity implements NetUtils.CallbackDataListener,CommentAdapter.CallbackListener{

    private ImageView imHead;
    private TextView tvUsername;
    private TextView tvTime;
    private TextView tvContent;
    private ImageButton btShowComment;

    private Oauth2AccessToken mAccessToken;

    private RecyclerView recyclerView;
    private ImageButton btSendComment;
    private EditText etComment;
//    private LinearLayout lyComment;

    private Status currentStatus;

    private List<Comment> comments = new ArrayList<>();

    private List<Comment> resultComments;

    private LinearLayout lyComment;

    private boolean isLoadMore = false;

    private boolean isDataInited = false;

//    private boolean isRefresh = false;

    private Integer currentPage = 1; // 获取到的评论列表当前页码  // 页码等于-1时代表出错，不再自增

    private Integer lastPage = 1; // 获取到的评论列表最后页码

    private CommentAdapter commentAdapter;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            if (msg.what == 0) {
                if (commentAdapter != null) {
                    comments.addAll(resultComments);
                    commentAdapter.addList(resultComments);
                    commentAdapter.notifyDataSetChanged();
                }
                isLoadMore = false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wb_detail);

        // 先判断是否已登录，登录则直接获取 token 执行请求操作
        mAccessToken = NetUtils.checkAccessToken(this);
        initView();
        // 设置请求的数据返回监听器
        NetUtils.setDataListener(this);
        currentStatus = new Gson().fromJson(this.getIntent().getStringExtra("WeiboItem"), Status.class);
        if (currentStatus != null) {
            if (mAccessToken != null) {
                WeiboRequests.getCommentWeiboRequest(this, mAccessToken, currentStatus.getId(), currentPage);
            }

            Glide.with(this)
                    .load(currentStatus.getUser().getProfileImageUrl())

                    .into(imHead);
            tvUsername.setText(currentStatus.getUser().getName());
            tvTime.setText(currentStatus.getCreatedAt());
            tvContent.setText(currentStatus.getText());
            btSendComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String comment = String.valueOf(etComment.getText());
                    if (mAccessToken != null) {
                        if (comment != null && !comment.equals("")) {
                            WeiboRequests.commentWeiboRequest(WbDetailActivity.this, mAccessToken, comment, currentStatus.getId());
                            // Refresh Comment List
                        }
                    }
                }
            });
        }
    }

    private void initView() {
        imHead = findViewById(R.id.include_weiboview).findViewById(R.id.im_head);
        tvUsername = findViewById(R.id.include_weiboview).findViewById(R.id.tv_username);
        tvTime = findViewById(R.id.include_weiboview).findViewById(R.id.tv_time);
        tvContent = findViewById(R.id.include_weiboview).findViewById(R.id.tv_content);
        btShowComment = findViewById(R.id.include_weiboview).findViewById(R.id.bt_comment);
        btShowComment.setVisibility(View.GONE);

        recyclerView = findViewById(R.id.include_commentview).findViewById(R.id.recyclerView);
        lyComment = findViewById(R.id.include_commentview).findViewById(R.id.ly_comment);
//        lyComment.setVisibility(View.GONE);
        btSendComment = findViewById(R.id.include_commentview).findViewById(R.id.bt_send);
        etComment = findViewById(R.id.include_commentview).findViewById(R.id.et_comment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    //本段可直接Copy，作用是监听RecyclerView是否滑动到底部
    private int mLastVisibleItemPosition;
    private RecyclerView.OnScrollListener monScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (commentAdapter != null) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == commentAdapter.getItemCount()) {
                    //发送网络请求获取更多数据
                    if (mAccessToken != null) {
                        // 已登录，执行请求操作
                        if (currentPage > 0) {
                            currentPage++;
                            WeiboRequests.getCommentWeiboRequest(WbDetailActivity.this, mAccessToken, currentStatus.getId(), currentPage);
                            isLoadMore = true;
                        } else if (currentPage == -1) {
                            Toast.makeText(WbDetailActivity.this, "已到最后一页: " + lastPage, Toast.LENGTH_SHORT).show();
                            isLoadMore = false;
                        }
                    }
                }
            }
        }
    };

    @Override
    public void callBack(Object result, String api) {
        try {
            resultComments = new Gson().fromJson(StringUtils.objectToJsonString(result), Comments.class).getComments();
            if (!isDataInited) {
                comments.addAll(resultComments);
                commentAdapter = new CommentAdapter(this, lyComment, this, comments);
                recyclerView.setAdapter(commentAdapter);
                recyclerView.addOnScrollListener(monScrollListener);
                isDataInited = true;
            }
            lastPage = currentPage;
            if (isLoadMore) {
                Message message = new Message();
                message.what = 0;
                handler.sendMessage(message);
            }
        } catch (IndexOutOfBoundsException e) {
            currentPage = -1;  // 页码等于-1时代表出错，不再自增
            e.printStackTrace();
            Toast.makeText(WbDetailActivity.this, "已到最后一页: " + lastPage, Toast.LENGTH_SHORT).show();
        }
    }

    // 点击评论item的回调
    @Override
    public void callback(Object data) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;//return true;拦截事件传递,从而屏蔽back键。
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }
}
