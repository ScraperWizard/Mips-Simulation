package DataPath.Wire;
import DataPath.DataPathComponent;

public class WireBuilder {
    private byte[] data;
    private DataPathComponent inputComponent;
    private DataPathComponent outputComponent;

    public WireBuilder setData(byte[] data) {
        this.data = data;
        return this;
    }

    public WireBuilder connectFrom(DataPathComponent inputComponent) {
        this.inputComponent = inputComponent;
        return this;
    }

    public WireBuilder connectTo(DataPathComponent outputComponent) {
        this.outputComponent = outputComponent;
        return this;
    }

    public Wire build() {
        // You can add additional validation or customization logic here
        Wire wire = new Wire(data);
        wire.connectFrom(inputComponent);
        wire.connectTo(outputComponent);
        return wire;
    }
}
