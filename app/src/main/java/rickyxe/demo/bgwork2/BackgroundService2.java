package rickyxe.demo.bgwork2;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import rickyxe.demo.util.ServiceUtil;

/**
 * 通过Messenger与Activity传输消息
 */
public class BackgroundService2 extends Service {

    protected static final String TAG = "BackgroundService2";
    protected static final String THREAD_TAG = "worker_thread";

    private HandlerThread workerThread;
    private Looper workerLooper;
    private MyHandler mHandler;
    private Messenger serviceMessenger;
    private Messenger activityMessenger;

    class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BackgroundWorkActivity2.MSG_SERVICE_CONNECTED:
                    activityMessenger = msg.replyTo;
                    sendEmptyMessageDelayed(BackgroundWorkActivity2.MSG_SLEEP_3000, 3000);
                    break;
                case BackgroundWorkActivity2.MSG_PRINT_A:
                    Log.i(TAG, "recive msg print aaaaa");
                    Toast.makeText(getApplicationContext(), "recive msg print aaaaa", Toast.LENGTH_SHORT).show();
                    ServiceUtil.replyMessage(msg.replyTo, BackgroundWorkActivity2.MSG_SERVICE_REVICE_MESSAGE_SUCCESS, null);
                    break;
                case BackgroundWorkActivity2.MSG_PRINT_B:
                    Log.i(TAG, "recive msg print bbbbb");
                    break;
                case BackgroundWorkActivity2.MSG_PRINT_C:
                    Log.i(TAG, "recive msg print ccccc");
                    break;
                case BackgroundWorkActivity2.MSG_SLEEP_3000:
                    ServiceUtil.replyMessage(activityMessenger, BackgroundWorkActivity2.MSG_SLEEP_3000, null);  // 向Activity返回消息
                    sendEmptyMessageDelayed(BackgroundWorkActivity2.MSG_SLEEP_3000, 3000);  // 给自身发消息
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        workerThread = new HandlerThread(THREAD_TAG);
        workerThread.start();

        workerLooper = workerThread.getLooper();
        mHandler = new MyHandler(workerLooper);
        serviceMessenger = new Messenger(mHandler);


        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        workerLooper.quit();
        activityMessenger = null;
        serviceMessenger = null;
        Log.i(TAG, "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return serviceMessenger.getBinder();
    }
}
