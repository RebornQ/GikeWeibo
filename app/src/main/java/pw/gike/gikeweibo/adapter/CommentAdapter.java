package pw.gike.gikeweibo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pw.gike.gikeweibo.R;
import pw.gike.gikeweibo.bean.comments.Comment;
import pw.gike.gikeweibo.bean.statuses.Status;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> implements View.OnClickListener{

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener mItemClickListener;

    private List<Comment> comments;

//    private Weibo mWeibo;

    private Context context;

    private LinearLayout lyComment;

    public interface CallbackListener <T> {
        public void callback(T data);
    }

    private CallbackListener callbackListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_status, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        // 为ItemView添加点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Comment comment = comments.get(i);
        Glide.with(context)
                .load(comment.getUser().getProfileImageUrl())

                .into(viewHolder.imHead);
        viewHolder.tvUsername.setText(comment.getUser().getName());
        viewHolder.tvTime.setText(comment.getCreatedAt());
        viewHolder.tvContent.setText(comment.getText());
        viewHolder.btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                lyComment.setVisibility(View.VISIBLE);
                callbackListener.callback(comment.getId());
            }
        });
        viewHolder.itemView.setTag(i);
    }

    public void addList(List<Comment> comments) {
        this.comments.addAll(comments);
        notifyDataSetChanged();
    }

    public void refresh(List<Comment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imHead;
        TextView tvUsername;
        TextView tvTime;
        TextView tvContent;
        ImageButton btComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imHead = itemView.findViewById(R.id.im_head);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvContent = itemView.findViewById(R.id.tv_content);
            btComment = itemView.findViewById(R.id.bt_comment);

        }
    }

    public CommentAdapter(Context context, LinearLayout lyComment, CallbackListener callbackListener, List<Comment> comments) {
        this.comments = comments;
        this.context = context;
        this.lyComment = lyComment;
        this.callbackListener = callbackListener;
    }

}
