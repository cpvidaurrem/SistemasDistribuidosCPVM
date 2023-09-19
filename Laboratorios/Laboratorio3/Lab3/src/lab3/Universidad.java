/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CHRISTIAN
 */
public class Universidad extends UnicastRemoteObject implements IUniversidad {

    public Universidad() throws RemoteException {
        super();
    }

    @Override
    public Diploma emitirDiploma(String CI, String Nombres, String p1erApellido, String p2doApellido, String fecha_nacimiento, String carrera) throws RemoteException {
        Diploma aux = null;
        Respuesta[] listaRespuestas = new Respuesta[3];
        // Llamar a SEGIP ---- RMI ---------------------------------
        ISegip segip;
        try {

            //ISegip segip;
            String ci = CI;
            String nomb = Nombres;
            String apellidos = p1erApellido + " " + p2doApellido;
            segip = (ISegip) Naming.lookup("rmi://localhost/Segip"); // instanciar un objeto remoto
            Respuesta respuestaSegip = segip.Verificar(ci, nomb, apellidos);
            System.out.println("Respuesta SEGIP: " + respuestaSegip.toString());
            listaRespuestas[0] = respuestaSegip;

        } catch (NotBoundException ex) {
            Logger.getLogger(Universidad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Universidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        // ------------------------------------------------

        // llamada a SEDUCA UDP ----------------------------------------
        int puerto = 6789;

        try {
            String rude = Nombres.substring(0, 2) + p1erApellido.substring(0, 2) + p2doApellido.substring(0, 2) + fecha_nacimiento;
            //System.out.println("Rude:" + rude);
            String ip = "localhost";
            DatagramSocket socketUDP = new DatagramSocket();
            byte[] mensaje = rude.getBytes();
            InetAddress hostServidor = InetAddress.getByName(ip);

            // Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket peticion = new DatagramPacket(mensaje, rude.length(), hostServidor, puerto);

            // Enviamos el datagrama
            socketUDP.send(peticion);

            // Construimos el DatagramPacket que contendrá la respuesta
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(respuesta);

            Respuesta rSeduca = null;
            // Enviamos la respuesta del servidor a la salida estandar
            String repuestaSeduca = new String(respuesta.getData());
            //System.out.println(repuestaSeduca);

            
            if (repuestaSeduca.equals("verificacion:verificado con exito")) {
                rSeduca = new Respuesta(true, repuestaSeduca);
            } else {
                rSeduca = new Respuesta(false, repuestaSeduca);
            }

            System.out.println("Respuesta SEDUCA: " + rSeduca.toString());
            listaRespuestas[1] = rSeduca;

            // Cerramos el socket
            socketUDP.close();

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }

        //Llamada a SERECI TCP ---------------------------------------------------------
        //int port = 5002; //CAMBIAMOS port por puerto
        try {
            Socket client = new Socket("localhost", puerto);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //toServer.println("SIS-258");
            String nombs = Nombres;
            String apelldos = p1erApellido + " " + p2doApellido;
            String feNac = fecha_nacimiento;
            String cadena = nombs + " " + apelldos + " " + feNac;
            //System.out.println("cadena es: " + cadena);
            toServer.println(cadena);
            String result = fromServer.readLine();
            //System.out.println("Respuesta SERECI: " + result);

            Respuesta rSereci = null;

            String cad = "verificacion correcta";

            if (result.equals(cad)) {
                rSereci = new Respuesta(true, result);
            } else {
                rSereci = new Respuesta(false, result);
            }

            System.out.println("Respuesta SERECI: " + rSereci.toString());
            listaRespuestas[2] = rSereci;

        } catch (IOException ex) {
            Logger.getLogger(Universidad.class.getName()).log(Level.SEVERE, null, ex);
        }

        String nombreCompleto = Nombres + " " + p1erApellido + " " + p2doApellido;
        String mensaje = "";
        for (Respuesta elemento : listaRespuestas) {
            System.out.println("Elemento: " + elemento.toString());
            
            if (elemento.isEstado() != true) {
                mensaje = mensaje.concat("//").concat(elemento.getMensaje());
                //aux = new Diploma(CI, nombreCompleto, carrera, fecha_nacimiento, mensaje);
                //System.out.println("Mensaje: " + elemento.getMensaje());
            } else {
                //aux = new Diploma(CI, nombreCompleto, carrera, fecha_nacimiento, mensaje);
                mensaje = "";
            }
        }
        aux = new Diploma(CI, nombreCompleto, carrera, fecha_nacimiento, mensaje);
        return aux;
    }
}
