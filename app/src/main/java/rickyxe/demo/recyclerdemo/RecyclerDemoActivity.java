package rickyxe.demo.recyclerdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rickyxe.demo.R;

public class RecyclerDemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_demo);
        recyclerView = findViewById(R.id.recycler_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);
    }
}
