package rickyxe.demo.reduxdemo.base;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class Store<STATE> {

    private STATE currentState;

    private Middleware<STATE> middleware;

    private final List<Reducer<STATE>> reducerList = new ArrayList<>();
    private final List<StateListener<STATE>> listenerList = new ArrayList<>();

    public Store(@NonNull STATE initialState) {
        this.currentState = initialState;
    }

    public Store(@NonNull STATE initialState, Middleware<STATE> ... middlewares) {
        this(initialState);

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

    public void addReducer(Reducer<STATE> reducer) {
        if (reducerList.contains(reducer)) {
            return;
        }

        reducerList.add(reducer);
    }

    public STATE getCurrentState() {
        return currentState;
    }

    public void dispatch(Action action) {
        if (middleware != null) {
            this.currentState = middleware.dispatch(this.currentState, action, reducerList);
        } else {
            this.currentState = applyReducer(this.currentState, action, reducerList);
        }

        notifyListeners(this.currentState);
    }

    static <T> T applyReducer(T currentState, Action action, List<Reducer<T>> reducerList) {
        T result = currentState;
        for (Reducer<T> reducer: reducerList) {
            T reduceState = reducer.reduce(currentState, action);
            if (reduceState != null && !reduceState.equals(currentState)) {
                result = reduceState;
            }
        }

        return result;
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
        reducerList.clear();
        listenerList.clear();
    }

    public interface StateListener<T> {
        void onUpdateState(T state);
    }
}
