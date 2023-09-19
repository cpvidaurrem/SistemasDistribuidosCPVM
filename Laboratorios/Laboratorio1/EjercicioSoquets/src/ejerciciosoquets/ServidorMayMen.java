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
        String exit = "";
        try {
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con exito");
            Socket client;
            PrintStream toClient;
            client = server.accept(); //conexion
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
            System.out.println("Cliente se conecto");

            while (!exit.equals("salir")) {

                String cadena = fromClient.readLine();
                String[] elementos = cadena.split("-");

                int num1 = Integer.parseInt(elementos[1]);
                int num2 = Integer.parseInt(elementos[2]);
                int num3 = Integer.parseInt(elementos[3]);

                String operacion = String.valueOf(elementos[0]);
                System.out.print(operacion);

                if ("salir".equals(elementos[0]) || "salir".equals(elementos[1]) || "salir".equals(elementos[2]) || "salir".equals(elementos[3])) {
                    exit = "salir";
                } else {

                    if ("mayor".equals(operacion)) {
                        Operacion op = new Operacion(num1, num2, num3);
                        toClient = new PrintStream(client.getOutputStream());
                        toClient.println(op.mayor());
                    } else {
                        if ("menor".equals(operacion)) {
                            Operacion op = new Operacion(num1, num2, num3);
                            toClient = new PrintStream(client.getOutputStream());
                            toClient.println(op.menor());
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorMayMen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
