package com.lenovo.video.network.http;

import com.google.gson.Gson;
import com.lenovo.video.app.MyApplication;
import com.lenovo.video.constants.Constants;
import com.lenovo.video.utils.LogUtils;
import com.lenovo.video.utils.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpUtil {
    private static OkHttpClient okHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create(new Gson());
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    static {
        Interceptor mTokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                if (NetworkUtil.isConnected(MyApplication.getInstants())) {
                    HttpUrl httpUrl = request.url().newBuilder()
                            .build();
                    Request authorised = request
                            .newBuilder()
                            .url(httpUrl)
                            .build();
                    Response originalResponse = chain.proceed(authorised);
                    return originalResponse.newBuilder().build();
                } else {
                    //无网络时强制使用缓存数据
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    Response response = chain.proceed(request);
                    int maxStale = 60 * 60 * 24 * 3;
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(new Cache(new File(MyApplication.getInstants().getCacheDir(), "test_cache"), 10 * 1024 * 1024))
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(mTokenInterceptor)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });
        if (Constants.isLog) {
            builder.addInterceptor(interceptor);
        }
        okHttpClient = builder.build();
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static <T> T createApi(Class<T> clazz, String host) {
        return new Retrofit.Builder().client(okHttpClient).baseUrl(host)
                .addConverterFactory(gsonConverterFactory).addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build().create(clazz);

    }

    public static <T> Subscription subscribe(Observable<T> observable, Observer<T> observer) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public static <T> Subscription subscribeBigData(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<T>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.i("bigdata 上报大数据完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("bigdata 上报大数据失败" + e.toString());
                    }

                    @Override
                    public void onNext(T t) {
                        LogUtils.i("bigdata 上报大数据成功");
                    }

                });
    }
}
