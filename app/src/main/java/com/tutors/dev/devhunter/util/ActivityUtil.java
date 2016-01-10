package com.tutors.dev.devhunter.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by jay on 2015. 10. 16..
 */
public class ActivityUtil {
    /**
     * AndroidManifest.xml의 parentActivityName 속성이 동작 하려면, activity stack에 activity가 없어야 동작한다.
     * stack을 clear하고 actvity를 start 시킴
     * @param context
     * @param intent
     */
    public static void clearStartActivity(Context context, Intent intent) {
        PendingIntent pendingIntent = TaskStackBuilder.create(context)
                // add all of DetailsActivity's parents to the stack,
                // followed by DetailsActivity itself
                .addNextIntentWithParentStack(intent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            pendingIntent.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * AppDetails Settings 화면 유도
     * @param context
     * @param packageName
     */
    public static void showInstalledAppDetails(Context context, String packageName) {
        Intent intent = new Intent();
        final int apiLevel = Build.VERSION.SDK_INT;
        if (apiLevel >= 9) { // above 2.3
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", packageName, null);
            intent.setData(uri);
        } else { // below 2.3
            final String appPkgName = (apiLevel == 8 ? "pkg"
                    : "com.android.settings.ApplicationPkgName");
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings",
                    "com.android.settings.InstalledAppDetails");
            intent.putExtra(appPkgName, packageName);
        }
        context.startActivity(intent);
    }

}
