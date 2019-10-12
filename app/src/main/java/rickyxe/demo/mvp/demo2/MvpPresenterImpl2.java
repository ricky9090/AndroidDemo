package rickyxe.demo.mvp.demo2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import rickyxe.easynetwork.MyRetrofitManager;

public class MvpPresenterImpl2 extends MvpContract2.Presenter {

    private Context mContext;
    private MvpContract2.View mView;
    private Handler mHandler;

    public MvpPresenterImpl2(Context mContext, MvpContract2.View mView) {
        this.mContext = mContext;
        this.mView = mView;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onDestroy() {
        mContext = null;
        mView = null;
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    void loadData(final String userName) {
        Log.d("MVP-Test", "before current Thread " + Thread.currentThread().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Retrofit retrofit = MyRetrofitManager.getRetrofit("https://www.v2ex.com");
                    Response<UserApiResult> resultResponse =
                            retrofit.create(V2exApiService.class).getUserInfo(userName).execute();
                    if (resultResponse.isSuccessful()) {
                        final UserApiResult result = resultResponse.body();
                        Log.d("MVP-Test", "after current Thread " + Thread.currentThread().getName());
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("MVP-Test","post runnable running");
                                if (mView != null && result != null) {
                                    Log.d("MVP-Test","showData");
                                    mView.showUserInfoData(result);
                                } else {
                                    Log.d("MVP-Test","view or result is null, cancel print result");
                                }
                            }
                        }, 5000);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
