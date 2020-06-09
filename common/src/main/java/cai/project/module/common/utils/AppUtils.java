package cai.project.module.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

public class AppUtils {


    /**
     * 判断手机中是否有对应包名的app
     * @param context
     *          环境变量
     * @param packageName
     *          包名
     * @return
     */
    public static boolean checkApkExist(Context context, String packageName){
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);

            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
