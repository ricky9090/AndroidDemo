package rickyxe.easynetwork;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofitManager {

    private MyRetrofitManager() {

    }

    private static final HashMap<String, Retrofit> retrofitMap = new HashMap<>();

    public static Retrofit getRetrofit(final String baseUrl) {
        synchronized (MyRetrofitManager.class) {
            Retrofit instance = retrofitMap.get(baseUrl);
            if (instance != null) {
                return instance;
            }

            Retrofit.Builder retrofitBuilder =
                    new Retrofit.Builder().baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(MyHttpClient.getInstance().getOkHttpClient());
            instance = retrofitBuilder.build();

            retrofitMap.put(baseUrl, instance);
            return instance;
        }
    }
}
