package rickyxe.demo;

import android.content.Context;

import io.flutter.app.FlutterApplication;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.view.FlutterMain;
import rickyxe.demo.reduxdemo.base.StoreManager;

public class MyApplication extends FlutterApplication {

    public static final String FLUTTER_ENGINE_ID = "demo_flutter_engine";
    public static final String FLUTTER_ENGINE_ID_TWO = "demo_flutter_engine_two";

    private StoreManager storeManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // Fix error ensureInitializationComplete must have already been called
        // 不调用此函数，new FlutterEngine 会抛异常
        FlutterMain.ensureInitializationComplete(this, null);
        warmFlutter(this);

        storeManager = new StoreManager();
    }

    /**
     * 缓存Flutter引擎以复用
     */
    public static void warmFlutter(Context context) {
        // Instantiate your FlutterEngine.
        FlutterEngine flutterEngine = new FlutterEngine(context);
        FlutterEngine secondPage = new FlutterEngine(context);

        // Pre-warm your FlutterEngine by starting Dart execution.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
        FlutterEngineCache
                .getInstance()
                .put(FLUTTER_ENGINE_ID, flutterEngine);


        secondPage.getDartExecutor().executeDartEntrypoint(
                new DartExecutor.DartEntrypoint(FlutterMain.findAppBundlePath(), "secondMain")
        );
        FlutterEngineCache
                .getInstance()
                .put(FLUTTER_ENGINE_ID_TWO, secondPage);
    }

    public StoreManager getStoreManager() {
        return storeManager;
    }
}
