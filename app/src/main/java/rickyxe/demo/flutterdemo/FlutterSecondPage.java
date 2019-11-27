package rickyxe.demo.flutterdemo;

import android.content.Context;
import android.content.Intent;

import io.flutter.embedding.android.FlutterActivity;
import rickyxe.demo.MyApplication;
import rickyxe.demo.common.DemoPage;

public class FlutterSecondPage extends DemoPage {

    public FlutterSecondPage(String title, String desc) {
        super(title, desc, null);
    }

    @Override
    public void openPage(Context context) {
        //Intent defaultFlutter = FlutterActivityTwo.withNewEngine().initialRoute("second").build(context);
        Intent defaultFlutter =
                (new Intent(context, FlutterActivityTwo.class))
                        .putExtra("cached_engine_id", MyApplication.FLUTTER_ENGINE_ID_TWO)
                        .putExtra("destroy_engine_with_activity", false)
                        .putExtra("background_mode", FlutterActivity.BackgroundMode.opaque.name());
        context.startActivity(defaultFlutter);
    }
}
