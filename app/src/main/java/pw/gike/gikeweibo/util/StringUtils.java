package pw.gike.gikeweibo.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class StringUtils {

    public static void putTextIntoClip(Context context, String text){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建ClipData对象
        ClipData clipData = ClipData.newPlainText("Label", text);
        //添加ClipData对象到剪切板中
        clipboardManager.setPrimaryClip(clipData);
    }
}
