package helper;

import java.net.URISyntaxException;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class SocketIO {

	final private String SERVER = "http://5.61.42.245:9998";
	final private String CUSTOM_KEY = "data";

	private Socket socket;

	private Emitter.Listener onConnectListener;
	private Emitter.Listener onDisconnectListener;
	private Emitter.Listener onErrorListener;
	private Emitter.Listener onDataListener;

	private SocketIO() throws URISyntaxException {
		socket = IO.socket(SERVER);

		socket.on(Socket.EVENT_CONNECT, onConnectListener);
		socket.on(Socket.EVENT_DISCONNECT, onDisconnectListener);
		socket.on(Socket.EVENT_ERROR, onErrorListener);
		socket.on(CUSTOM_KEY, onDataListener);

	}

	public void setOnErrorListener(Emitter.Listener onErrorListener) {
		this.onErrorListener = onErrorListener;
	}

	public void setOnConnectListener(Emitter.Listener onConnectListener) {
		this.onConnectListener = onConnectListener;
	}

	public void setOnDisconnectListener(Emitter.Listener onDisconnectListener) {
		this.onDisconnectListener = onDisconnectListener;
	}

	public void setOnDataListener(Emitter.Listener onDataListener) {
		this.onDataListener = onDataListener;
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