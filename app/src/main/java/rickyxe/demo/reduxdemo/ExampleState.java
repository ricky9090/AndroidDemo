package rickyxe.demo.reduxdemo;

import androidx.annotation.NonNull;

import rickyxe.demo.reduxdemo.base.StateObject;

public class ExampleState implements StateObject {

    public int year;
    public String type;

    public ExampleState(int year, String type) {
        this.year = year;
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "State: [Year: " + year + ", Type: " + type + "]";
    }


    @Override
    public StateObject copy(StateObject old) {
        if (old instanceof ExampleState) {
            ExampleState oldState = (ExampleState) old;
            ExampleState state = new ExampleState(oldState.year, oldState.type);
            return state;
        }
        return null;
    }
}
