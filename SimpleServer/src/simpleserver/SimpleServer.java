/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a-tarek
 */
public class SimpleServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ServerSocket serverSocket = new ServerSocket(9090);
            while (true) {
                Socket socket1 = serverSocket.accept();
                System.err.println("Socket 1> "+ socket1.toString());
                        
                Socket socket2 = serverSocket.accept();
                System.err.println("Socket 2> "+ socket2.toString());
                new GameHandler(socket1, socket2);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
