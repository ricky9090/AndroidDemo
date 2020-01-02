package rickyxe.demo.reduxdemo.base;

import java.util.ArrayList;
import java.util.List;

public class Store<STATE> {

    private STATE currentState;
    private Reducer<STATE> reducer;

    List<StateListener<STATE>> listenerList = new ArrayList<>();

    public Store(STATE currentState, Reducer<STATE> reducer) {
        this.currentState = currentState;
        this.reducer = reducer;
    }

    public STATE getCurrentState() {
        return currentState;
    }

    public void dispatch(Action action) {
        this.currentState = applyReducer(this.currentState, action);
        notifyListeners(this.currentState);
    }

    private STATE applyReducer(STATE old, Action action) {
        return reducer.reduce(old, action);
    }

    private void notifyListeners(STATE updatedState) {
        for (StateListener<STATE> listener : listenerList) {
            listener.onUpdateState(updatedState);
        }
    }

    public void attachListener(StateListener<STATE> listener) {
        if (listenerList.contains(listener)) {
            return;
        }

        listenerList.add(listener);
    }

    public void detachListener(StateListener<STATE> listener) {
        if (listenerList.contains(listener)) {
            int index = listenerList.indexOf(listener);
            if (index >= 0) {
                listenerList.remove(index);
            }
        }
    }

    public void onDestroy() {
        listenerList.clear();
    }

    public interface StateListener<T> {
        void onUpdateState(T state);
    }
}
