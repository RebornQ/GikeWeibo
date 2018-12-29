
package pw.gike.gikeweibo.bean.comments;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pw.gike.gikeweibo.bean.statuses.Annotation;
import pw.gike.gikeweibo.bean.statuses.CommentManageInfo;
import pw.gike.gikeweibo.bean.statuses.User_;
import pw.gike.gikeweibo.bean.statuses.Visible;

public class Status {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("idstr")
    @Expose
    private String idstr;
    @SerializedName("mid")
    @Expose
    private String mid;
    @SerializedName("can_edit")
    @Expose
    private Boolean canEdit;
    @SerializedName("show_additional_indication")
    @Expose
    private Long showAdditionalIndication;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("textLength")
    @Expose
    private Long textLength;
    @SerializedName("source_allowclick")
    @Expose
    private Long sourceAllowclick;
    @SerializedName("source_type")
    @Expose
    private Long sourceType;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("favorited")
    @Expose
    private Boolean favorited;
    @SerializedName("truncated")
    @Expose
    private Boolean truncated;
    @SerializedName("in_reply_to_status_id")
    @Expose
    private String inReplyToStatusId;
    @SerializedName("in_reply_to_user_id")
    @Expose
    private String inReplyToUserId;
    @SerializedName("in_reply_to_screen_name")
    @Expose
    private String inReplyToScreenName;
    @SerializedName("pic_urls")
    @Expose
    private List<Object> picUrls = null;
    @SerializedName("geo")
    @Expose
    private Object geo;
    @SerializedName("is_paid")
    @Expose
    private Boolean isPaid;
    @SerializedName("mblog_vip_type")
    @Expose
    private Long mblogVipType;
    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("annotations")
    @Expose
    private List<Annotation> annotations = null;
    @SerializedName("reposts_count")
    @Expose
    private Long repostsCount;
    @SerializedName("comments_count")
    @Expose
    private Long commentsCount;
    @SerializedName("attitudes_count")
    @Expose
    private Long attitudesCount;
    @SerializedName("pending_approval_count")
    @Expose
    private Long pendingApprovalCount;
    @SerializedName("isLongText")
    @Expose
    private Boolean isLongText;
    @SerializedName("reward_exhibition_type")
    @Expose
    private Long rewardExhibitionType;
    @SerializedName("hide_flag")
    @Expose
    private Long hideFlag;
    @SerializedName("mlevel")
    @Expose
    private Long mlevel;
    @SerializedName("visible")
    @Expose
    private Visible visible;
    @SerializedName("biz_feature")
    @Expose
    private Long bizFeature;
    @SerializedName("hasActionTypeCard")
    @Expose
    private Long hasActionTypeCard;
    @SerializedName("darwin_tags")
    @Expose
    private List<Object> darwinTags = null;
    @SerializedName("hot_weibo_tags")
    @Expose
    private List<Object> hotWeiboTags = null;
    @SerializedName("text_tag_tips")
    @Expose
    private List<Object> textTagTips = null;
    @SerializedName("mblogtype")
    @Expose
    private Long mblogtype;
    @SerializedName("userType")
    @Expose
    private Long userType;
    @SerializedName("more_info_type")
    @Expose
    private Long moreInfoType;
    @SerializedName("positive_recom_flag")
    @Expose
    private Long positiveRecomFlag;
    @SerializedName("content_auth")
    @Expose
    private Long contentAuth;
    @SerializedName("gif_ids")
    @Expose
    private String gifIds;
    @SerializedName("is_show_bulletin")
    @Expose
    private Long isShowBulletin;
    @SerializedName("comment_manage_info")
    @Expose
    private CommentManageInfo commentManageInfo;

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

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Long getShowAdditionalIndication() {
        return showAdditionalIndication;
    }

