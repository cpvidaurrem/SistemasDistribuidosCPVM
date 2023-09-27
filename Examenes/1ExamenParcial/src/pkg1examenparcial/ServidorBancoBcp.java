/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1examenparcial;

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
 * @author CHRISTIAN
 */
public class ServidorBancoBcp {
    
    public static void main(String[] args) {
        int port = 6789;
        ServerSocket server;
        try{
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con exito");
            Socket client;
            PrintStream toClient;
            client = server.accept(); //conexion
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
            
            System.out.println("Cliente se conecto");

            String cadenaRecibida = fromClient.readLine();
            
            String raux= "";
            String comp = "Buscar:11021654-Juan Perez-Segovia";
            String compF = comp.replaceAll("[^a-z0-9]", "");
            String cadenaF = cadenaRecibida.replaceAll("[^a-z0-9]", "");
            boolean res = cadenaF.equals(compF);
            System.out.println("r: " + res);
            
            if (compF.equals(cadenaF)) {
                raux= "cuenta1-saldo1:cuenta2-saldo2";
            }else{
                raux = "";
            }
            
            String r = raux;
            System.out.println(r);
            toClient = new PrintStream(client.getOutputStream());
            toClient.println(r);
            
        }catch (IOException ex) {
            Logger.getLogger(ServidorBancoBcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
