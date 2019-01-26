/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagamesserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.Request;
import shared.Response;

/**
 *
 * @author abano
 */



public class RequestHandler extends Thread {

    Socket socket;
    ObjectInputStream objectInputStream;

    RequestHandler(Socket sock) {

        this.socket = sock;
        try {
            InputStream inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.start();
    }

    public void run() {
        Request request;
        try {
            request = (Request) objectInputStream.readObject();
            Response response = request.handle();
            
            // send response back to user
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(response);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        
        // Closing socket after each request 
        terminateSession();
    }

    public void terminateSession() {
        try {
            System.out.println("Closing sockets.");
            objectInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
