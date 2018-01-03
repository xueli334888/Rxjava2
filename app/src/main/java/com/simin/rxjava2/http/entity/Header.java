package com.simin.rxjava2.http.entity;

import android.os.Build;

import com.simin.rxjava2.BaseApplication;
import com.simin.rxjava2.cons.SystemConfig;
import com.simin.rxjava2.utils.DeviceInfo;

import java.io.Serializable;


public class Header implements Serializable {

    private static final long serialVersionUID = -2654425717222491543L;

    public String CHANNEL_TYPE = SystemConfig.APP_OS + "Mobile";

    /**
     * 终端应用版本,建议格式：“x.y”，其中x为大版本，y为小版本
     */
    public String VERSION = SystemConfig.APP_VERSION;

    /**
     * 设备信息用来区别终端特性，建议格式：“厂商名,产品名,型号”;如果是基于浏览器的RIA应用，则填写浏览器信息，建议格式：“厂商名,产品名,版本号”
     */
    public String DEVICE = Build.MANUFACTURER + "," + Build.MODEL + "," + Build.PRODUCT;

    /**
     * 操作系统信息,建议格式：“厂商名,产品名,版本号”
     */
    public String PLATFORM = Build.BRAND + "," + SystemConfig.APP_OS + "," + Build.VERSION.RELEASE;

    public String DEVICE_ID = "865002029386653";//DeviceInfo.getIMEI(BaseApplication.getSelf());

}
