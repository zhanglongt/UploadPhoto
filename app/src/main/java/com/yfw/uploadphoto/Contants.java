package com.yfw.uploadphoto;

import android.os.Environment;

/**
 * Created by zlt on 2016/10/13.
 */

public class Contants {
    /**
     * 本地缓存目录
     */
    public static final String CACHE_DIR;
    /**
     * 图片缓存目录
     */
    public static final String CACHE_DIR_IMAGE;

    /**
     * 待上传图片缓存目录
     */
    public static final String CACHE_DIR_UPLOADING_IMG;

    static {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MySelf/";
        } else {
            CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + "/MySelf/";
        }
        CACHE_DIR_IMAGE = CACHE_DIR + "/pic";
        CACHE_DIR_UPLOADING_IMG = CACHE_DIR + "/uploading_img";
    }
}
