package rickyxe.demo.reduxdemo.middleware;

import android.util.Log;

import rickyxe.demo.reduxdemo.ExampleState;
import rickyxe.demo.reduxdemo.base.Action;
import rickyxe.demo.reduxdemo.base.Middleware;

public class AnotherLogger extends Middleware<ExampleState> {

    public static final String LOG_TAG = "LoggerB";

    @Override
    public void beforeReduce(ExampleState currentState, Action action) {
        Log.d(LOG_TAG, ">>>> " + currentState.toString() + ", " + action.toString());
    }

    @Override
    public void afterReduce(ExampleState updatedState) {
        Log.d(LOG_TAG, "<<<< " + updatedState.toString());
    }
}
