package com.dianbin.latte.net;

import com.dianbin.latte.app.ConfigKeys;
import com.dianbin.latte.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ZHEN on 2018/2/27.
 */

public class RestCreator {

    private static final class ParamHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.SERVICE;
    }

    private static final class RetrofitHolder {
        // FIXME: 2018/2/28 getConfiguration().get(ConfigKeys.API_HOST)
        private static final String BASE_URL = Latte.getHashMapData(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    @SuppressWarnings("unchecked")
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        // FIXME: 2018/2/28 getConfiguration().get(ConfigKeys.INTERCEPTOR)
        private static final ArrayList<Interceptor> INTERCEPTORS =
                (ArrayList<Interceptor>) Latte.getHashMapData(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
