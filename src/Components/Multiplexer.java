package Components;

import DataPath.DataPathComponent;
import DataPath.Wire.Wire;

public class Multiplexer extends DataPathComponent {
    private Wire inputWire;
    private Wire outputWire;
    private String humanNickname;

    public Multiplexer(String humanNickname) {
        this.humanNickname = humanNickname;
        this.inputWire = super.getInputWire();
        this.outputWire = super.getOutputWire();
    }
}
