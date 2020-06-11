package cai.project.module.common_utils.codeutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;


/**打开链接*/
public class LinkUtils {
    /**
     * 使用浏览器打开链接
     */
    public static void openLink(Context context, String content) {
        if (!TextUtils.isEmpty(content) && content.startsWith("http")) {
            Uri issuesUrl = Uri.parse(content);
            Intent intent = new Intent(Intent.ACTION_VIEW, issuesUrl);
            context.startActivity(intent);
        }
    }
}
