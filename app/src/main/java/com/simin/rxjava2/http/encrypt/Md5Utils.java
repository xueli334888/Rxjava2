package com.simin.rxjava2.http.encrypt;

import com.simin.rxjava2.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static byte[] md5(String s) {
        try {
            messagedigest.reset();
            messagedigest.update(s.getBytes("UTF-8"));
            byte[] messageDigest = messagedigest.digest();
            return messageDigest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String content) {
        try {
            return new String(toHex(md5(content)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return content;
        }
    }

    public static String getFileMD5String(File file) {
        try {
            InputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                messagedigest.update(buffer, 0, numRead);
            }
            fis.close();
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
            return "";
        }
        return toHex(messagedigest.digest());
    }

}
