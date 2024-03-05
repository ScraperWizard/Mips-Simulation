package Components;

import DataPath.DataPathComponent;

public class ProgramCounter extends DataPathComponent {
    private int counter;

    public ProgramCounter() {
        this.counter = 0;
    }

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
