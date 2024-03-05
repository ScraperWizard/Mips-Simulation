package DataPath;

import DataPath.Wire.Wire;

public abstract class DataPathComponent {
    private Wire inputWire;
    private Wire outputWire;
    public void setInputWire(Wire wire) {
        this.inputWire = wire;
    }

    public void setOuputWire(Wire wire) {
        this.outputWire = wire;
    }
    protected Wire getInputWire() {
        return this.inputWire;
    };
    protected Wire getOutputWire() {
        return this.outputWire;
    };
}
