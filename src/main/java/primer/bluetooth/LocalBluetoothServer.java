package primer.bluetooth;

import java.io.IOException;

/**
 * Created by primer on 17/10/16.
 */
public class LocalBluetoothServer {
    public static void main(String[] args) throws IOException {
        Thread bluetoothThread = new Thread(new BluetoothServer());
        bluetoothThread.start();
    }
}
