
package pw.gike.gikeweibo.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weibo {

    @SerializedName("statuses")
    @Expose
    private List<Status> statuses = null;
    @SerializedName("advertises")
    @Expose
    private List<Object> advertises = null;
    @SerializedName("ad")
    @Expose
    private List<Object> ad = null;
    @SerializedName("hasvisible")
    @Expose
    private Boolean hasvisible;
    @SerializedName("previous_cursor")
    @Expose
    private Long previousCursor;
    @SerializedName("next_cursor")
    @Expose
    private Long nextCursor;
    @SerializedName("total_number")
    @Expose
    private Long totalNumber;
    @SerializedName("interval")
    @Expose
    private Long interval;
    @SerializedName("uve_blank")
    @Expose
    private Long uveBlank;
    @SerializedName("since_id")
    @Expose
    private Long sinceId;
    @SerializedName("max_id")
    @Expose
    private Long maxId;
    @SerializedName("has_unread")
    @Expose
    private Long hasUnread;

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public List<Object> getAdvertises() {
        return advertises;
    }

    public void setAdvertises(List<Object> advertises) {
        this.advertises = advertises;
    }

    public List<Object> getAd() {
        return ad;
    }

    public void setAd(List<Object> ad) {
        this.ad = ad;
    }

    public Boolean getHasvisible() {
        return hasvisible;
    }

    public void setHasvisible(Boolean hasvisible) {
        this.hasvisible = hasvisible;
    }

    public Long getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(Long previousCursor) {
        this.previousCursor = previousCursor;
    }

    public Long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(Long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Long getUveBlank() {
        return uveBlank;
    }

    public void setUveBlank(Long uveBlank) {
        this.uveBlank = uveBlank;
    }

    public Long getSinceId() {
        return sinceId;
    }

    public void setSinceId(Long sinceId) {
        this.sinceId = sinceId;
    }

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    public Long getHasUnread() {
        return hasUnread;
    }

    public void setHasUnread(Long hasUnread) {
        this.hasUnread = hasUnread;
    }

}
