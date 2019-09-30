package rickyxe.demo.bgwork;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import rickyxe.demo.util.ServiceUtil;

/**
 * 收到消息后通过Activity的Messenger向其返回消息
 */
public class BackgroundService extends Service {

    protected static final String TAG = "BackgroundService";
    protected static final String THREAD_TAG = "worker_thread";

    private HandlerThread workerThread;
    private Looper workerLooper;
    private MyHandler mHandler;

    private String tmp;

    class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BackgroundWorkActivity.MSG_PRINT_A:
                    Log.i(TAG, "recive msg print aaaaa");
                    Toast.makeText(getApplicationContext(), "recive msg print aaaaa", Toast.LENGTH_SHORT).show();
                    ServiceUtil.replyMessage(msg.replyTo, BackgroundWorkActivity.MSG_SERVICE_REVICE_MESSAGE_SUCCESS, null);
                    break;
                case BackgroundWorkActivity.MSG_PRINT_B:
                    Log.i(TAG, "recive msg print bbbbb");
                    break;
                case BackgroundWorkActivity.MSG_PRINT_C:
                    Log.i(TAG, "recive msg print ccccc");
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
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        workerLooper.quit();
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
        return new BackgroundBinder(this);
    }

    public void sendMessage(Message msg) {
        mHandler.sendMessage(msg);
    }

    class BackgroundBinder extends Binder {
        BackgroundService service;

        public BackgroundBinder(BackgroundService service) {
            this.service = service;
        }

        public BackgroundService getService() {
            return service;
        }
    }
}
