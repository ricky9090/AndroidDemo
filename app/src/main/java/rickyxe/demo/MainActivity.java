package rickyxe.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import rickyxe.demo.bgwork.BackgroundWorkActivity;
import rickyxe.demo.bgwork2.BackgroundWorkActivity2;
import rickyxe.demo.contactpick.ContactPickActivity;
import rickyxe.demo.dialogexample.DialogTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button serviceTestBtn;
    Button serviceTest2Btn;
    Button dialogTestBtn;
    Button contactPickBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceTestBtn = findViewById(R.id.btn_service_test);
        serviceTestBtn.setOnClickListener(this);

        serviceTest2Btn = findViewById(R.id.btn_service_test_2);
        serviceTest2Btn.setOnClickListener(this);

        dialogTestBtn = findViewById(R.id.btn_dialog_test_1);
        dialogTestBtn.setOnClickListener(this);

        contactPickBtn = findViewById(R.id.btn_contact_pick);
        contactPickBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_service_test) {
            jumpToActivity(BackgroundWorkActivity.class);
        } else if (id == R.id.btn_service_test_2) {
            jumpToActivity(BackgroundWorkActivity2.class);
        } else if (id == R.id.btn_dialog_test_1) {
            jumpToActivity(DialogTestActivity.class);
        } else if (id == R.id.btn_contact_pick) {
            jumpToActivity(ContactPickActivity.class);
        }
    }

    private void jumpToActivity(Class clazz) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, clazz);
        startActivity(intent);
    }
}
