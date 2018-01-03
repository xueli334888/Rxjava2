package com.simin.rxjava2.http.upload;

public interface UploadProgressListener {
    /**
     * 上传进度
     *
     * @param currentBytesCount
     * @param totalBytesCount
     */
    void onProgress(long currentBytesCount, long totalBytesCount);
}