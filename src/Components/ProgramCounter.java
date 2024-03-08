package Components;

public class ProgramCounter {
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

    public void setCounter(int branchAddress) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCounter'");
    }
}
