
package pw.gike.gikeweibo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentManageInfo_ {

    @SerializedName("comment_permission_type")
    @Expose
    private Long commentPermissionType;
    @SerializedName("approval_comment_type")
    @Expose
    private Long approvalCommentType;

    public Long getCommentPermissionType() {
        return commentPermissionType;
    }

    public void setCommentPermissionType(Long commentPermissionType) {
        this.commentPermissionType = commentPermissionType;
    }

    public Long getApprovalCommentType() {
        return approvalCommentType;
    }

    public void setApprovalCommentType(Long approvalCommentType) {
        this.approvalCommentType = approvalCommentType;
    }

}
