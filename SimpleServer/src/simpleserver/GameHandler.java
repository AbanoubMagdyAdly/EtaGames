/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import simpleserver.GameHandler.oRunnable;
import simpleserver.GameHandler.xRunnable;

/**
 *
 * @author a-tarek
 */
public class GameHandler {

    Socket xSocket;
    Socket oSocket;

    DataInputStream xInput;
    DataInputStream oInput;

    PrintStream xOutput;
    PrintStream oOutput;

    public GameHandler(Socket s1, Socket s2) {
        try {
            System.out.println("GAME HANDLER STARTED");
            this.xSocket = s1;
            this.oSocket = s2;

            oInput = new DataInputStream(oSocket.getInputStream());
            xInput = new DataInputStream(xSocket.getInputStream());

            xOutput = new PrintStream(xSocket.getOutputStream());
            oOutput = new PrintStream(oSocket.getOutputStream());
            
            xOutput.println("X");
            oOutput.println("O");
            
            Thread xThread = new Thread(new xRunnable());
            Thread oThread = new Thread(new oRunnable());
            
            xThread.start();
            oThread.start();
            
        } catch (IOException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class xRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    String str = xInput.readLine();
                    System.out.println("X: "+ str);
                    
                    if (str != null) {
                        oOutput.println(str);
                    }
                    else{
                            kill();
                            break;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    kill();
                    break;
                }
            }
        }
    }
    
    
    public class oRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    String str = oInput.readLine();
                    System.out.println("O: "+ str);
                    if (str != null) {
                        xOutput.println(str);
                    }
                    else{
                            kill();
                            break;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    kill();
                    break;
                }
            }
        }
    }
    
    
    public void kill(){
        try {
            xInput.close();
            oInput.close();
            xOutput.close();
            oOutput.close();
        } catch (IOException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setXSocket(Socket s) {
        this.xSocket = s;
    }

    public void setOSocket(Socket s) {
        this.oSocket = s;
    }

}
