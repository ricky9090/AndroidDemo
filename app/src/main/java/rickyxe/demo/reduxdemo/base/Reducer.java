package rickyxe.demo.reduxdemo.base;

public interface Reducer<T extends StateObject> {

    T reduce(T currentState, Action action);
}
