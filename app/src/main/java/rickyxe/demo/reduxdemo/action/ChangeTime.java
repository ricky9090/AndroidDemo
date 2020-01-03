package rickyxe.demo.reduxdemo.action;

import androidx.annotation.NonNull;

import rickyxe.demo.reduxdemo.base.Action;

public class ChangeTime extends Action<Integer> {
    public ChangeTime(Integer data) {
        super(data);
    }

    @NonNull
    @Override
    public String toString() {
        return "[Action ChangeTime:" + data + "]";
    }
}
