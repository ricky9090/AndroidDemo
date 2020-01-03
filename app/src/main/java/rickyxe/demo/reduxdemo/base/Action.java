package rickyxe.demo.reduxdemo.base;

public class Action<T> {
    public T data;

    public Action() {
        this.data = null;
    }

    public Action(T data) {
        this.data = data;
    }
}
