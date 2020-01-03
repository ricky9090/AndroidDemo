package rickyxe.demo.reduxdemo.base;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class Store<STATE> {

    private STATE currentState;
    private Reducer<STATE> reducer;

    private Middleware<STATE> middleware;

    private final List<StateListener<STATE>> listenerList = new ArrayList<>();

    public Store(@NonNull STATE initialState, Reducer<STATE> reducer) {
        this.currentState = initialState;
        this.reducer = reducer;
    }

    public Store(@NonNull STATE initialState, Reducer<STATE> reducer, Middleware<STATE> ... middlewares) {
        this(initialState, reducer);

        if (middlewares != null && middlewares.length > 0) {
            this.middleware = middlewares[0];
            Middleware<STATE> last = null;
            for (int i = 0; i < middlewares.length; i++) {
                Middleware<STATE> m = middlewares[i];
                if (last != null) {
                    last.next = m;
                }
                last = m;
            }
        }
    }

    public STATE getCurrentState() {
        return currentState;
    }

    public void dispatch(Action action) {
        if (middleware != null) {
            this.currentState = middleware.dispatch(this.currentState, action, reducer);
        } else {
            this.currentState = applyReducer(this.currentState, action);
        }

        notifyListeners(this.currentState);
    }

    private STATE applyReducer(STATE currentState, Action action) {
        return reducer.reduce(currentState, action);
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
