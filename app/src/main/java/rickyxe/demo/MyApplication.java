package rickyxe.demo;

import android.app.Application;

import rickyxe.demo.reduxdemo.base.StoreManager;

public class MyApplication extends Application {

    private StoreManager storeManager;

    @Override
    public void onCreate() {
        super.onCreate();


        storeManager = new StoreManager();
    }

    public StoreManager getStoreManager() {
        return storeManager;
    }
}
