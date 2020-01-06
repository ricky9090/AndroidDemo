package rickyxe.demo.reduxdemo.base;

public interface Storeable<T> {

    T getCurrentState();

    void addReducer(Reducer<T> reducer);

    void dispatch(Action action);

    void attachListener(StateListener<T> listener);

    void detachListener(StateListener<T> listener);

    void onDestroy();

    interface StateListener<S> {
        void onUpdateState(S state);
    }
}
