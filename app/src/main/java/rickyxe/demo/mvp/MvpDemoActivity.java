package rickyxe.demo.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import rickyxe.demo.R;

public class MvpDemoActivity extends AppCompatActivity implements MvpContract.View {

    TextView result;
    Button loadButton;
    MvpContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mvp_demo);
        result = findViewById(R.id.result_text);
        loadButton = findViewById(R.id.load_data_btn);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData();
            }
        });

        presenter = new MvpPresenterImpl(this, this);
        getLifecycle().addObserver(presenter);
    }

    @Override
    public void showData(String data) {
        result.setText(data);
    }
}
