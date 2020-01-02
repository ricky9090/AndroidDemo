package rickyxe.demo.reduxdemo.base;

public class Action<T> {
    public T data;

    public Action(T data) {
        this.data = data;
    }
}
