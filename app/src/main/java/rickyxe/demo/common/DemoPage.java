package rickyxe.demo.common;

import android.content.Context;

public class DemoPage {
    public String title;
    public String desc;
    public Class pageClazz;

    public DemoPage(String title, String desc, Class pageClazz) {
        this.title = title;
        this.desc = desc;
        this.pageClazz = pageClazz;
    }

    public void openPage(Context context) {

    }
}
