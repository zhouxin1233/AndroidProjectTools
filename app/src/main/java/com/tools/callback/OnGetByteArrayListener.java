package com.tools.callback;

/**
 * 处理OkHttp获取到字节数组
 */
public interface OnGetByteArrayListener {
    void onResponse(byte[] result);

}