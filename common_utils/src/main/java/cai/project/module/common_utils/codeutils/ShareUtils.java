package cai.project.module.common_utils.codeutils;

import android.content.Context;
import android.content.Intent;

import cai.project.module.common_utils.R;

/**
 * 使用本地分享工具
 */
public class ShareUtils {

    /**
     * 分享
     */
    public static void shareText(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.action_share));
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.action_share)));
    }
}
