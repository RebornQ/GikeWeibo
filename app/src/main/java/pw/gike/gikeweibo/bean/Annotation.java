
package pw.gike.gikeweibo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Annotation {

    @SerializedName("mapi_request")
    @Expose
    private Boolean mapiRequest;

    public Boolean getMapiRequest() {
        return mapiRequest;
    }

    public void setMapiRequest(Boolean mapiRequest) {
        this.mapiRequest = mapiRequest;
    }

}
