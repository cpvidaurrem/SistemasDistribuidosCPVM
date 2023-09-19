/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class ServidorSeduca {

    public static void main(String args[]) throws SQLException {
        int port = 6789;
        try {
            DatagramSocket socketUDP = new DatagramSocket(port);
            byte[] bufer = new byte[1000];

            while (true) {
                // Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

                // Leemos una petición del DatagramSocket
                socketUDP.receive(peticion);

                System.out.print("Datagrama recibido del host: " + peticion.getAddress());
                System.out.println(" desde el puerto remoto: " + peticion.getPort());

                String cadena = new String(peticion.getData());
                System.out.println("DATOS:" + cadena);
                //String[] comandos = cadena.split("-");
                //String rude = comandos[1];
                String response;

                String rudeSinEspacios = cadena.replace(" ", "");
                String rudeSinGuiones = rudeSinEspacios.replace("-", "");
                String rude = rudeSinGuiones;

                //conectando y recorriendo BD
                Conexion co = new Conexion();
                co.conectar();

                Connection conexion = co.getCx();
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("Select * from alumnos");

                String comparacion = "";

                while (rs.next()) {
                    String n = rs.getString("nombres").substring(0, 2);
                    String pa = rs.getString("p1erApellido").substring(0, 2);
                    String sa = rs.getString("p2doApellido").substring(0, 2);
                    int fNac = Integer.parseInt(rs.getString("fechaNacimiento").replace("-", ""));
                    System.out.println("fecha Redu: " + fNac);
                    System.out.println("nom Redu: " + n);
                    System.out.println("1er Redu: " + pa);
                    System.out.println("2do Redu: " + sa);

                    String nR = rude.substring(0, 2);
                    String paR = rude.substring(2, 4);
                    String saR = rude.substring(4, 6);
                    String fnR = rude.substring(6).replaceAll("[^0-9]", "");
                    int fnacR = Integer.parseInt(fnR);

                    System.out.println("nom R: " + nR);
                    System.out.println("1er R: " + paR);
                    System.out.println("2do R: " + saR);
                    System.out.println("fecha R: " + fnacR);

                    if (n.equals(nR) && pa.equals(paR) && sa.equals(saR) && fNac == fnacR) {
                        String fNacS = Integer.toString(fNac);

                        comparacion = n + pa + sa + fNacS;

                        System.out.println("ENTRO");
                        System.out.println("comparacion: " + comparacion);
                        break;
                    } else {
                        comparacion = "";
                        System.out.println("NO ENTRO");
                        System.out.println("comparacion: " + comparacion);
                    }
                }

                String rudeFinal = rude.toLowerCase().replaceAll("[^a-z0-9]", "");

                String cSinEspacios = comparacion.replace(" ", "");
                String cSinGuiones = cSinEspacios.replace("-", "");
                String cFinal = cSinGuiones.toLowerCase().replaceAll("[^a-z0-9]", "");

                /*for (String parte : comandos) {
                    System.out.println(parte);
                }*/
                System.out.println("RUDEr:" + rudeFinal);
                System.out.println("RUDEc:" + cFinal);
                boolean r = cFinal.equals(rudeFinal);
                System.out.println("Respuesta:" + r);

                
                if (cFinal.equals(rudeFinal)) { 
                    response = "verificacion:verificado con exito";
                } else {
                    response = "verificacion:no se encontro el titulo de bachiller";
                }

                System.out.println("Respuesta:" + response);

                byte[] mensaje = response.getBytes();

                DatagramPacket respuesta = new DatagramPacket(mensaje, response.length(), peticion.getAddress(), peticion.getPort());
                // Enviamos la respuesta, que es un eco
                socketUDP.send(respuesta);
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}
