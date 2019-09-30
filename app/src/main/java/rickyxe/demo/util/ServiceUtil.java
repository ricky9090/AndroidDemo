package rickyxe.demo.util;

import android.os.Message;
import android.os.Messenger;

public class ServiceUtil {
    public static void replyMessage(Messenger messenger, int what, Object obj) {
        if (messenger == null) {
            return;
        }
        try {
            Message msg = Message.obtain();
            msg.what = what;
            if (obj != null) {
                msg.obj = obj;
            }
            messenger.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
