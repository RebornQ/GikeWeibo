
package pw.gike.gikeweibo.bean.comments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pw.gike.gikeweibo.bean.statuses.User;

public class Comment {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("rootid")
    @Expose
    private Long rootid;
    @SerializedName("floor_number")
    @Expose
    private Long floorNumber;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("disable_reply")
    @Expose
    private Long disableReply;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("mid")
    @Expose
    private String mid;
    @SerializedName("idstr")
    @Expose
    private String idstr;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("appKey")
    @Expose
    private String appKey;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRootid() {
        return rootid;
    }

    public void setRootid(Long rootid) {
        this.rootid = rootid;
    }

    public Long getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Long floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getDisableReply() {
        return disableReply;
    }

    public void setDisableReply(Long disableReply) {
        this.disableReply = disableReply;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

}
