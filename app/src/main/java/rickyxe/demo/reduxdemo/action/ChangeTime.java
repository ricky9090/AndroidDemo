package rickyxe.demo.reduxdemo.action;

import rickyxe.demo.reduxdemo.base.Action;

public class ChangeTime extends Action<Integer> {
    public ChangeTime(Integer data) {
        super(data);
    }
}
