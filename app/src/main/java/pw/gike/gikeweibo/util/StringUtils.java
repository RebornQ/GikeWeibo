package pw.gike.gikeweibo.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StringUtils {

    public static void putTextIntoClip(Context context, String text){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建ClipData对象
        ClipData clipData = ClipData.newPlainText("Label", text);
        //添加ClipData对象到剪切板中
        clipboardManager.setPrimaryClip(clipData);
    }

    public static String objectToJsonString(Object object) {
        // 主用于在 request 中把获取的 Map 集合转换为 Json
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(object);
    }
}
