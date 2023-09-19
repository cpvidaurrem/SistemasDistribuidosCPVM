/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
public class Segip extends UnicastRemoteObject implements ISegip {

    public Segip() throws RemoteException {
        super();
    }

    @Override
    public Respuesta Verificar(String CI, String Nombres, String Apellido) throws RemoteException {
        Respuesta respuesta = null;
        String ci = "";
        String nbs = "";
        String aplls = "";
        //conectando y recorriendo BD
        Conexion co = new Conexion();
        co.conectar();
        //co.consultar();
        try {
            Connection conexion = co.getCx();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("Select * from alumnos ");
            
            while (rs.next()) {
                String cApell = rs.getString("p1erApellido") + " " + rs.getString("p2doApellido");
                if (rs.getString("ci").equals(CI) && rs.getString("nombres").equals(Nombres) && cApell.equals(Apellido)) {
                    ci = rs.getString("ci");
                    nbs = rs.getString("nombres");
                    aplls = rs.getString("p1erApellido") + " " + rs.getString("p2doApellido");

                    System.out.println("ci: " + ci);
                    System.out.println("nbs: " + nbs);
                    System.out.println("aplls: " + aplls);
                    break;
                } else {
                    ci = "";
                    nbs = "";
                    aplls = "";
                    System.out.println("ci: " + ci);
                    System.out.println("nbs: " + nbs);
                    System.out.println("aplls: " + aplls);
                }
            }

            if (CI.equals(ci) && Nombres.equals(nbs) && Apellido.equals(aplls)) {
                respuesta = new Respuesta(true, "Los Datos son correctos");
            } else {
                respuesta = new Respuesta(false, "Datos del CI no son correctos");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        //----------------------------------------------------------------------------------------------------
        /*if (CI.equals(ci) && Nombres.equals("Walter Jhamil") && Apellido.equals("Segovia Arellano")) {
            respuesta = new Respuesta(true, "Los Datos son correctos");
        } else {
            respuesta = new Respuesta(false, "Datos del CI no son correctos");
        }*/

        return respuesta;
    }

}
