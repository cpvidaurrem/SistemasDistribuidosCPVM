/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1examenparcial;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHRISTIAN
 */
public class ClienteAsfi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String CI = "11021654";
        String Nombres = "Juan Perez";
        String Apellidos = "Segovia";
        try {

            IJuez juez;
            juez = (IJuez) Naming.lookup("rmi://localhost/Juez");
            juez.ConsultarCuentas(CI, Nombres, Apellidos);
            //ArrayList<Cuenta> lista = (ArrayList<Cuenta>) juez.ConsultarCuentas(CI, Nombres, Apellidos).clone();
            
            //for (Cuenta componente : lista) {
            //    System.out.println(componente.toString());
            //}
            
            //System.out.println(lista.size());

            
        } catch (NotBoundException ex) {
            Logger.getLogger(ServidorAsfi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServidorAsfi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ServidorAsfi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
