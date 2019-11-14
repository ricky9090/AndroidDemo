package rickyxe.demo.main;

import android.content.Context;
import android.content.Intent;

import io.flutter.embedding.android.FlutterActivity;
import rickyxe.demo.common.DemoPage;

public class FlutterDemoPage extends DemoPage {

    public FlutterDemoPage(String title, String desc) {
        super(title, desc, null);
    }

    @Override
    public void openPage(Context context) {
        Intent defaultFlutter = FlutterActivity.createDefaultIntent(context);
        context.startActivity(defaultFlutter);
    }
}
