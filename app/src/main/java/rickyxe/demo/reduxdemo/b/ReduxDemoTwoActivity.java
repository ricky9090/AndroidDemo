package rickyxe.demo.reduxdemo.b;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import rickyxe.demo.MyApplication;
import rickyxe.demo.R;
import rickyxe.demo.reduxdemo.ExampleAction;
import rickyxe.demo.reduxdemo.ExampleState;
import rickyxe.demo.reduxdemo.base.Action;
import rickyxe.demo.reduxdemo.base.ActivityWithStoreContract;
import rickyxe.demo.reduxdemo.base.Store;
import rickyxe.demo.reduxdemo.base.Storeable;
import rickyxe.demo.reduxdemo.middleware.AnotherLogger;
import rickyxe.demo.reduxdemo.middleware.Logger;
import rickyxe.demo.reduxdemo.reducer.ChangeTimeReducer;
import rickyxe.demo.reduxdemo.reducer.ChangeTypeReducer;

public class ReduxDemoTwoActivity extends AppCompatActivity implements
        Storeable.StateListener<ExampleState>,
        ActivityWithStoreContract<ExampleState> {

    public static final String STORE_ID = "Store_ReduxDemoTwoActivity";

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

        //createStore();
        getStore().attachListener(this);
        initViews();
        yearText.setText(getStore().getCurrentState().year + "年");
        typeText.setText(getStore().getCurrentState().type + " 类型");

        headerText.setText(getStore().getCurrentState().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyStore();
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

        MyApplication myApplication = (MyApplication) getApplication();
        myApplication.getStoreManager().addStore(store, STORE_ID);
        return store;
    }

    @Override
    public void destroyStore() {
        MyApplication myApplication = (MyApplication) getApplication();
        if (myApplication.getStoreManager().getStoreById(STORE_ID) != null) {
            myApplication.getStoreManager().getStoreById(STORE_ID).onDestroy();
        }
        myApplication.getStoreManager().removeStore(STORE_ID);
    }

    @NonNull
    @Override
    @SuppressWarnings(value = "unchecked")
    public Storeable<ExampleState> getStore() {
        MyApplication myApplication = (MyApplication) getApplication();
        Storeable<ExampleState> store;
        try {
            if (myApplication.getStoreManager().getStoreById(STORE_ID) != null) {
                store = (Storeable<ExampleState>) myApplication.getStoreManager().getStoreById(STORE_ID);
                return store;
            }
            store = createStore();
            myApplication.getStoreManager().addStore(store, STORE_ID);
        } catch (ClassCastException e) {
            e.printStackTrace();
            store = createStore();
            myApplication.getStoreManager().addStore(store, STORE_ID);
        }
        return store;
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
        ReduxTwoFragmentAdapter adapter = new ReduxTwoFragmentAdapter(this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(ReduxDemoTwoActivity.this)
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
        AlertDialog.Builder builder = new AlertDialog.Builder(ReduxDemoTwoActivity.this)
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
