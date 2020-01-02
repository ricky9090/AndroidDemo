package rickyxe.demo.reduxdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rickyxe.demo.R;
import rickyxe.demo.reduxdemo.base.ActivityWithStore;
import rickyxe.demo.reduxdemo.base.Store;

public class ReduxFragment extends Fragment implements Store.StateListener<ExampleState> {

    private static final String LOG_TAG = "ReduxFragment";

    private Store store;
    private String name;

    private TextView titleText;

    public static ReduxFragment createFragment(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("name", tag);
        ReduxFragment fragment = new ReduxFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            name = getArguments().getString("name");
        }

        if (getActivity() instanceof ActivityWithStore) {
            Store _store = ((ActivityWithStore) getActivity()).getStore();
            if (_store.getCurrentState() instanceof ExampleState) {
                store = _store;
                store.attachListener(this);
            }
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
        store.detachListener(this);
        store = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (store != null) {
            ExampleState state = (ExampleState) store.getCurrentState();
            onUpdateState(state);
        }
    }

    @Override
    public void onUpdateState(ExampleState state) {
        if (!isVisible()) {
            Log.d(LOG_TAG, this.toString() + "  not visible !!! update cancel");
            return;

        }
        Log.d(LOG_TAG, this.toString() + "  update data !!!");
        titleText.setText("Fragment : " + name + "\n" + state.toString());

    }

}
