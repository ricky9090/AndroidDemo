package rickyxe.demo.reduxdemo.base;

import androidx.annotation.NonNull;

public interface ActivityWithStoreContract<T> {

    @NonNull
    Storeable<T> createStore();

    void destroyStore();

    @NonNull
    Storeable<T> getStore();
}
