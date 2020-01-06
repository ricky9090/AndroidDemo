package rickyxe.demo.reduxdemo.reducer;

import android.util.Log;

import rickyxe.demo.reduxdemo.ExampleState;
import rickyxe.demo.reduxdemo.base.Action;
import rickyxe.demo.reduxdemo.base.Reducer;

import static rickyxe.demo.reduxdemo.ExampleAction.CHANGE_TYPE;

public class ChangeTypeReducer implements Reducer<ExampleState> {

    public static final String LOG_TAG = "ChangeTypeReducer";

    @Override
    public ExampleState reduce(ExampleState currentState, Action action) {
        if (action.isTypeOf(CHANGE_TYPE)) {
            Log.d(LOG_TAG, "ChangeTypeReducer reduce action !!!");
            int time = currentState.year;
            String type = (String) action.data;
            return new ExampleState(time, type);
        }

        return currentState;
    }
}
