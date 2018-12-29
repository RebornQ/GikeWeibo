
package pw.gike.gikeweibo.bean.statuses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Annotation {

    @SerializedName("shooting")
    @Expose
    private Long shooting;
    @SerializedName("mapi_request")
    @Expose
    private Boolean mapiRequest;

    public Long getShooting() {
        return shooting;
    }

    public void setShooting(Long shooting) {
        this.shooting = shooting;
    }

    public Boolean getMapiRequest() {
        return mapiRequest;
    }

    public void setMapiRequest(Boolean mapiRequest) {
        this.mapiRequest = mapiRequest;
    }

}
