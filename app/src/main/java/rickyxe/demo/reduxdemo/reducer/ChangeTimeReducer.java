package rickyxe.demo.reduxdemo.reducer;

import android.util.Log;

import rickyxe.demo.reduxdemo.ExampleState;
import rickyxe.demo.reduxdemo.base.Action;
import rickyxe.demo.reduxdemo.base.Reducer;

import static rickyxe.demo.reduxdemo.ExampleAction.CHANGE_TIME;

public class ChangeTimeReducer implements Reducer<ExampleState> {

    public static final String LOG_TAG = "ChangeTimeReducer";

    @Override
    public ExampleState reduce(ExampleState currentState, Action action) {
        if (action.isTypeOf(CHANGE_TIME)) {
            Log.d(LOG_TAG, "ChangeTimeReducer reduce action !!!");
            int time = (int) action.data;
            String type = currentState.type;
            return new ExampleState(time, type);
        }

        return currentState;
    }
}