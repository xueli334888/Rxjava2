package com.simin.rxjava2.cons;

/**
 * 作者：wgyscsf on 2017/1/2 18:10
 * 邮箱：wgyscsf@163.com
 * 博客：http://blog.csdn.net/wgyscsf
 */
//常量管理类
public class Constant {



    //文件路径管理类
    public static class FilePath {
        public static final String ROOT_PATH = SystemConfig.SAVE_ROOT + "/";
        public static final String RECORD_DIR = "record/";
        public static final String VEDIO_DIR = "vedio/";
        public static final String IMAGE_DIR = "image/";
        public static final String DB_DIR = "db/";
        public static final String SP_NAME = SystemConfig.SAVE_ROOT + "SP";
    }

    /**
     * 更新下载后台服务 常量管理
     */
    public static class DownLoad {
        public static final String DOWNLOADRECEIVER_CANCEL = "com.jnnx.downloadcancle.receiver";
        public static final String DOWNLOAD_TICKER = "众易贷有新版本了";
        public static final String DOWNLOAD_CONTENTTEXT = "新版本";
        public static final String DOWNLOAD_FILENAME = "jnncmobile.apk";
    }

    /**
     * 其它常用常量
     */
    public static class Normal {
        /**
         * 是否第一次启动app
         */
        public static final String IS_FIRST_APP = "is_first_app";

        public static final String ACCOUNT_NUMBER = "account_number";

        public static final int TAB_FIRST = 0;
        public static final int TAB_SECOND = 1;
        public static final int TAB_THIRD = 2;
        public static final int TAB_FORTH = 3;
        public static final int TAB_FIFTH = 4;

        public static final int PER_PAGE_SIZE = 10;

        public static final int LOGIN_REQUEST_CODE = 200;

        public static final String THUMBNAIL = "THUMBNAIL";//缩略图
        public static final String ARTWORKMASTER = "ARTWORKMASTER";//原图

        public static final String SENDNOTICE = "sendNotice";
        public static final String SENDPLAN = "sendPlan";

        public static final int COLUMN_3 = 3;
        public static final int COLUMN_4 = 4;
    }


}
