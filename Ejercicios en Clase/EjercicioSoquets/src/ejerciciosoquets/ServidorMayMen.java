/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosoquets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class ServidorMayMen {

    public static void main(String[] args) {
        int port = 5002;
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            System.out.println(
                    "Se inicio el servidor con exito");
            Socket client;
            PrintStream toClient;
            client = server.accept(); //conexion
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
            System.out.println("Cliente se conecto");
            
            String cadena = fromClient.readLine();
            
            String[] elementos = cadena.split("-");
            System.out.print(elementos[0]);
            
            int num1 = Integer.parseInt(elementos[1]);
            int num2 = Integer.parseInt(elementos[2]);
            int num3 = Integer.parseInt(elementos[3]);
            
            String operacion = String.valueOf(elementos[0]);
            
            if("mayor".equals(operacion)){
                Operacion op = new Operacion(num1,num2 ,num3 );
                toClient = new PrintStream(client.getOutputStream());
                toClient.println(op.mayor());
            } else {
                if("menor".equals(operacion)){
                    Operacion op = new Operacion(num1,num2 ,num3 );
                    toClient = new PrintStream(client.getOutputStream());
                    toClient.println(op.menor());
                }
            }
            
            //Operacion op = new Operacion(num1,num2 ,num3 );
            
            //op.mayor();
            
            //toClient = new PrintStream(client.getOutputStream());
            //toClient.println(op.mayor());

        } catch (IOException ex) {
            Logger.getLogger(ServidorMayMen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
