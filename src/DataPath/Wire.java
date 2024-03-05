package DataPath;

// This is a wire that connects to components
public class Wire {
    private byte[] data;
    private int length;
    private DataPathComponent inputComponent;
    private DataPathComponent outputComponent;
    public Wire(byte[] data, DataPathComponent inputComponent, DataPathComponent outputComponent) {
        this.data = data;
        this.length = 32;
        this.inputComponent = inputComponent;
        this.outputComponent = outputComponent;
    }

    public int getLength() {
        return length;
    }

    // TODO implement execute input/output calling and handeling

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDataAsHex() {
        String output = "";

        for (byte b : data) {
            // Convert each byte to its hexadecimal representation
            String hex = String.format("%02x", b & 0xFF);
            output += hex;
        }

        return output;
    }

    public String getDataAsString() {
        String output = "";

        for(int i = 0; i < data.length; i++) {
            if(i % 4 == 0) {
                output += " ";
            }

            String byteString = String.format("", data[i]);
            output += byteString;
        }

        return output;
    }
}
