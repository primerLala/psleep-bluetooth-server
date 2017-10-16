package primer.bluetooth.util;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;

/**
 * Created by primer on 17/10/16.
 */
public class RemoteDeviceDiscovery {

    public static RemoteDevice getDeviceByName(String names) throws IOException {
        RemoteDevice[] devices = LocalDevice.getLocalDevice().getDiscoveryAgent().retrieveDevices(1);
        for (RemoteDevice device : devices) {
            if (device.getFriendlyName(false).equals(names)) {
                System.out.println("Device: " + names + " found");
                System.out.println("     address: " + device.getBluetoothAddress());
                return device;
            }
        }
        return null;
    }

    public static RemoteDevice getDevicesByAddress(String address) throws IOException {
        RemoteDevice[] devices = LocalDevice.getLocalDevice().getDiscoveryAgent().retrieveDevices(1);
        for (RemoteDevice device : devices) {
            if (device.getBluetoothAddress().equals(address)) {
                System.out.println("Device: " + device.getFriendlyName(false) + " found");
                System.out.println("     address: " + address);
                return device;
            }
        }
        return null;
    }

    public static RemoteDevice[] getDevices() throws BluetoothStateException, InterruptedException {
        return LocalDevice.getLocalDevice().getDiscoveryAgent().retrieveDevices(1);
    }
}
