package com.simin.rxjava2.http.encrypt;

import com.simin.rxjava2.utils.LogUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;


public class ApiEncrypt {

    /**
     * 计算签名
     * 例子；
     * Map<String, Object> params=new HashMap<String,Object>();
     * Map<String, Object> input=new HashMap<String ,Object>();
     * Map<String, Object> keys1=new HashMap<String ,Object>();
     * input.put("DWZH", "");
     * input.put("BMBM", "");
     * input.put("JCLX", "");
     * input.put("JCNY", "");
     * <p>
     * keys1.put("TIMESTAMP",new Date().getTime());
     * <p>
     * params.put("keys", JSONObject.toJSONString(keys1));
     * params.put("input", JSONObject.toJSONString(input));
     *
     * @param param map参数  map中的value需要全部转换成json字符串
     * @return
     */
    public static String sign(Map<String, Object> param) {
        Set<String> keysSet = param.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        try {
            for (Object key : keys) {
                if (key.toString().equals("keys")) {
                    continue;
                }
                if (first) {
                    first = false;
                } else {
                    temp.append("&");
                }
                temp.append(key).append("=");
                Object value = param.get(key);
                String valueString = "";
                if (null != value) {
                    valueString = String.valueOf(value);
                }
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            }
            try {
                String log = URLDecoder.decode(temp.toString(), "utf-8");
                log = log + "&SECRET=sdjnrcb_1602#$%?_@%^#@7835";
                LogUtil.d(log);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        // 最后拼接秘钥
        temp.append("&").append("SECRET").append("=").append("sdjnrcb_1602#$%?_@%^#@7835");
        return Md5Utils.hash(temp.toString()).toUpperCase();
    }
}
