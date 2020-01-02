package rickyxe.demo.reduxdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class ActivityWithStore<T> extends AppCompatActivity {

    private Store<T> store = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        store = createStore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (store != null) {
            store.onDestroy();
        }
    }

    protected abstract Store<T> createStore();

    public Store<T> getStore() {
        return store;
    }
}
