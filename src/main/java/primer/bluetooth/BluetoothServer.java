package primer.bluetooth;

import java.io.IOException;
import java.io.InputStream;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import primer.bluetooth.util.RemoteDeviceDiscovery;

/**
 * Created by primer on 17/10/16.
 */
public class BluetoothServer implements Runnable {
    //LocalDevice local = null;
    // 流连接通知 用于创建流连接
    //private StreamConnectionNotifier connectionNotifier = null;
    // 流连接
    private StreamConnection connection = null;
    // 接受数据字节流
    private byte[] acceptedByteArray = new byte[12];
    // 读取（输入）流
    private InputStream inputStream = null;

    public BluetoothServer() {
        try {
            RemoteDevice device = RemoteDeviceDiscovery.getDeviceByName("Dean Zheng's iPhone8");
            System.out.println("waiting for connection...");
            connection = (StreamConnection)Connector.open("btspp://" + device.getBluetoothAddress() + ":1");
            System.out.println("connected device: " + device.getFriendlyName(false));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String inSTR = null;
            // 持续保持着监听客户端的连接请求
            while (true) {
                //// 获取流连接
                //System.out.println("waiting for connection...");
                //connection = connectionNotifier.acceptAndOpen();
                //System.out.println("connected device.");

                // 获取流通道
                System.out.println("waiting for input...");
                inputStream = connection.openInputStream();

                // 读取字节流
                while (inputStream.read(acceptedByteArray) != -1) {
                    inSTR = new String(acceptedByteArray);
                    System.out.println("receive message: " + inSTR);
                    if (inSTR.contains("EXIT")) {
                        // 手机客户端退出则关闭连接通道。
                        inputStream.close();
                        if (connection != null) {
                            connection.close();
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
