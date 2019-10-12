package rickyxe.demo.mvp.demo1;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class MvpPresenterImpl extends MvpContract.Presenter {

    private Context mContext;
    private MvpContract.View mView;
    private Handler mHandler;

    public MvpPresenterImpl(Context mContext, MvpContract.View mView) {
        this.mContext = mContext;
        this.mView = mView;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onCreate() {
        if (mView != null) {
            mView.showData("presenter onCreate");
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
        mContext = null;
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    void loadData() {
        Log.d("MVP-Test", "before current Thread " + Thread.currentThread().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String result = "hello from Thread " + Thread.currentThread().getName();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("MVP-Test", "after current Thread " + Thread.currentThread().getName());
                        if (mView != null) {
                            Log.d("MVP-Test","showData");
                            mView.showData(result);
                        }
                    }
                });
            }
        }).start();
    }
}
