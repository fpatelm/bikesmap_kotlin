package com.faizal.bikesmap

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by fpatel on 21/05/2017.
 */

object Utils {
    fun GetRequestHeader(): OkHttpClient {
        /*       HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);*/
        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS).build()
    }
}
