package rickyxe.demo.reduxdemo.action;

import androidx.annotation.NonNull;

import rickyxe.demo.reduxdemo.base.Action;

public class ChangeType extends Action<String> {
    public ChangeType(String data) {
        super(data);
    }

    @NonNull
    @Override
    public String toString() {
        return "[Action ChangeType:" + data + "]";
    }
}
