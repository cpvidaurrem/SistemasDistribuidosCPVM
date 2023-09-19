/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab3;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHRISTIAN
 */
public class clienteUniversidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String CI = "1140506";
        String Nombres = "WWalter Jhamil";
        String p1erApellido = "Segovia";
        String p2doApellido = "Arellano";
        String fecha_nacimiento = "11-02-1996";
        String carrera = "Ing. en Ciencias de la Computacion";
        /*String CI = "12518280";
        String Nombres = "Christian Paul";
        String p1erApellido = "Vidaurre";
        String p2doApellido = "Mejia";
        String fecha_nacimiento = "16-03-1998";
        String carrera = "Ing. en Ciencias de la Computacion";*/
        try {
            IUniversidad universidad;

            universidad = (IUniversidad) Naming.lookup("rmi://localhost/Universidad"); // instanciar un objeto remoto
   
            System.out.println(universidad.emitirDiploma(CI, Nombres, p1erApellido, p2doApellido, fecha_nacimiento, carrera));
            
            

        } catch (NotBoundException ex) {
            Logger.getLogger(ServidorUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServidorUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ServidorUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
