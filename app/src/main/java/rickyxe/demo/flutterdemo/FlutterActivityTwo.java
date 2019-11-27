package rickyxe.demo.flutterdemo;

import android.os.Bundle;
import android.util.Log;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;

public class FlutterActivityTwo extends FlutterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        Log.d("Flutter Second", "config flutter engine !!!!!!");
        Log.d("Flutter Second", "initRoute = " + getInitialRoute());
        //flutterEngine.getNavigationChannel().pushRoute("second");
    }
}
