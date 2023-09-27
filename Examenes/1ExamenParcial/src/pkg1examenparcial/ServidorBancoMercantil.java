/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1examenparcial;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author CHRISTIAN
 */
public class ServidorBancoMercantil {
    
    public static void main(String[] args) {
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
                System.out.println(cadena);
                String response;
                String comp = "Buscar:11021654-Juan Perez-Segovia";
                String compF = comp.replaceAll("[^a-z0-9]", "");
                String cadenaF = cadena.replaceAll("[^a-z0-9]", "");
                boolean r = cadenaF.equals(compF);
                System.out.println("r: " + r);
                
                
                if(cadenaF.equals(compF)){
                    response = "cuenta1-saldo1:cuenta2-saldo2";
                }else{
                    response = "";
                }
                
                byte[] mensaje = response.getBytes();
                
                 DatagramPacket respuesta = new DatagramPacket(mensaje, response.length(),peticion.getAddress(), peticion.getPort());

                // Enviamos la respuesta, que es un eco
                socketUDP.send(respuesta);
                
            }
            
            
        }catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
    
}
