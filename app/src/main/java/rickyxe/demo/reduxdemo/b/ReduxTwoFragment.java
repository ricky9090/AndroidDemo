package rickyxe.demo.reduxdemo.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rickyxe.demo.MyApplication;
import rickyxe.demo.R;
import rickyxe.demo.reduxdemo.ExampleState;
import rickyxe.demo.reduxdemo.base.StateChangeListener;
import rickyxe.demo.reduxdemo.base.Storeable;

public class ReduxTwoFragment extends Fragment implements StateChangeListener<ExampleState> {

    private static final String LOG_TAG = "ReduxTwoFragment";
    private String name;

    private TextView titleText;

    static ReduxTwoFragment createFragment(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("name", tag);
        ReduxTwoFragment fragment = new ReduxTwoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            name = getArguments().getString("name");
        }

        if (getStore() != null) {
            getStore().addListener(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_redux_demo, container, false);

        titleText = rootView.findViewById(R.id.fragment_text);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getStore() != null) {
            getStore().removeListener(this);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getStore() != null) {
            ExampleState state = getStore().getCurrentState();
            onStateChange(state);
        }
    }

    @Override
    public void onStateChange(ExampleState state) {
        if (!isVisible()) {
            Log.d(LOG_TAG, this.toString() + "  not visible !!! update cancel");
            return;

        }
        Log.d(LOG_TAG, this.toString() + "  update data !!!");
        titleText.setText("Fragment : " + name + "\n" + state.toString());

    }

    @SuppressWarnings(value = "unchecked")
    private Storeable<ExampleState> getStore() {
        try {
            if (getActivity() != null) {
                MyApplication myApplication = (MyApplication) getActivity().getApplication();
                return (Storeable<ExampleState>) myApplication.getStoreManager()
                        .getStoreById(ReduxDemoTwoActivity.STORE_ID);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}