package rickyxe.demo.reduxdemo;

import androidx.annotation.NonNull;

public class ExampleState {

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
}
