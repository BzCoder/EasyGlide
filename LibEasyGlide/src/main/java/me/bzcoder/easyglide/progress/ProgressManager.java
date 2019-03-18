package me.bzcoder.easyglide.progress;

import android.text.TextUtils;

import java.util.concurrent.ConcurrentHashMap;

import me.bzcoder.easyglide.http.SSLSocketClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 进度监听器管理类
 * 加入图片加载进度监听，加入Https支持
 * @author : BaoZhou
 * @date : 2019/3/17 1:49
 */
public class ProgressManager {

    private static ConcurrentHashMap<String, OnProgressListener> listenersMap = new ConcurrentHashMap<>();
    private static OkHttpClient okHttpClient;

    private ProgressManager() {
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(chain -> {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        return response.newBuilder()
                                .body(new ProgressResponseBody(request.url().toString(), LISTENER, response.body()))
                                .build();
                    })
//                    .socketFactory(SSLSocketClient.getSSLSocketFactory())
//                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                    .build();
        }
        return okHttpClient;
    }

    private static final ProgressResponseBody.InternalProgressListener LISTENER = (url, bytesRead, totalBytes) -> {
        OnProgressListener onProgressListener = getProgressListener(url);
        if (onProgressListener != null) {
            int percentage = (int) ((bytesRead * 1f / totalBytes) * 100f);
            boolean isComplete = percentage >= 100;
            onProgressListener.onProgress(isComplete, percentage, bytesRead, totalBytes);
            if (isComplete) {
                removeListener(url);
            }
        }
    };

    public static void addListener(String url, OnProgressListener listener) {
        if (!TextUtils.isEmpty(url) && listener != null) {
            listenersMap.put(url, listener);
            listener.onProgress(false, 1, 0, 0);
        }
    }

    public static void removeListener(String url) {
        if (!TextUtils.isEmpty(url)) {
            listenersMap.remove(url);
        }
    }

    public static OnProgressListener getProgressListener(String url) {
        if (TextUtils.isEmpty(url) || listenersMap == null || listenersMap.size() == 0) {
            return null;
        }

        OnProgressListener listenerWeakReference = listenersMap.get(url);
        if (listenerWeakReference != null) {
            return listenerWeakReference;
        }
        return null;
    }
}
