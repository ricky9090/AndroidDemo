package rickyxe.demo.reduxdemo.action;

import rickyxe.demo.reduxdemo.base.Action;

public class ChangeType extends Action<String> {
    public ChangeType(String data) {
        super(data);
    }
}
