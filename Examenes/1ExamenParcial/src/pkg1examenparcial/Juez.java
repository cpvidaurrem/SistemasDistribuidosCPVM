/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1examenparcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHRISTIAN
 */
public class Juez extends UnicastRemoteObject implements IJuez{
    
    ArrayList<Cuenta> aux;
    
    public Juez() throws RemoteException{
        super();
    }

    @Override
    public ArrayList<Cuenta> ConsultarCuentas(String ci, String nombres, String apellidos) throws RemoteException {
        
        // llamada a BANCO MERCANTIL UDP ----------------------------------------
        int puerto = 6789;
        
        try{
            String cadena = "Buscar:" + ci + "-" + nombres + "-" + apellidos;
            String ip = "localhost";
            DatagramSocket socketUDP = new DatagramSocket();
            byte[] mensaje = cadena.getBytes();
            InetAddress hostServidor = InetAddress.getByName(ip);
            
            // Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket peticion = new DatagramPacket(mensaje, cadena.length(), hostServidor, puerto);

            // Enviamos el datagrama
            socketUDP.send(peticion);
            
            // Construimos el DatagramPacket que contendrá la respuesta
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(respuesta);
            
            String respuestaMercantil = new String(respuesta.getData());
            
            Cuenta c = null;
            String rMF = respuestaMercantil.replaceAll("[^a-z0-9]", "");
            String comp = "cuenta1-saldo1:cuenta2-saldo2";
            String compF = comp.replaceAll("[^a-z0-9]", "");
            System.out.println(respuestaMercantil);
            
            if(rMF.equals(compF)){
                c = new Cuenta(Banco.Mercantil, "123456", ci, nombres, apellidos, "1200");
                System.out.println(c.toString());
            }else{
                System.out.println("No se encontro cuenta MERCANTIL");
                c = new Cuenta(Banco.Mercantil, "x", "x", "x", "x", "0");
                System.out.println(c.toString());
                //aux.add(c);
            }
            
            this.aux.add(c);
            
            for (Cuenta cu : this.aux) {
                System.out.println(cu.toString());
            }
            //System.out.println("Tamanio: " + this.aux.size());
            
            // Cerramos el socket
            socketUDP.close();
        }catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        
        
        //Llamada a BANCOBCP TCP ---------------------------------------------------------
        try{
            Socket client = new Socket("localhost", puerto);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            String carnet = ci;
            String nombs = nombres;
            String apelldos = apellidos;
            String cadena = "Buscar:" + carnet + "-" + nombs + "-" + apelldos;
            
            toServer.println(cadena);
            String result = fromServer.readLine();
            
            Cuenta c2 = null;
            
            String cad ="cuenta1-saldo1:cuenta2-saldo2";
            String rMF = result.replaceAll("[^a-z0-9]", "");
            String compF = cad.replaceAll("[^a-z0-9]", "");
            System.out.println(result);
            
            if(rMF.equals(compF)){
                c2 = new Cuenta(Banco.BancoBCP, "456789", ci, nombres, apellidos, "1500");
                System.out.println(c2.toString());
                //aux.add(c2);
            }else{
                System.out.println("No se encontro cuenta BCP");
                c2 = new Cuenta(Banco.BancoBCP, "x", "x", "x", "x", "0");
                System.out.println(c2.toString());
                //aux.add(c2);
            }
            
            this.aux.add(c2);
            
        }catch (IOException ex) {
            Logger.getLogger(Juez.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.aux;
    }

    @Override
    public boolean RetenerMonto(Cuenta cuenta, String monto, String glosa) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
