package rickyxe.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import rickyxe.demo.bgwork.BackgroundWorkActivity;
import rickyxe.demo.bgwork2.BackgroundWorkActivity2;
import rickyxe.demo.constraintdemo.ConstraintDemoActivity;
import rickyxe.demo.contactpick.ContactPickActivity;
import rickyxe.demo.dialogexample.DialogTestActivity;
import rickyxe.demo.lifecycledemo.LifecycleDemoActivity;
import rickyxe.demo.mvp.MvpDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button serviceTestBtn = findViewById(R.id.btn_service_test);
        serviceTestBtn.setOnClickListener(this);

        Button serviceTest2Btn = findViewById(R.id.btn_service_test_2);
        serviceTest2Btn.setOnClickListener(this);

        Button dialogTestBtn = findViewById(R.id.btn_dialog_test_1);
        dialogTestBtn.setOnClickListener(this);

        Button contactPickBtn = findViewById(R.id.btn_contact_pick);
        contactPickBtn.setOnClickListener(this);

        Button lifecycleTest1Btn = findViewById(R.id.btn_lifecycle_test_1);
        lifecycleTest1Btn.setOnClickListener(this);

        Button constraintTestBtn = findViewById(R.id.btn_constraint_test_1);
        constraintTestBtn.setOnClickListener(this);

        Button mvpTestBtn = findViewById(R.id.btn_mvp_test);
        mvpTestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_service_test:
                jumpToActivity(BackgroundWorkActivity.class);
                break;
            case R.id.btn_service_test_2:
                jumpToActivity(BackgroundWorkActivity2.class);
                break;
            case R.id.btn_dialog_test_1:
                jumpToActivity(DialogTestActivity.class);
                break;
            case R.id.btn_contact_pick:
                jumpToActivity(ContactPickActivity.class);
                break;
            case R.id.btn_lifecycle_test_1:
                jumpToActivity(LifecycleDemoActivity.class);
                break;
            case R.id.btn_constraint_test_1:
                jumpToActivity(ConstraintDemoActivity.class);
                break;
            case R.id.btn_mvp_test:
                jumpToActivity(MvpDemoActivity.class);
                break;
            default:
                break;
        }
    }

    private void jumpToActivity(Class clazz) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, clazz);
        startActivity(intent);
    }
}