    public void setShowAdditionalIndication(Long showAdditionalIndication) {
        this.showAdditionalIndication = showAdditionalIndication;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTextLength() {
        return textLength;
    }

    public void setTextLength(Long textLength) {
        this.textLength = textLength;
    }

    public Long getSourceAllowclick() {
        return sourceAllowclick;
    }

    public void setSourceAllowclick(Long sourceAllowclick) {
        this.sourceAllowclick = sourceAllowclick;
    }

    public Long getSourceType() {
        return sourceType;
    }

    public void setSourceType(Long sourceType) {
        this.sourceType = sourceType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Boolean getTruncated() {
        return truncated;
    }

    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    public String getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(String inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    public String getInReplyToUserId() {
        return inReplyToUserId;
    }

    public void setInReplyToUserId(String inReplyToUserId) {
        this.inReplyToUserId = inReplyToUserId;
    }

    public String getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    public void setInReplyToScreenName(String inReplyToScreenName) {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    public List<Object> getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(List<Object> picUrls) {
        this.picUrls = picUrls;
    }

    public Object getGeo() {
        return geo;
    }

    public void setGeo(Object geo) {
        this.geo = geo;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Long getMblogVipType() {
        return mblogVipType;
    }

    public void setMblogVipType(Long mblogVipType) {
        this.mblogVipType = mblogVipType;
    }

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public Long getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(Long repostsCount) {
        this.repostsCount = repostsCount;
    }

    public Long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Long getAttitudesCount() {
        return attitudesCount;
    }

    public void setAttitudesCount(Long attitudesCount) {
        this.attitudesCount = attitudesCount;
    }

    public Long getPendingApprovalCount() {
        return pendingApprovalCount;
    }

    public void setPendingApprovalCount(Long pendingApprovalCount) {
        this.pendingApprovalCount = pendingApprovalCount;
    }

    public Boolean getIsLongText() {
        return isLongText;
    }

    public void setIsLongText(Boolean isLongText) {
        this.isLongText = isLongText;
    }

    public Long getRewardExhibitionType() {
        return rewardExhibitionType;
    }

    public void setRewardExhibitionType(Long rewardExhibitionType) {
        this.rewardExhibitionType = rewardExhibitionType;
    }

    public Long getHideFlag() {
        return hideFlag;
    }

    public void setHideFlag(Long hideFlag) {
        this.hideFlag = hideFlag;
    }

    public Long getMlevel() {
        return mlevel;
    }

    public void setMlevel(Long mlevel) {
        this.mlevel = mlevel;
    }

    public Visible getVisible() {
        return visible;
    }

    public void setVisible(Visible visible) {
        this.visible = visible;
    }

    public Long getBizFeature() {
        return bizFeature;
    }

    public void setBizFeature(Long bizFeature) {
        this.bizFeature = bizFeature;
    }

    public Long getHasActionTypeCard() {
        return hasActionTypeCard;
    }

    public void setHasActionTypeCard(Long hasActionTypeCard) {
        this.hasActionTypeCard = hasActionTypeCard;
    }

    public List<Object> getDarwinTags() {
        return darwinTags;
    }

    public void setDarwinTags(List<Object> darwinTags) {
        this.darwinTags = darwinTags;
    }

    public List<Object> getHotWeiboTags() {
        return hotWeiboTags;
    }

    public void setHotWeiboTags(List<Object> hotWeiboTags) {
        this.hotWeiboTags = hotWeiboTags;
    }

    public List<Object> getTextTagTips() {
        return textTagTips;
    }

    public void setTextTagTips(List<Object> textTagTips) {
        this.textTagTips = textTagTips;
    }

    public Long getMblogtype() {
        return mblogtype;
    }

    public void setMblogtype(Long mblogtype) {
        this.mblogtype = mblogtype;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public Long getMoreInfoType() {
        return moreInfoType;
    }

    public void setMoreInfoType(Long moreInfoType) {
        this.moreInfoType = moreInfoType;
    }

    public Long getPositiveRecomFlag() {
        return positiveRecomFlag;
    }

    public void setPositiveRecomFlag(Long positiveRecomFlag) {
        this.positiveRecomFlag = positiveRecomFlag;
    }

    public Long getContentAuth() {
        return contentAuth;
    }

    public void setContentAuth(Long contentAuth) {
        this.contentAuth = contentAuth;
    }

    public String getGifIds() {
        return gifIds;
    }

    public void setGifIds(String gifIds) {
        this.gifIds = gifIds;
    }

    public Long getIsShowBulletin() {
        return isShowBulletin;
    }

    public void setIsShowBulletin(Long isShowBulletin) {
        this.isShowBulletin = isShowBulletin;
    }

    public CommentManageInfo getCommentManageInfo() {
        return commentManageInfo;
    }

    public void setCommentManageInfo(CommentManageInfo commentManageInfo) {
        this.commentManageInfo = commentManageInfo;
    }

}
