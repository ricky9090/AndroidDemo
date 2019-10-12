package rickyxe.easynetwork;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyHttpClient {

    private OkHttpClient okHttpClient;

    private MyHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true).build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private static class Holder {
        private static MyHttpClient mInstance = new MyHttpClient();
    }

    public static MyHttpClient getInstance() {
        return Holder.mInstance;
    }
}
