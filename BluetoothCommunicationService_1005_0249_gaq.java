// 代码生成时间: 2025-10-05 02:49:26
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

/**
 * BluetoothCommunicationService.java
 *
 * This service provides functionality for Bluetooth communication.
 *
 * @author Your Name
 * @version 1.0
 */
@Service
public class BluetoothCommunicationService {

    private static final UUID SPP_UUID = new UUID(0x1101);
    private static final String MAC_ADDRESS = "XX:XX:XX:XX:XX:XX"; // Replace with the target device's MAC address
    private DiscoveryAgent agent;
    private RemoteDevice remoteDevice;
    private String connectionString;

    public BluetoothCommunicationService() {
        try {
            agent = LocalDevice.getLocalDevice().getDiscoveryAgent();
            remoteDevice = agent.getRemoteDevice(MAC_ADDRESS);
            connectionString = "btspp://" + MAC_ADDRESS + ":1;uuid=" + SPP_UUID.toString();
        } catch (BluetoothStateException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connects to the Bluetooth device and returns the input and output streams for communication.
     *
     * @return A pair of input and output streams
     * @throws IOException If connection fails or streams cannot be obtained
     */
    public Pair<InputStream, OutputStream> connectToDevice() throws IOException {
        try {
            agent.searchServices(null, remoteDevice, false, false);
            ServiceRecord[] records = agent.retrieveServiceRecords(remoteDevice);
            for (ServiceRecord record : records) {
                if (record.getBluetoothProfileDescriptor().getProfileDescriptorValue().equals(SPP_UUID)) {
                    InputStream inputStream = (InputStream) record.getConnection().openStream(0);
                    OutputStream outputStream = (OutputStream) record.getConnection().openStream(1);
                    return new Pair<>(inputStream, outputStream);
                }
            }
        } catch (IOException e) {
            throw new IOException("Failed to connect to the Bluetooth device", e);
        }
        throw new IOException("No matching service record found");
    }

    /**
     * Sends data to the Bluetooth device.
     *
     * @param data The data to be sent
     * @throws IOException If an I/O error occurs
     */
    public void sendData(byte[] data) throws IOException {
        Pair<InputStream, OutputStream> streams = connectToDevice();
        OutputStream outputStream = streams.getValue();
        outputStream.write(data);
        outputStream.flush();
    }

    /**
     * Receives data from the Bluetooth device.
     *
     * @return The received data as a byte array
     * @throws IOException If an I/O error occurs
     */
    public byte[] receiveData() throws IOException {
        Pair<InputStream, OutputStream> streams = connectToDevice();
        InputStream inputStream = streams.getKey();
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        return Arrays.copyOfRange(buffer, 0, bytesRead);
    }

    // Utility class to hold a pair of values
    public static class Pair<T, U> {
        private T key;
        private U value;

        public Pair(T key, U value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public U getValue() {
            return value;
        }
    }
}
