package rickyxe.demo.mvp;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class MvpPresenterImpl extends MvpContract.Presenter {

    private Context mContext;
    private MvpContract.View mView;

    public MvpPresenterImpl(Context mContext, MvpContract.View mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void onCreate() {
        if (mView != null) {
            mView.showData("presenter onCreate");
        }
    }

    @Override
    public void onDestroy() {
        if (mView != null) {
            mView.showData("presenter onDestroy");
        }
        mView = null;
        mContext = null;
    }

    @Override
    void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mContext == null) {
                    return;
                }
                ((AppCompatActivity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mView != null) {
                            mView.showData("hello from presenter thread");
                        }
                    }
                });
            }
        }).start();
    }
}
