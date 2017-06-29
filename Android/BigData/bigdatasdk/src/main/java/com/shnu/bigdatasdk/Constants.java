package com.shnu.bigdatasdk;

import android.os.Environment;

import java.io.File;

/**
 * Created by Dainel on 2017/6/29.
 */

public class Constants {

    //================ BASEURL ========
    public static final String TEST_URL= "http://test.api.camera.moxiangtv.com/";
    public static final String API_URL= "http://api.camera.moxiangtv.com/";

    //================= PATH ====================
    private static final String RECOMMENDFOLDER = "recommend";//"recommend";
    private static final String PICFOLDER = "pic";
    private static final String MOVFOLDER = "mov";
    private static final String THUMBFOLDER = "thumbnail";

    //
    public static final String PATH_DATA = BaseApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String MEETVR_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "meetvr";

    public static final String PIC_PATH = MEETVR_PATH + File.separator + PICFOLDER;
    public static final String MOV_PATH = MEETVR_PATH + File.separator + MOVFOLDER;
    public static final String RECOMMEND_PATH = MEETVR_PATH + File.separator + RECOMMENDFOLDER;
    public static final String THUMB_PATH = MEETVR_PATH + File.separator + THUMBFOLDER;

    public static final String PATH_CACHE = PATH_DATA + File.separator + "NetCache";

    public static final long THRESHOLD_SPACE_SIZE = 200*1024*1024L;

    //===================Private File Formate ==============================================
    public static final String MJPEG = ".m_jpeg";
    public static final String MJPG = ".m_jpg";
    public static final String MPJPEG = ".mp_jpeg";
    public static final String MPJPG = ".mp_jpg";
    public static final String MMP4 = ".m_mp4";

    public static final String SAMSUNGBRAND = "samsung";
}
