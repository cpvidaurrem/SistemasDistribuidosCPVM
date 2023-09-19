/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab3;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author CHRISTIAN
 */
public interface IUniversidad extends Remote{
    public Diploma emitirDiploma(String CI, String Nombres, String p1erApellido, String p2doApellido, String fecha_nacimiento, String carrera)throws RemoteException;
    
}
