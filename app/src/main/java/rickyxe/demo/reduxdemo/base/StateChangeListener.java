package rickyxe.demo.reduxdemo.base;

public interface StateChangeListener<S extends StateObject> {
    void onStateChange(S state);
}
