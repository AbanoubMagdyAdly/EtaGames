package etagamesserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener {

    public ConnectionListener() {

        try {
            ServerSocket ss = new ServerSocket(9090);
            System.out.println("ServerSocket awaiting connections...");
            while (true) {
                Socket socket = ss.accept();
                System.out.println("Connection from " + socket + "!");
                new RequestHandler(socket);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
