package RemoteActors.BaseClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public class ClientConnectionHandler extends Thread {
    private Socket socket;
    private RemoteEmployeeListener listener;

    public ClientConnectionHandler(Socket socket, RemoteEmployeeListener listener) {
        this.socket = socket;
        this.listener = listener;
    }


    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while (socket.isConnected()) { //TODO add some delay in order to simulate a real office (?)
                listener.onEmployeeMessageRecived(this, reader.readLine());
            }
        } catch (IOException e) {
            dispose();
        }
    }

    public void dispose() {
        try {
            if (!this.socket.isClosed())
                this.socket.close();
            this.interrupt();

            listener.onEmployeeDisposed(this);
        } catch (IOException e) {
            //TODO NULL
        }
    }
}
