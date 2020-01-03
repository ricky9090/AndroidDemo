package rickyxe.demo.reduxdemo.base;

public abstract class Middleware<T> {

    protected Middleware<T> next;

    protected T dispatch(T currentState, Action action, Reducer<T> reducer) {
        beforeReduce(currentState, action);
        T updatedState;
        if (next == null) {
            updatedState = reducer.reduce(currentState, action);
        } else {
            updatedState = next.dispatch(currentState, action, reducer);
        }
        afterReduce(updatedState);

        return updatedState;
    }

    public void beforeReduce(T currentState, Action action) {}

    public void afterReduce(T updatedState) {}
}
