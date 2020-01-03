package rickyxe.demo.reduxdemo.base;

public interface Reducer<T> {

    T reduce(T currentState, Action action);
}
