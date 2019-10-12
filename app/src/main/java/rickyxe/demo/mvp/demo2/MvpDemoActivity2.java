package rickyxe.demo.mvp.demo2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import rickyxe.demo.R;

public class MvpDemoActivity2 extends AppCompatActivity implements MvpContract2.View {

    TextView resultTextView;
    Button loadButton;

    MvpContract2.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mvp_demo_2);
        resultTextView = findViewById(R.id.result_text);
        loadButton = findViewById(R.id.load_data_btn);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData("Livid");
            }
        });

        presenter = new MvpPresenterImpl2(this, this);
        getLifecycle().addObserver(presenter);
    }

    @Override
    public void showUserInfoData(UserApiResult result) {
        Log.d("MVP-Test","activity show data");
        String resultStr = result.id + "\n" + result.username + "\n" + result.website;
        resultTextView.setText(resultStr);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(presenter);
        presenter = null;
        Log.d("MVP-Test","activity destroy");
    }
}
