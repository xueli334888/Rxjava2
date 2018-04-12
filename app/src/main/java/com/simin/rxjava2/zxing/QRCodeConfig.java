package com.simin.rxjava2.zxing;

import android.graphics.Color;
import android.text.TextUtils;

/**
 * 作者：Fengsimin on 2018/1/30 11:14
 */

public class QRCodeConfig {

    private String content;
    private int width;
    private int height;
    private String character_set;
    private String error_correction;
    private String margin;
    private int color_black;
    private int color_white;

    private QRCodeConfig(Builder builder) {
        content = builder.content;
        width = builder.width;
        height = builder.height;
        character_set = builder.character_set;
        error_correction = builder.error_correction;
        margin = builder.margin;
        color_black = builder.color_black;
        color_white = builder.color_white;
    }

    public static final class Builder {
        private String content;
        private int width;
        private int height;
        private String character_set;
        private String error_correction;
        private String margin;
        private int color_black;
        private int color_white;

        public Builder() {
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder width(int val) {
            width = val;
            return this;
        }

        public Builder height(int val) {
            height = val;
            return this;
        }

        public Builder character_set(String val) {
            if (TextUtils.isEmpty(val))
                character_set = "UTF-8";
            character_set = val;
            return this;
        }

        public Builder error_correction(String val) {
            if (TextUtils.isEmpty(val))
                error_correction = "H";
            error_correction = val;
            return this;
        }

        public Builder margin(String val) {
            margin = val;
            return this;
        }

        public Builder color_black(int val) {
            color_black = val == 0 ? Color.BLACK : val;
            return this;
        }

        public Builder color_white(int val) {
            color_white = val == 0 ? Color.WHITE : val;
            return this;
        }

        public QRCodeConfig build() {
            return new QRCodeConfig(this);
        }
    }

    public String getContent() {
        return content;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getCharacter_set() {
        return character_set;
    }

    public String getError_correction() {
        return error_correction;
    }

    public String getMargin() {
        return margin;
    }

    public int getColor_black() {
        return color_black;
    }

    public int getColor_white() {
        return color_white;
    }
}
