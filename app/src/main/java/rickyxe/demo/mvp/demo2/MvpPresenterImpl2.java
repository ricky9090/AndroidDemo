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

    private static boolean leakMemoryTest = false;  // 为true时跳过onDestory中的处理，延长postDelay时间，让LeakCanary检测出内存泄漏
    private int waitTime = 3000;

    public MvpPresenterImpl2(Context mContext, MvpContract2.View mView) {
        this.mContext = mContext;
        this.mView = mView;
        mHandler = new Handler(Looper.getMainLooper());
        if (leakMemoryTest) {
            waitTime = 30000;
        }
    }

    @Override
    public void onCreate() {
        Log.d("MVP-Test", "presenter onCreate");
    }

    @Override
    public void onDestroy() {
        Log.d("MVP-Test", "presenter onDestroy");
        if (!leakMemoryTest) {
            mContext = null;
            mView = null;
            mHandler.removeCallbacksAndMessages(null);
        }
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
                                if (leakMemoryTest) {
                                    Context badContext = mContext;
                                }
                                Log.d("MVP-Test","post runnable running");
                                if (mView != null && result != null) {
                                    Log.d("MVP-Test","showData");
                                    mView.showUserInfoData(result);
                                } else {
                                    Log.d("MVP-Test","view or result is null, cancel print result");
                                }
                            }
                        }, waitTime);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
