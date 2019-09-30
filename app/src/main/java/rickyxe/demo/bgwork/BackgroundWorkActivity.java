package rickyxe.demo.bgwork;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import rickyxe.demo.R;

public class BackgroundWorkActivity extends AppCompatActivity {

    protected static final String TAG = "BackgroundWorkActivity2";

    protected static final int MSG_PRINT_A = 1;
    protected static final int MSG_PRINT_B = 2;
    protected static final int MSG_PRINT_C = 3;
    protected static final int MSG_SERVICE_REVICE_MESSAGE_SUCCESS = 100;

    private TextView instructionText;
    private TextView titleText;
    private Button createServiceBtn;
    private Button unbindServiceBtn;
    private Button sendBtn;
    private BackgroundService bgService;

    private ActivityHandler mHandler;
    private Messenger activityMessenger;

    boolean hasBind = false;

    static class ActivityHandler extends Handler {
        WeakReference<BackgroundWorkActivity> activityRef;

        public ActivityHandler(BackgroundWorkActivity activity) {
            this.activityRef = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (activityRef == null || activityRef.get() == null) {
                return;
            }

            if (msg.what == MSG_SERVICE_REVICE_MESSAGE_SUCCESS) {
                BackgroundWorkActivity activity = activityRef.get();
                activity.printMessage("service have recived message, time: " + System.currentTimeMillis());
            }
        }
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BackgroundService.BackgroundBinder binder = (BackgroundService.BackgroundBinder) service;
            bgService = ((BackgroundService.BackgroundBinder) service).getService();
            Log.i(TAG, "onServiceConnected");
            printMessage("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bgService = null;
            Log.i(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_work);

        mHandler = new ActivityHandler(this);
        activityMessenger = new Messenger(mHandler);

        instructionText = findViewById(R.id.text_instruction);
        instructionText.setText(R.string.service_practice_instruction_1);

        titleText = findViewById(R.id.text_title);
        createServiceBtn = findViewById(R.id.btn_bind_service);
        createServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BackgroundWorkActivity.this, BackgroundService.class);
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                hasBind = true;
            }
        });

        unbindServiceBtn = findViewById(R.id.btn_unbind_service);
        unbindServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasBind) {
                    unbindService(serviceConnection);
                    hasBind = false;
                    printMessage("unbindService");
                }

            }
        });

        sendBtn = findViewById(R.id.btn_send_msg_to_service);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bgService == null) {
                    return;
                }
                Message msg = Message.obtain();
                msg.replyTo = activityMessenger;
                msg.what = MSG_PRINT_A;
                bgService.sendMessage(msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (hasBind) {
            unbindService(serviceConnection);
            hasBind = false;
        }
    }

    private void printMessage(String msg) {
        titleText.setText(msg);
    }
}
