
package pw.gike.gikeweibo.bean.statuses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Long id;    // Integer 会报错
    @SerializedName("idstr")
    @Expose
    private String idstr;
    @SerializedName("class")
    @Expose
    private Long _class;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("profile_image_url")
    @Expose
    private String profileImageUrl;
    @SerializedName("cover_image_phone")
    @Expose
    private String coverImagePhone;
    @SerializedName("profile_url")
    @Expose
    private String profileUrl;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("weihao")
    @Expose
    private String weihao;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("followers_count")
    @Expose
    private Long followersCount;
    @SerializedName("friends_count")
    @Expose
    private Long friendsCount;
    @SerializedName("pagefriends_count")
    @Expose
    private Long pagefriendsCount;
    @SerializedName("statuses_count")
    @Expose
    private Long statusesCount;
    @SerializedName("video_status_count")
    @Expose
    private Long videoStatusCount;
    @SerializedName("favourites_count")
    @Expose
    private Long favouritesCount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("following")
    @Expose
    private Boolean following;
    @SerializedName("allow_all_act_msg")
    @Expose
    private Boolean allowAllActMsg;
    @SerializedName("geo_enabled")
    @Expose
    private Boolean geoEnabled;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("verified_type")
    @Expose
    private Long verifiedType;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("insecurity")
    @Expose
    private Insecurity insecurity;
    @SerializedName("ptype")
    @Expose
    private Long ptype;
    @SerializedName("allow_all_comment")
    @Expose
    private Boolean allowAllComment;
    @SerializedName("avatar_large")
    @Expose
    private String avatarLarge;
    @SerializedName("avatar_hd")
    @Expose
    private String avatarHd;
    @SerializedName("verified_reason")
    @Expose
    private String verifiedReason;
    @SerializedName("verified_trade")
    @Expose
    private String verifiedTrade;
    @SerializedName("verified_reason_url")
    @Expose
    private String verifiedReasonUrl;
    @SerializedName("verified_source")
    @Expose
    private String verifiedSource;
    @SerializedName("verified_source_url")
    @Expose
    private String verifiedSourceUrl;
    @SerializedName("verified_state")
    @Expose
    private Long verifiedState;
    @SerializedName("verified_level")
    @Expose
    private Long verifiedLevel;
    @SerializedName("verified_type_ext")
    @Expose
    private Long verifiedTypeExt;
    @SerializedName("pay_remind")
    @Expose
    private Long payRemind;
    @SerializedName("pay_date")
    @Expose
    private String payDate;
    @SerializedName("has_service_tel")
    @Expose
    private Boolean hasServiceTel;
    @SerializedName("verified_reason_modified")
    @Expose
    private String verifiedReasonModified;
    @SerializedName("verified_contact_name")
    @Expose
    private String verifiedContactName;
    @SerializedName("verified_contact_email")
    @Expose
    private String verifiedContactEmail;
    @SerializedName("verified_contact_mobile")
    @Expose
    private String verifiedContactMobile;
    @SerializedName("follow_me")
    @Expose
    private Boolean followMe;
    @SerializedName("like")
    @Expose
    private Boolean like;
    @SerializedName("like_me")
    @Expose
    private Boolean likeMe;
    @SerializedName("online_status")
    @Expose
    private Long onlineStatus;
    @SerializedName("bi_followers_count")
    @Expose
    private Long biFollowersCount;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("star")
    @Expose
    private Long star;
    @SerializedName("mbtype")
    @Expose
    private Long mbtype;
    @SerializedName("mbrank")
    @Expose
    private Long mbrank;
    @SerializedName("block_word")
    @Expose
    private Long blockWord;
    @SerializedName("block_app")
    @Expose
    private Long blockApp;
    @SerializedName("credit_score")
    @Expose
    private Long creditScore;
    @SerializedName("user_ability")
    @Expose
    private Long userAbility;
    @SerializedName("urank")
    @Expose
    private Long urank;
    @SerializedName("story_read_state")
    @Expose
    private Long storyReadState;
    @SerializedName("vclub_member")
    @Expose
    private Long vclubMember;
    @SerializedName("cover_image")
    @Expose
    private String coverImage;
    @SerializedName("dianping")
    @Expose
    private String dianping;
    @SerializedName("cardid")
    @Expose
    private String cardid;
    @SerializedName("avatargj_id")
    @Expose
    private String avatargjId;

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

    public Long getClass_() {
        return _class;
    }

    public void setClass_(Long _class) {
        this._class = _class;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCoverImagePhone() {
        return coverImagePhone;
    }

    public void setCoverImagePhone(String coverImagePhone) {
        this.coverImagePhone = coverImagePhone;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWeihao() {
        return weihao;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Long followersCount) {
        this.followersCount = followersCount;
    }

    public Long getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(Long friendsCount) {
        this.friendsCount = friendsCount;
    }

    public Long getPagefriendsCount() {
        return pagefriendsCount;
    }

    public void setPagefriendsCount(Long pagefriendsCount) {
        this.pagefriendsCount = pagefriendsCount;
    }

    public Long getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(Long statusesCount) {
        this.statusesCount = statusesCount;
    }

    public Long getVideoStatusCount() {
        return videoStatusCount;
    }

    public void setVideoStatusCount(Long videoStatusCount) {
        this.videoStatusCount = videoStatusCount;
    }

    public Long getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(Long favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public Boolean getAllowAllActMsg() {
        return allowAllActMsg;
    }

    public void setAllowAllActMsg(Boolean allowAllActMsg) {
        this.allowAllActMsg = allowAllActMsg;
    }

    public Boolean getGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(Boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Long getVerifiedType() {
        return verifiedType;
    }

    public void setVerifiedType(Long verifiedType) {
        this.verifiedType = verifiedType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Insecurity getInsecurity() {
        return insecurity;
    }

    public void setInsecurity(Insecurity insecurity) {
        this.insecurity = insecurity;
    }

    public Long getPtype() {
        return ptype;
    }

    public void setPtype(Long ptype) {
        this.ptype = ptype;
    }

    public Boolean getAllowAllComment() {
        return allowAllComment;
    }

    public void setAllowAllComment(Boolean allowAllComment) {
        this.allowAllComment = allowAllComment;
    }

    public String getAvatarLarge() {
        return avatarLarge;
    }

    public void setAvatarLarge(String avatarLarge) {
        this.avatarLarge = avatarLarge;
    }

    public String getAvatarHd() {
        return avatarHd;
    }

    public void setAvatarHd(String avatarHd) {
        this.avatarHd = avatarHd;
    }

    public String getVerifiedReason() {
        return verifiedReason;
    }

    public void setVerifiedReason(String verifiedReason) {
        this.verifiedReason = verifiedReason;
    }

    public String getVerifiedTrade() {
        return verifiedTrade;
    }

    public void setVerifiedTrade(String verifiedTrade) {
        this.verifiedTrade = verifiedTrade;
    }

    public String getVerifiedReasonUrl() {
        return verifiedReasonUrl;
    }

    public void setVerifiedReasonUrl(String verifiedReasonUrl) {
        this.verifiedReasonUrl = verifiedReasonUrl;
    }

    public String getVerifiedSource() {
        return verifiedSource;
    }

    public void setVerifiedSource(String verifiedSource) {
        this.verifiedSource = verifiedSource;
    }

    public String getVerifiedSourceUrl() {
        return verifiedSourceUrl;
    }

    public void setVerifiedSourceUrl(String verifiedSourceUrl) {
        this.verifiedSourceUrl = verifiedSourceUrl;
    }

    public Long getVerifiedState() {
        return verifiedState;
    }

    public void setVerifiedState(Long verifiedState) {
        this.verifiedState = verifiedState;
    }

    public Long getVerifiedLevel() {
        return verifiedLevel;
    }

    public void setVerifiedLevel(Long verifiedLevel) {
        this.verifiedLevel = verifiedLevel;
    }

    public Long getVerifiedTypeExt() {
        return verifiedTypeExt;
    }

    public void setVerifiedTypeExt(Long verifiedTypeExt) {
        this.verifiedTypeExt = verifiedTypeExt;
    }

    public Long getPayRemind() {
        return payRemind;
    }

    public void setPayRemind(Long payRemind) {
        this.payRemind = payRemind;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public Boolean getHasServiceTel() {
        return hasServiceTel;
    }

    public void setHasServiceTel(Boolean hasServiceTel) {
        this.hasServiceTel = hasServiceTel;
    }

    public String getVerifiedReasonModified() {
        return verifiedReasonModified;
    }

    public void setVerifiedReasonModified(String verifiedReasonModified) {
        this.verifiedReasonModified = verifiedReasonModified;
    }

    public String getVerifiedContactName() {
        return verifiedContactName;
    }

    public void setVerifiedContactName(String verifiedContactName) {
        this.verifiedContactName = verifiedContactName;
    }

    public String getVerifiedContactEmail() {
        return verifiedContactEmail;
    }

    public void setVerifiedContactEmail(String verifiedContactEmail) {
        this.verifiedContactEmail = verifiedContactEmail;
    }

    public String getVerifiedContactMobile() {
        return verifiedContactMobile;
    }

    public void setVerifiedContactMobile(String verifiedContactMobile) {
        this.verifiedContactMobile = verifiedContactMobile;
    }

    public Boolean getFollowMe() {
        return followMe;
    }

    public void setFollowMe(Boolean followMe) {
        this.followMe = followMe;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public Boolean getLikeMe() {
        return likeMe;
    }

    public void setLikeMe(Boolean likeMe) {
        this.likeMe = likeMe;
    }

    public Long getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Long onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Long getBiFollowersCount() {
        return biFollowersCount;
    }

    public void setBiFollowersCount(Long biFollowersCount) {
        this.biFollowersCount = biFollowersCount;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public Long getMbtype() {
        return mbtype;
    }

    public void setMbtype(Long mbtype) {
        this.mbtype = mbtype;
    }

    public Long getMbrank() {
        return mbrank;
    }

    public void setMbrank(Long mbrank) {
        this.mbrank = mbrank;
    }

    public Long getBlockWord() {
        return blockWord;
    }

    public void setBlockWord(Long blockWord) {
        this.blockWord = blockWord;
    }

    public Long getBlockApp() {
        return blockApp;
    }

    public void setBlockApp(Long blockApp) {
        this.blockApp = blockApp;
    }

    public Long getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Long creditScore) {
        this.creditScore = creditScore;
    }

    public Long getUserAbility() {
        return userAbility;
    }

    public void setUserAbility(Long userAbility) {
        this.userAbility = userAbility;
    }

    public Long getUrank() {
        return urank;
    }

    public void setUrank(Long urank) {
        this.urank = urank;
    }

    public Long getStoryReadState() {
        return storyReadState;
    }

    public void setStoryReadState(Long storyReadState) {
        this.storyReadState = storyReadState;
    }

    public Long getVclubMember() {
        return vclubMember;
    }

    public void setVclubMember(Long vclubMember) {
        this.vclubMember = vclubMember;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDianping() {
        return dianping;
    }

    public void setDianping(String dianping) {
        this.dianping = dianping;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getAvatargjId() {
        return avatargjId;
    }

    public void setAvatargjId(String avatargjId) {
        this.avatargjId = avatargjId;
    }

}
