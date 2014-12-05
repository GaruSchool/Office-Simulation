package RemoteActors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public class ConnectedHandler extends Thread {
    private Socket socket;
    private RemoteEmployeeListener listener;

    public ConnectedHandler(Socket socket, RemoteEmployeeListener listener) {
        this.socket = socket;
        this.listener = listener;
    }


    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while (socket.isConnected()) {


            }
        } catch (IOException e) {
            dispose();
        }
    }

    private void dispose() {

        try {
            this.socket.close();
            this.interrupt();
        } catch (IOException e) {
            //TODO NULL
        }
    }
}
