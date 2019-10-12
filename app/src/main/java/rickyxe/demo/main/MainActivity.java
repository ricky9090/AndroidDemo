package rickyxe.demo.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rickyxe.demo.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PageListAdapter(this);
        adapter.buildPageList();
        recyclerView.setAdapter(adapter);
    }
}
