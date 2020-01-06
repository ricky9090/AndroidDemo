package rickyxe.demo.reduxdemo.base;

import android.os.Looper;

import java.util.HashMap;

public class StoreManager {

    private final HashMap<String, Storeable> storeMap = new HashMap<>();

    public void addStore(Storeable store, String id) {
        checkThread();

        Storeable st = storeMap.get(id);
        if (st != null) {
            return;
        }

        storeMap.put(id, store);
    }

    public Storeable getStoreById(String id) {
        checkThread();

        return storeMap.get(id);
    }

    public void removeStore(String id) {
        checkThread();

        storeMap.remove(id);
    }

    private void checkThread() {
        if (!isMainThread()) {
            throw new RuntimeException("method must called on MainThread");
        }
    }

    private boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
