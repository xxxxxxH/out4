package net.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class CopyUtils {

    public static void copy(Context context, String content) {
        if (TextUtils.isEmpty(content) || TextUtils.equals(content, "No Data")) {
            return;
        }

        ClipboardManager manager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Label", content);
        manager.setPrimaryClip(clipData);
        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
    }

}
