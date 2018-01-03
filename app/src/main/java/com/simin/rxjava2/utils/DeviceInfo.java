package com.simin.rxjava2.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class DeviceInfo {
    public static String IMEI = null;
    public static String IMSI = null;
    public static String SN = null;

    /**
     * @param context
     * @return
     * @Title: getIMEI
     * @Description: 获取手机IMEI号
     */
    public static String getIMEI(Context context) {
        if (IMEI == null) {
            TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String deviceId = telManager.getDeviceId(); //手机上的IMEI号
            if (deviceId == null || "".equals(deviceId)) {
                return "";
            } else {
                IMEI = deviceId;
                return deviceId;
            }
        } else {
            return IMEI;
        }
    }

    public static String getIMSI(Context context) {
        if (IMEI == null) {
            TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String subscriberId = telManager.getSubscriberId(); //手机上的IMEI号
            if (subscriberId == null || "".equals(subscriberId)) {
                return "";
            } else {
                IMSI = subscriberId;
                return subscriberId;
            }
        } else {
            return IMSI;
        }
    }

    public static String getSNAddress(Context context) {
        if (SN == null) {
            TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String deviceId = telManager.getSimSerialNumber();
            if (deviceId == null || "".equals(deviceId)) {
                return "";
            } else {
                return deviceId;
            }
        } else {
            return SN;
        }
    }

    /**
     * @return
     * @Title: getWifiMacAddress
     * @Description: 获取wifi Mac地址
     */
    public static String getWifiMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * @return
     * @Title: getManufacturer
     * @Description: 获取生产厂商名称（如三星 HTC等）
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * @Title: getManufacturer
     * @Description: 获取手机型号
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * @return
     * @Title: getLocalIpAddress
     * @Description: 获取手机IP地址(v4)
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();

                 en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        if (inetAddress.getHostAddress().toString() == null)
                            return "";
                        else
                            return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            return "";
        }
        return "";

    }

    /**
     * @Title: getScreenLayout
     * @Description: 获取手机分辨率(高 * 宽)
     */
    public static String getScreenLayout(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels + "*" + dm.widthPixels;
    }

    /**
     * @Title: getScreenLayout
     * @Description: 获取手机分辨率  宽
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * @Title: getScreenLayout
     * @Description: 获取手机分辨率 高
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * @return
     * @Title: getOSVersion
     * @Description: 获取手机系统版本号
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * @Title: getPhoneDateCid
     * @Description: 获取基站cid
     */
    public static String getPhoneDateCid(Context context) {
        try {
            TelephonyManager mTelNet = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            GsmCellLocation location = (GsmCellLocation) mTelNet.getCellLocation();
            return String.valueOf(location.getCid());
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * @return
     * @Title: getMnc
     * @Description: 手机运营商代码
     */
    public static String getMnc(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String numeric = tm.getSimOperator();
        try {
            String mnc = numeric.substring(3, numeric.length());
            return mnc;
        } catch (Exception e2) {
            return "";
        }
    }

    /**
     * @param context
     * @return
     * @Title: getCountryId
     * @Description: 获取手机国家码
     */
    public static String getNetworkCountryIso(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkCountryIso();
    }


    /**
     * @param context
     * @return
     * @Title: getlanguage
     * @Description: 获取设备语言
     */
    public static String getlanguage(Context context) {
        return context.getResources().getConfiguration().locale.getCountry();
    }
}