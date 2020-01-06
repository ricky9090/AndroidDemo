package rickyxe.demo.reduxdemo.base;

import java.util.List;

public abstract class Middleware<T> {

    protected Middleware<T> next;

    protected T dispatch(T currentState, Action action, List<Reducer<T>> reducerList) {
        beforeReduce(currentState, action);
        T updatedState;
        if (next == null) {
            updatedState = Store.applyReducer(currentState, action, reducerList);
        } else {
            updatedState = next.dispatch(currentState, action, reducerList);
        }
        afterReduce(updatedState);

        return updatedState;
    }

    public void beforeReduce(T currentState, Action action) {}

    public void afterReduce(T updatedState) {}
}
