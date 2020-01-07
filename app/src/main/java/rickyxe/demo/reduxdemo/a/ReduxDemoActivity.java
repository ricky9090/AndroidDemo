package rickyxe.demo.reduxdemo.a;

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
import rickyxe.demo.reduxdemo.ExampleAction;
import rickyxe.demo.reduxdemo.ExampleState;
import rickyxe.demo.reduxdemo.base.Action;
import rickyxe.demo.reduxdemo.base.ActivityWithStore;
import rickyxe.demo.reduxdemo.base.StateChangeListener;
import rickyxe.demo.reduxdemo.base.Store;
import rickyxe.demo.reduxdemo.base.Storeable;
import rickyxe.demo.reduxdemo.middleware.AnotherLogger;
import rickyxe.demo.reduxdemo.middleware.Logger;
import rickyxe.demo.reduxdemo.reducer.ChangeTimeReducer;
import rickyxe.demo.reduxdemo.reducer.ChangeTypeReducer;

public class ReduxDemoActivity extends ActivityWithStore<ExampleState> implements StateChangeListener<ExampleState> {

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

        Storeable<ExampleState> store = getStore();
        yearText.setText(store.getCurrentState().year + "年");
        typeText.setText(store.getCurrentState().type + " 类型");

        headerText.setText(store.getCurrentState().toString());

        getStore().addListener(this);
    }

    @NonNull
    @Override
    public Storeable<ExampleState> createStore() {
        ExampleState initState = new ExampleState(2017, "Android");
        ChangeTimeReducer timeReducer = new ChangeTimeReducer();
        ChangeTypeReducer typeReducer = new ChangeTypeReducer();
        Storeable<ExampleState> store = new Store<>(initState, new Logger(), new AnotherLogger());
        store.addReducer(timeReducer);
        store.addReducer(typeReducer);
        return store;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getStore().removeListener(this);
        getStore().onDestroy();
    }

    @Override
    public void onStateChange(ExampleState state) {
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
                        switch (which) {
                            case 0:
                                getStore().dispatch(Action.create(ExampleAction.CHANGE_TIME, 2017));
                                break;
                            case 1:
                                getStore().dispatch(Action.create(ExampleAction.CHANGE_TIME, 2018));
                                break;
                            case 2:
                                getStore().dispatch(Action.create(ExampleAction.CHANGE_TIME, 2019));
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
                        switch (which) {
                            case 0:
                                getStore().dispatch(Action.create(ExampleAction.CHANGE_TYPE, "Android"));
                                break;
                            case 1:
                                getStore().dispatch(Action.create(ExampleAction.CHANGE_TYPE, "iOS"));
                                break;
                        }
                    }
                });
        builder.show();
    }
}
