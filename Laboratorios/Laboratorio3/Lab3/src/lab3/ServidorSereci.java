/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHRISTIAN
 */
public class ServidorSereci {

    public static void main(String[] args) throws SQLException {

        int port = 6789;
        ServerSocket server;
        //String cadena = "Walter Jhamil Segovia Arellano 11-02-1996";
        //System.out.println("cadena es : " + cadena);

        //conectando y recorriendo BD
        Conexion co = new Conexion();
        co.conectar();

        try {
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con exito");
            Socket client;
            PrintStream toClient;
            client = server.accept(); //conexion
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector

            System.out.println("Cliente se conecto");

            String cadenaRecibida = fromClient.readLine();

            Connection conexion = co.getCx();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("Select * from alumnos");
            String c = "";
            String raux= "";

            while (rs.next()) {
                c = rs.getString("nombres") + " " + rs.getString("p1erApellido")
                        + " " + rs.getString("p2doApellido") + " " + rs.getString("fechaNacimiento");
                System.out.println("cbd: " + c);
                System.out.println("cr: " + cadenaRecibida);

                if (cadenaRecibida.equals(c)) {
                    raux = "verificacion correcta";
                    /*System.out.println(r);
                    toClient = new PrintStream(client.getOutputStream());
                    toClient.println(r);*/
                    break;
                } else {
                    raux = "error fecha nacimiento no correcta";
                    /*System.out.println(r);
                    toClient = new PrintStream(client.getOutputStream());
                    toClient.println(r);*/
                }
            }
            String r = raux;
            System.out.println(r);
            toClient = new PrintStream(client.getOutputStream());
            toClient.println(r);
            //toClient = new PrintStream(client.getOutputStream());
            //toClient.println("Hola Mundo");

        } catch (IOException ex) {
            Logger.getLogger(ServidorSereci.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
