package rickyxe.demo.reduxdemo;

import rickyxe.demo.reduxdemo.action.ChangeTime;
import rickyxe.demo.reduxdemo.action.ChangeType;
import rickyxe.demo.reduxdemo.base.Action;
import rickyxe.demo.reduxdemo.base.Reducer;

public class ExampleReducer implements Reducer<ExampleState> {

    @Override
    public ExampleState reduce(ExampleState old, Action action) {
        if (action instanceof ChangeTime) {
            int time = ((ChangeTime) action).data;
            String type = old.type;
            return new ExampleState(time, type);
        } else if (action instanceof ChangeType) {
            int time = old.year;
            String type = ((ChangeType) action).data;
            return new ExampleState(time, type);
        }

        return old;
    }
}
