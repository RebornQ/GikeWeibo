
package pw.gike.gikeweibo.bean.statuses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Visible_ {

    @SerializedName("type")
    @Expose
    private Long type;
    @SerializedName("list_id")
    @Expose
    private Long listId;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

}
