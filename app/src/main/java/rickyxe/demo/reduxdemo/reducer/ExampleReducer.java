package rickyxe.demo.reduxdemo.reducer;

import android.util.Log;

import rickyxe.demo.reduxdemo.ExampleState;
import rickyxe.demo.reduxdemo.base.Action;
import rickyxe.demo.reduxdemo.base.Reducer;

import static rickyxe.demo.reduxdemo.ExampleAction.CHANGE_TIME;
import static rickyxe.demo.reduxdemo.ExampleAction.CHANGE_TYPE;

public class ExampleReducer implements Reducer<ExampleState> {

    public static final String LOG_TAG = "ExampleReducer";

    @Override
    public ExampleState reduce(ExampleState currentState, Action action) {
        Log.d(LOG_TAG, "reduce action !!!");
        if (action.isTypeOf(CHANGE_TIME)) {
            int time = (int) action.data;
            String type = currentState.type;
            return new ExampleState(time, type);
        } else if (action.isTypeOf(CHANGE_TYPE)) {
            int time = currentState.year;
            String type = (String) action.data;
            return new ExampleState(time, type);
        }

        return currentState;
    }
}
