package me.bzcoder.easyglide.progress;

/**
 * 
 * @author : BaoZhou
 * @date : 2019/6/4 9:25
 */
public interface OnProgressListener {
    void onProgress(boolean isComplete, int percentage, long bytesRead, long totalBytes);
}
