package lab3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {

    String bd = "sisdislab3";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;
    Statement st = null;
    ResultSet rs = null;

    public Conexion() {
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Connection getCx() {
        return cx;
    }

    public void setCx(Connection cx) {
        this.cx = cx;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
    

    public Connection conectar() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, password);
            System.out.println("SE CONECTO A " + bd);

            //Consulta----------------------------------------------
            /*st = cx.createStatement();
            rs = st.executeQuery("Select * from articulos ");
            while (rs.next()) {
                int idArticulo = rs.getInt(1);
                String descrip = rs.getString(2);
                String precio = rs.getString(3);
                System.out.println("Id: " + idArticulo);
                System.out.println("Descrip: " + descrip);
                System.out.println("Precio: " + precio);
            }*/
            //------------------------------------------------------
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("NO SE CONECTO A BD" + bd);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }
    
    public Connection consultar() {
        try {
            cx = DriverManager.getConnection(url + bd, user, password);

            //Consulta----------------------------------------------
            st = cx.createStatement();
            rs = st.executeQuery("Select * from alumnos ");
            while (rs.next()) {
                int idArticulo = rs.getInt(1);
                String descrip = rs.getString(2);
                String precio = rs.getString(3);
                System.out.println("Id: " + idArticulo);
                System.out.println("Descrip: " + descrip);
                System.out.println("Precio: " + precio);
            }
            //------------------------------------------------------
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }

    public void desconectar() {
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        conexion.conectar();
        conexion.consultar();
    }
}
