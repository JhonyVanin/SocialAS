package helper;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SocketIO {

    final private String SERVER = "http://5.61.42.245:9998";
    final private String CUSTOM_KEY = "data";

    private Socket socket;

    private SocketIO() throws URISyntaxException {
        socket = IO.socket(SERVER);
    }

    public void setOnConnectListener(Emitter.Listener onConnectListener) {
        socket.on(Socket.EVENT_CONNECT, onConnectListener);
    }

    public void setOnDisconnectListener(Emitter.Listener onDisconnectListener) {
        socket.on(Socket.EVENT_DISCONNECT, onDisconnectListener);
    }

    public void setOnErrorListener(Emitter.Listener onErrorListener) {
        socket.on(Socket.EVENT_ERROR, onErrorListener);
    }

    public void setOnDataListener(Emitter.Listener onDataListener) {
        socket.on(CUSTOM_KEY, onDataListener);
    }

    public void connect() {
        if (!socket.connected()) {
            socket.connect();
        }
    }

    public void disconnect() {
        if (socket.connected()) {
            socket.disconnect();
        }
    }

    public void send(String msg) {
        socket.emit(CUSTOM_KEY, msg);
    }

    private static SocketIO instance;

    public static SocketIO getInstance() throws URISyntaxException {
        if (instance == null) {
            instance = new SocketIO();
        }
        return instance;
    }
}