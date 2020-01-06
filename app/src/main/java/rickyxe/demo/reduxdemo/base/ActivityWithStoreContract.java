package rickyxe.demo.reduxdemo.base;

import androidx.annotation.NonNull;

public interface ActivityWithStoreContract<T> {

    @NonNull
    Store<T> createStore();

    void destroyStore();

    @NonNull
    Store<T> getStore();
}
