package wifi;

// import lejos.remote.nxt.SocketConnection;
// import lejos.remote.nxt.SocketConnector;
import lejos.remote.nxt.SocketConnection;
// import lejos.remote.nxt.NXTConnection;
import java.net.ServerSocket;
// import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TCPConnection {
    // private ServerSocket serverSocket;
    private ServerSocket serverSocket;
	// private Socket socket;
    // private SocketConnector socket; 
    private SocketConnection socket; 
    // private NXTConnection NXTSocket;
    private PrintWriter out;
    private BufferedReader in;

    public TCPConnection(int port) throws Exception {
        try {
            this.serverSocket = new ServerSocket(port);
            // this.serverSocket = new Socket(host, port);
            // this.socket = this.serverSocket.accept();
            this.socket = new SocketConnection(this.serverSocket.accept());
            // this.socket = new SocketConnector();
            // this.NXTSocket = this.socket.waitForConnection(10000, NXTConnection.RAW);

            this.in = new BufferedReader(new InputStreamReader(this.socket.openInputStream()));
            this.out = new PrintWriter(this.socket.openOutputStream(), true);
        } catch (Exception e) {
            throw e;
        }
    }

    public void send(String msg) throws Exception {
		try {
			this.out.println(msg);
		} catch (Exception e) {
			throw e;
		}
    }

	public String read() throws Exception {
		try {
			return this.in.readLine();
		} catch (Exception e) {
			throw e;
		}
	}

    public void disconnect() throws Exception {
		try {
			this.in.close();
			this.out.close();
			this.serverSocket.close();
		} catch (Exception e) {
			throw e;
		}
        // this.socket.close();
        // this.NXTSocket.close();
    }
}
