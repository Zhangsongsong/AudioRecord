package com.zasko.audiorecord;

import android.Manifest;

/**
 * Created by JuAn_Zhangsongzhou on 2017/5/17.
 */

public final class PermissionManager {

    /**
     * sd卡
     */
    public static final int REQUEST_SD_WRITE = 101;

    /**
     * 地理位置
     */
    public static final int REQUEST_LOCATION = 102;

    /**
     * 相机
     */
    public static final int REQUEST_CAMERA = 103;

    /**
     * 录音
     */
    public static final int REQUEST_RECORD = 104;

    /**
     * 读取手机
     */
    public static final int REQUEST_READ_PHONE = 105;

    /**
     * 悬浮窗通知
     */
    public static final int REQUEST_ALERT_WINDOW = 106;

    public static final String[] PERMISSION_SD_WRITE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final String[] PERMISSION_LOCATION = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    public static final String[] PERMISSION_CAMERA = new String[]{Manifest.permission.CAMERA};
    public static final String[] PERMISSION_RECORD = new String[]{Manifest.permission.RECORD_AUDIO};
    public static final String[] PERMISSION_READ_PHONE = new String[]{Manifest.permission.READ_PHONE_STATE};

}
