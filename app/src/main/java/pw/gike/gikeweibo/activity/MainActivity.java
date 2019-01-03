package pw.gike.gikeweibo.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NetUtils.CallbackDataListener,WeiboAdapter.CallbackListener  {

    private ImageButton btComment;

    private EditText etComment;

    private LinearLayout lyComment;

    private Long statusId;

    private Weibo resultWeibo;

    private List<Status> statusList = new ArrayList<>();

    private Oauth2AccessToken mAccessToken;

//    private String resultJson;

    private RecyclerView recyclerView;

    private WeiboAdapter weiboAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private boolean isLoadMore = false;

    private boolean isDataInited = false;

    private boolean isRefresh = false;

    private Integer currentPage = 1; // 获取到的微博列表当前页码  // 页码等于-1时代表出错，不再自增

    private Integer lastPage = 1; // 获取到的微博列表最后页码

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            if (msg.what == 0) {
                if (weiboAdapter != null) {
                    statusList.addAll(resultWeibo.getStatuses());
                    weiboAdapter.addList(resultWeibo.getStatuses());
                    weiboAdapter.notifyDataSetChanged();
                }
                isLoadMore = false;
            } else if (msg.what == 1) {
                if (weiboAdapter != null) {
                    statusList.clear();
                    statusList.addAll(resultWeibo.getStatuses());
                    weiboAdapter.refresh(resultWeibo.getStatuses());
                    weiboAdapter.notifyDataSetChanged();
                }
                isRefresh = false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "当前Appid未通过实名，无法使用该功能", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();

        setListener();

        // 先判断是否已登录，登录则直接获取 token 执行请求操作
        mAccessToken = NetUtils.checkAccessToken(this);
        if (mAccessToken != null) {
            // 已登录，执行请求操作
            Toast.makeText(MainActivity.this, "已登录", Toast.LENGTH_SHORT).show();

            WeiboRequests.getWeiboRequest(this, mAccessToken, currentPage);
//            isRefresh = true;
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

        //上拉刷新（有动画）
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mAccessToken != null) {
                    currentPage = 1;
                    isRefresh = true;
                    WeiboRequests.getWeiboRequest(MainActivity.this, mAccessToken, currentPage);
                }
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        swipeRefreshLayout.setRefreshing(false);
                        isRefresh = false;
                    }
                }, 2500);//default = 6000
            }
        });
    }

    private void setListener() {

        // 设置请求的数据返回监听器
        NetUtils.setDataListener(this);

        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = String.valueOf(etComment.getText());
                if (mAccessToken != null) {
                    if (comment != null && !comment.equals("") && currentPage > 0) {
                        WeiboRequests.commentWeiboRequest(MainActivity.this, mAccessToken, comment, statusId);
                        lyComment.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void initData() {
//          statusList.clear();
        if (!isDataInited) {
            statusList = resultWeibo.getStatuses();
            weiboAdapter = new WeiboAdapter(this, lyComment, this, statusList);
            recyclerView.setAdapter(weiboAdapter);
            weiboAdapter.setItemClickListener(new WeiboAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(MainActivity.this, WbDetailActivity.class);
                    intent.putExtra("WeiboItem", new Gson().toJson(statusList.get(position)));
                    startActivity(intent);
                }
            });
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

    //本段可直接Copy，作用是监听RecyclerView是否滑动到底部
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
                    if (mAccessToken != null) {
                        // 已登录，执行请求操作
                        if (currentPage > 0) {
                            currentPage++;
                            WeiboRequests.getWeiboRequest(MainActivity.this, mAccessToken, currentPage);
                            isLoadMore = true;
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
                if (isLoadMore) {
                    Message message = new Message();
                    message.what = 0;
                    handler.sendMessage(message);
                }
                if (isRefresh) {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
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
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_switchaccount) {
            Intent intent = new Intent(MainActivity.this,WBAuthActivity.class);
            startActivity(intent);
        }else if (id == R.id.action_exit) {
            // Kill JVM
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_index) {
//            Intent intent = new Intent(MainActivity.this,IndexActivity.class);
//            startActivity(intent);
        } else if (id == R.id.nav_msg) {
            showAlert("当前Appid未通过实名，调用接口失败，无法使用该功能");
        } else if (id == R.id.nav_user) {
            showAlert("当前Appid未通过实名，调用接口失败，无法使用该功能");
        } else if (id == R.id.nav_set) {
            Intent intent = new Intent(MainActivity.this,WBAuthActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            showAlert("由于GikeWeibo尚未上线，暂时无法分享");
        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_exit) {
            // Kill JVM
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showAlert(String text){
        new AlertDialog.Builder(MainActivity.this).setTitle("GikeWeibo")
                .setMessage(text)
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
