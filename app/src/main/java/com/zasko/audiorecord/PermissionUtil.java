package com.zasko.audiorecord;

import android.Manifest;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuAn_Zhangsongzhou on 2017/6/12.
 */

public class PermissionUtil {

    private static final String TAG = "PermissionUtil";


    public static int requestPermissions(Context context, String[] permissions, int request) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissionList = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                if (ActivityCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permissions[i]);
                }
            }
            if (permissionList.size() > 0) {
                ActivityCompat.requestPermissions((Activity) context, permissionList.toArray(new String[permissionList.size()]), request);
                return permissionList.size();
            }

        }
        return 0;
    }

    public static boolean isHasSystemAlertWindow(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.SYSTEM_ALERT_WINDOW) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 文件权限读写
     *
     * @param context
     */

    public static void requestSDCardWrite(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!isHasSDCardWritePermission(context)) {
                ActivityCompat.requestPermissions((Activity) context, PermissionManager.PERMISSION_SD_WRITE, PermissionManager.REQUEST_SD_WRITE);
            }
        }
    }

    /**
     * 判断是否有文件读写的权限
     *
     * @param context
     * @return
     */
    public static boolean isHasSDCardWritePermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * 请求地理位置
     *
     * @param context
     */
    public static void requestLocationPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!isHasLocationPermission(context)) {
                ActivityCompat.requestPermissions((Activity) context, PermissionManager.PERMISSION_LOCATION, PermissionManager.REQUEST_LOCATION);
            }
        }
    }

    /**
     * 判断是否有地理位置
     *
     * @param context
     * @return
     */
    public static boolean isHasLocationPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @param context
     * @return
     */
    public static boolean isHasCameraPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestCameraPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!isHasCameraPermission(context)) {
                Log.d(TAG, "requestCameraPermission: ----->" + true);
                ActivityCompat.requestPermissions((Activity) context, PermissionManager.PERMISSION_CAMERA, PermissionManager.REQUEST_CAMERA);
            }
        }
    }

    /**
     * 请求录音权限
     *
     * @param context
     */
    public static void requestRecordPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!isHasRecordPermission(context)) {
                ActivityCompat.requestPermissions((Activity) context, PermissionManager.PERMISSION_RECORD, PermissionManager.REQUEST_RECORD);
            }
        }
    }

    public static boolean isHasRecordPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * @param context
     * @return
     */
    public static boolean isHasReadPhonePermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @param context
     */
    public static void requestReadPhonePermission(Context context) {

        if (Build.VERSION.SDK_INT >= 23) {
            if (!isHasReadPhonePermission(context)) {
                ActivityCompat.requestPermissions((Activity) context, PermissionManager.PERMISSION_READ_PHONE, PermissionManager.REQUEST_READ_PHONE);
            }

        }
    }


    /**
     * 判断是否开启通知权限
     */
    public static boolean isNotificationEnabled(Context context) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return true;
        }

        try {
            AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);

            Class appOpsClass = Class.forName(AppOpsManager.class.getName());

            Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
            int value = (int) opPostNotificationValue.get(Integer.class);
            //app uid
            int uid = context.getApplicationInfo().uid;
            //app 包名
            String pkg = context.getApplicationContext().getPackageName();

            //检查权限
            Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
            return ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
