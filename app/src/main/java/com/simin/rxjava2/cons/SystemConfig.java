package com.simin.rxjava2.cons;

/**
 * App基本配置
 * 打包地址配置
 *
 * @Author Fsm
 */
public class SystemConfig {

    /**
     * 数据存储根路径
     */
    public static String SAVE_ROOT = "Rxjava";

    /**
     * SD卡数据库存储名称
     */
    public static String DB_NAME = SAVE_ROOT + ".db";

    public static String BASE_SERVICE_URL = "http://112.6.110.50:6012/";
    //public static String BASE_SERVICE_URL_DEBUG = "http://112.6.110.50:6012/";
    public static String BASE_SERVICE_URL_DEBUG = "http://112.250.104.167:6311/";

    public static String getBaseServiceUrl() {
        if (DEBUG) {
            return BASE_SERVICE_URL_DEBUG;
        } else {
            return BASE_SERVICE_URL;
        }
    }

    /**
     * 是否为debug模式，投产需要改为false
     */
    public static boolean DEBUG = true;

    /**
     * 客户端版本
     */
    public static String APP_VERSION = "1.0";//AppUtil.getVersionName(BaseApplication.getSelf());

    /**
     * 终端操作系统
     */
    public static final String APP_OS = "android";

}
