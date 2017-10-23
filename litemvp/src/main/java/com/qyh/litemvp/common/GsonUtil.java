package com.qyh.litemvp.common;

import com.google.gson.Gson;

/**
 * @author 邱永恒
 * @time 2017/10/23  16:26
 * @desc Gson单例操作
 */

public class GsonUtil {
    private static Gson gson;

    public static Gson gson() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }
}
