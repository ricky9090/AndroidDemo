package rickyxe.demo.reduxdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import rickyxe.demo.R;
import rickyxe.demo.reduxdemo.action.ChangeTime;
import rickyxe.demo.reduxdemo.action.ChangeType;
import rickyxe.demo.reduxdemo.base.ActivityWithStore;
import rickyxe.demo.reduxdemo.base.Store;
import rickyxe.demo.reduxdemo.middleware.AnotherLogger;
import rickyxe.demo.reduxdemo.middleware.Logger;

public class ReduxDemoActivity extends ActivityWithStore<ExampleState> implements Store.StateListener<ExampleState> {

    String[] yearArray = {"2017", "2018", "2019"};
    String[] typeArray = {"Android", "iOS"};

    TextView yearText;
    TextView typeText;
    TextView headerText;

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redux_demo);

        initViews();

        Store<ExampleState> store = getStore();
        yearText.setText(store.getCurrentState().year + "年");
        typeText.setText(store.getCurrentState().type + " 类型");

        headerText.setText(store.getCurrentState().toString());


        getStore().attachListener(this);
    }

    @NonNull
    @Override
    protected Store<ExampleState> createStore() {
        ExampleState initState = new ExampleState(2017, "Android");
        ExampleReducer reducer = new ExampleReducer();
        return new Store<>(initState, reducer, new Logger(), new AnotherLogger());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onUpdateState(ExampleState state) {
        yearText.setText(state.year + "年");
        typeText.setText(state.type + " 类型");
        headerText.setText(state.toString());
    }

    private void initViews() {
        yearText = findViewById(R.id.year_button);
        typeText = findViewById(R.id.type_button);
        headerText = findViewById(R.id.header_text);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        yearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearDialog();
            }
        });

        typeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeDialog();
            }
        });


        // ---------- init viewpager ----------
        ReduxFragmentAdapter adapter = new ReduxFragmentAdapter(this);
        viewPager.setAdapter(adapter);
        TabLayoutMediator.TabConfigurationStrategy strategy = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                int name = position + 1;
                tab.setText("Tab: " + name);
            }
        };
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, strategy);
        tabLayoutMediator.attach();
    }

    private void showYearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReduxDemoActivity.this)
                .setItems(yearArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExampleState currentState = getStore().getCurrentState();
                        switch (which) {
                            case 0:
                                getStore().dispatch(new ChangeTime(2017));
                                break;
                            case 1:
                                getStore().dispatch(new ChangeTime(2018));
                                break;
                            case 2:
                                getStore().dispatch(new ChangeTime(2019));
                                break;
                        }
                    }
                });
        builder.show();
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReduxDemoActivity.this)
                .setItems(typeArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExampleState currentState = getStore().getCurrentState();
                        switch (which) {
                            case 0:
                                getStore().dispatch(new ChangeType("Android"));
                                break;
                            case 1:
                                getStore().dispatch(new ChangeType("iOS"));
                                break;
                        }
                    }
                });
        builder.show();
    }
}
