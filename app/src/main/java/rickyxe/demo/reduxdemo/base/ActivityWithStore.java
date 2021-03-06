package rickyxe.demo.reduxdemo.base;

import android.os.Bundle;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class ActivityWithStore<T extends StateObject>
        extends AppCompatActivity
        implements ActivityWithStoreContract<T> {

    private Storeable<T> store = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        store = createStore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyStore();
    }

    @NonNull
    @Override
    public abstract Storeable<T> createStore();

    @NonNull
    public Storeable<T> getStore() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new RuntimeException("getStore not called on Main Thread");
        }
        if (store == null) {
            store = createStore();
        }
        return store;
    }

    @Override
    public void destroyStore() {
        if (store != null) {
            store.onDestroy();
        }
    }
}
